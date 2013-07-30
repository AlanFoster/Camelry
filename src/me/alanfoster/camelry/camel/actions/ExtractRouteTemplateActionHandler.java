package me.alanfoster.camelry.camel.actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.RunResult;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlComment;
import com.intellij.psi.xml.XmlElement;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import me.alanfoster.camelry.CamelryBundle;
import me.alanfoster.camelry.camel.dom.CamelContext;
import me.alanfoster.camelry.camel.dom.Route;
import org.jetbrains.annotations.NotNull;

/**
 * Implements the refactoring of extracting the highlighted camel context elements into a
 * new CamelRoute.
 *
 * @see RouteRefactoringSupportProvider
 */
public class ExtractRouteTemplateActionHandler implements RefactoringActionHandler {

    // TODO Write an error when the highlighted selection is not valid
    @Override
    public void invoke(final @NotNull Project project, final Editor editor, final PsiFile psiFile, final DataContext dataContext) {
        final SelectionModel selectionModel = editor.getSelectionModel();
        if (!selectionModel.hasSelection()) {
            CommonRefactoringUtil
                    .showErrorHint(project, editor,
                            CamelryBundle.message("camelus.camel.actions.extract.route.errors.no.selection.message"),
                            CamelryBundle.message("camelus.camel.actions.extract.route.errors.no.selection.title"),
                            null);
            return;
        }

        boolean isSuccess = invoke(project, editor, psiFile);
        if (isSuccess) {
            editor.getSelectionModel().removeSelection();
        } else {
            CommonRefactoringUtil
                    .showErrorHint(project, editor,
                            CamelryBundle.message("camelus.camel.actions.extract.route.errors.unsuccessful.message"),
                            CamelryBundle.message("camelus.camel.actions.extract.route.errors.unsuccessful.title"),
                            null);
        }
    }

    /**
     * @param project See super class invoke
     * @param editor  See super class invoke
     * @param psiFile See super class invoke
     * @return True if the refactoring has been successful, or the user has cancelled the operation.
     *         False otherwise, ie a validation error.
     */
    private boolean invoke(final Project project, final Editor editor, final PsiFile psiFile) {
        Pair<XmlElement, XmlElement> startEndElements = getStartStopElements(editor.getSelectionModel(), psiFile);
        if(startEndElements == null) return false;

        final XmlElement startElement = startEndElements.getFirst();
        final XmlElement endElement = startEndElements.getSecond();

        final XmlTag selectionParentTag = PsiTreeUtil.getParentOfType(startElement, XmlTag.class, true);
        if (selectionParentTag == null) return false;

        final XmlTag parentRoute = getParentXmlTagOfType(selectionParentTag, Route.class, false);
        if(parentRoute == null) return false;

        // TODO It would be nice to allow inline naming similar to live templates
        final String newRouteUri = getNewRouteUri(project);

        // This value will be null when the user hits 'cancel', this is a valid action, therefore return true
        if (StringUtil.isEmpty(newRouteUri)) return true;

        RunResult<Boolean> refactoringResult = new WriteCommandAction<Boolean>(project) {
            @Override
            protected void run(Result<Boolean> result) throws Throwable {
                result.setResult(performRefactor());
            }

            /**
             * Performs the required refactor
             * @return True if the refactoring has been successful.
             *         False otherwise, ie a validation error.
             */
            private boolean performRefactor() {
                // Pull out our existing camel route so we can insert the new camel route beneath it
                if (!insertNewRoute(selectionParentTag, parentRoute)) return false;

                // Create new 'to' element to send to the new route
                XmlTag to = createToXmlTag(selectionParentTag);
                to = (XmlTag) selectionParentTag.addBefore(to, startElement);

                // Delete the now refactored XML :)
                selectionParentTag.deleteChildRange(startElement, endElement);

                // Format created code - Doesn't seem to be needed, seems to format perfectly without any help :)
                // CodeStyleManager.getInstance().adjustLineIndent

                int toUriOffset = to.getAttribute("uri").getValueElement().getTextRange().getStartOffset() + 1;
                editor.getCaretModel().moveToOffset(toUriOffset);

                return true;
            }

            /**
             * Inserts a new route into the given document underneath the topmost route parent.
             * @param selectionParentTag
             * @param previousRoute the route being refactored
             * @return True if successful, otherwise false.
             */
            private boolean insertNewRoute(@NotNull XmlTag selectionParentTag, @NotNull XmlTag previousRoute) {
                XmlTag camelContext = getParentXmlTagOfType(previousRoute, CamelContext.class, false);
                if(camelContext == null) return false;

                // Create a new route, copying the start + end selection elements into the new route below the `from` domElement
                XmlTag newRoute = createNewRoute(newRouteUri, project, psiFile, startElement, endElement);
                camelContext.addAfter(newRoute, previousRoute);
                return true;
            }

            private XmlTag createToXmlTag(XmlTag selectionParentTag) {
                XmlTag to = selectionParentTag.createChildTag("to", selectionParentTag.getNamespace(), null, false);
                to.setAttribute("uri", newRouteUri);
                return to;
            }

            /**
             * In IntelliJ you should create new elements, rather than modify existing.
             * This method helps with this by creating a temp Xml document and returning
             * the newly route elements
             *
             * @return A route with a copy of the required Xml elements.
             */
            private XmlTag createNewRoute(String routeUri, Project project, PsiFile psiFile, PsiElement startElement, PsiElement endElement) {
                final int startElementOffset = startElement.getTextRange().getStartOffset();
                final int endElementOffset = endElement.getTextRange().getEndOffset();

                String extractedText = psiFile.getText().substring(startElementOffset, endElementOffset);

                // Building a new document from a string is common practice
                // As demoed by Jetbrain's CTO during the 'Live Coding a plugin from scratch' webinar
                StringBuilder stringBuilder = new StringBuilder();
                String tempDocumentXml =
                        stringBuilder
                                .append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
                                .append("<route>")
                                .append("<from uri=\"").append(routeUri).append("\"/>")
                                .append(extractedText)
                                .append("</route>")
                        .toString();

                String tempFileName = "__" + psiFile.getName();
                XmlFile tempDocument = (XmlFile) PsiFileFactory.getInstance(project)
                        .createFileFromText(tempFileName, StdFileTypes.XML, tempDocumentXml);

                XmlTag rootTag = tempDocument.getRootTag();
                assert rootTag != null;
                return rootTag;
            }
        }.execute();

        return refactoringResult.getResultObject();
    }

    private String getNewRouteUri(Project project) {
        String defaultRouteUri = CamelryBundle.message("camelus.camel.actions.extract.route.initial.value");
        // Highlight by default the value after the default component, ie "direct:<selection>newRoute</selection>"
        int componentDivider = defaultRouteUri.indexOf(':') + 1;
        TextRange highlightedTextRange = new TextRange(componentDivider, defaultRouteUri.length());
        return Messages.showInputDialog(project,
                CamelryBundle.message("camelus.camel.actions.extract.route.message"),
                CamelryBundle.message("camelus.camel.actions.extract.route.title"),
                Messages.getQuestionIcon(),
                defaultRouteUri,
                null,
                highlightedTextRange
        );
    }

    /**
     * Extract the start and end XmlElements which are in the given PsiFile.
     *
     * @param selectionModel
     * @param psiFile
     * @return Null if the *either* of the required elements cannot be extract successfully.
     *          Otherwise the pair of start and end elements.
     */
    @SuppressWarnings("unchecked")
    private Pair<XmlElement, XmlElement> getStartStopElements(SelectionModel selectionModel, PsiFile psiFile) {
        // Find Selection start + end to copy into a new route
        final PsiElement startElement = getNextNonWhitespaceElementAt(psiFile, selectionModel.getSelectionStart());
        final PsiElement endElement = getPreviousNonWhitespaceElementAt(psiFile, selectionModel.getSelectionEnd());
        if (startElement == null || endElement == null) return null;

        final XmlElement startTag = PsiTreeUtil.getParentOfType(startElement, XmlTag.class, XmlComment.class);
        final XmlElement endTag = PsiTreeUtil.getParentOfType(endElement, XmlTag.class, XmlComment.class);

        if(startTag == null || endTag == null) return null;
        // Handle the scenario of consuming consuming a startTag after the endTag, IE during white-space only selection
        if(startTag.getTextOffset() > endTag.getTextOffset()) return null;
        if(startTag.getNode().getTreeParent() != endTag.getNode().getTreeParent()) return null;

        return new Pair<XmlElement, XmlElement>(startTag, endTag);
    }


    private PsiElement getNextNonWhitespaceElementAt(PsiFile psiFile, int position) {
        PsiElement element = psiFile.findElementAt(position);
        return element instanceof PsiWhiteSpace ? PsiTreeUtil.nextLeaf(element) : element;
    }

    private PsiElement getPreviousNonWhitespaceElementAt(PsiFile psiFile, int position) {
        PsiElement element = psiFile.findElementAt(position - 1);
        return element instanceof PsiWhiteSpace ? PsiTreeUtil.prevLeaf(element) : element;
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiElement[] elements, DataContext dataContext) {
        throw new UnsupportedOperationException();
    }


    private static XmlTag getParentXmlTagOfType(XmlTag tag, Class<? extends DomElement> requiredDomClass, boolean strict) {
        DomElement domElement = DomUtil.getDomElement(tag);
        if(domElement == null) return null;
        DomElement parent = domElement.getParentOfType(requiredDomClass, strict);
        return parent == null ? null : parent.getXmlTag();
    }

}



