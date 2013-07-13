package me.alanfoster.camelus.camel.actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.RunResult;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlElement;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomManager;
import me.alanfoster.camelus.blueprint.dom.Blueprint;
import me.alanfoster.camelus.camel.dom.Route;
import org.jetbrains.annotations.NotNull;

import static me.alanfoster.camelus.CamelusBundle.message;

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
        boolean isSuccess = invoke(project, editor, psiFile);
        if (!isSuccess) {
            CommonRefactoringUtil
                    .showErrorHint(project, editor,
                            message("camelus.camel.actions.extract.route.error.message"),
                            message("camelus.camel.actions.extract.route.error.title"),
                            null);
        }
        editor.getSelectionModel().removeSelection();
    }

    /**
     * @param project See super class invoke
     * @param editor  See super class invoke
     * @param psiFile See super class invoke
     * @return True if the refactoring has been successful, or the user has cancelled the operation.
     *         False otherwise, ie a validation error.
     */
    private boolean invoke(final Project project, final Editor editor, final PsiFile psiFile) {
        final SelectionModel selectionModel = editor.getSelectionModel();
        if (!selectionModel.hasSelection()) return false;

        final Blueprint blueprintRoot = getBlueprintRoot(project, psiFile);
        if (blueprintRoot == null) return false;

        // Find Selection start + end to copy into a new route
        final PsiElement startElement = getNextNonWhitespaceElementAt(psiFile, selectionModel.getSelectionStart());
        final PsiElement endElement = getPreviousNonWhitespaceElementAt(psiFile, selectionModel.getSelectionEnd());

        if (startElement == null || endElement == null) return false;

        final int startElementOffset = startElement.getTextRange().getStartOffset();
        final int endElementOffset = endElement.getTextRange().getEndOffset();

        if (startElementOffset == endElementOffset) return false;

        // TODO It would be nice to allow inline naming similar to live templates
        final String newRouteUri =
                Messages.showInputDialog(project,
                        message("camelus.camel.actions.extract.route.message"),
                        message("camelus.camel.actions.extract.route.title"),
                        Messages.getQuestionIcon(),
                        message("camelus.camel.actions.extract.route.initial.value"),
                        null
                );

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
                XmlTag selectionParentTag = PsiTreeUtil.getParentOfType(startElement, XmlTag.class, true);
                if (selectionParentTag == null) return false;

                // Copy Start + end selection elements into the new route below the `from` domElement
                PsiElement[] newElements = copyElements(project, psiFile, startElementOffset, endElementOffset);

                if (newElements.length == 0) return false;

                Route newRoute = createNewRoute(blueprintRoot, newRouteUri);
                copyElementsIntoRoute(newRoute, newElements);

                // Create new 'to' element to send to the new route
                XmlTag to = getToXmlTag(selectionParentTag);
                to = (XmlTag) selectionParentTag.addBefore(to, startElement);

                // Delete the now refactored XML :)
                selectionParentTag.deleteChildRange(startElement, endElement);

                // Format created code - Doesn't seem to be needed, seems to format perfectly without any help :)
                // CodeStyleManager.getInstance().adjustLineIndent

                int toUriOffset = to.getAttribute("uri").getValueElement().getTextRange().getStartOffset() + 1;
                editor.getCaretModel().moveToOffset(toUriOffset);

                return true;
            }


            private XmlTag getToXmlTag(XmlTag selectionParentTag) {
                XmlTag to = selectionParentTag.createChildTag("to", selectionParentTag.getNamespace(), null, false);
                to.setAttribute("uri", newRouteUri);
                return to;
            }

            private Route createNewRoute(Blueprint blueprintRoot, String newRouteUri) {
                // Create a new route with the given route uri
                Route route = blueprintRoot.getCamelContext().addRoute();
                route.getFrom().getUri().setStringValue(newRouteUri);
                return route;
            }

            /**
             * Copies the required PsiELements into the new route, beneath the 'from' element
             * @param route The blueprint route
             * @param newElements The new elmeents to copy beneath the from element
             */
            private void copyElementsIntoRoute(Route route, PsiElement[] newElements) {
                XmlElement newRouteElement = route.getXmlElement();
                assert newRouteElement != null;
                XmlElement fromElement = route.getFrom().getXmlElement();

                if (newElements.length == 1) {
                    newRouteElement.addAfter(newElements[0], fromElement);
                } else {
                    newRouteElement.addRangeAfter(newElements[0], newElements[newElements.length - 1], fromElement);
                }
            }

            /**
             * In IntelliJ you should create new elements, rather than modify existing.
             * This method helps with this by creating a temp Xml document and returning
             * the newly created elements.
             *
             * @return A new copy of the required Xml elements.
             */
            private PsiElement[] copyElements(Project project, PsiFile psiFile, int startElementOffset, int endElementOffset) {
                String extractedText = psiFile.getText().substring(startElementOffset, endElementOffset);

                // Building a new document from a string is commnon practice
                // As demoed by Jetbrain's CTO during the 'Live Coding a plugin from scratch' webinar
                String tempDocumentXml =
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                                + "<temp>" + extractedText + "</temp>";

                String tempFileName = "__" + psiFile.getName();
                XmlFile tempDocument = (XmlFile) PsiFileFactory.getInstance(project)
                        .createFileFromText(tempFileName, StdFileTypes.XML, tempDocumentXml);

                XmlTag rootTag = tempDocument.getRootTag();
                assert rootTag != null;
                return rootTag.getSubTags();
            }
        }.execute();

        return refactoringResult.getResultObject();
    }

    private PsiElement getNextNonWhitespaceElementAt(PsiFile psiFile, int position) {
        PsiElement element = psiFile.findElementAt(position);
        return element instanceof PsiWhiteSpace ? PsiTreeUtil.nextLeaf(element) : element;
    }

    private PsiElement getPreviousNonWhitespaceElementAt(PsiFile psiFile, int position) {
        PsiElement element = psiFile.findElementAt(position);
        return element instanceof PsiWhiteSpace ? PsiTreeUtil.prevLeaf(element) : element;
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiElement[] elements, DataContext dataContext) {
        throw new UnsupportedOperationException();
    }

    /**
     * Extracts the Blueprint root DOM element from the given action event.
     * Note, this method has no side effects on the event passed in.
     *
     * @param project The project
     * @return the Blueprint root DOM element from the given action event,
     *         this will be null if if not found
     */
    private Blueprint getBlueprintRoot(@NotNull Project project, PsiFile psiFile) {
        if (!(psiFile instanceof XmlFile)) return null;

        XmlFile xmlFile = (XmlFile) psiFile;
        DomManager domManager = DomManager.getDomManager(project);
        DomFileElement<Blueprint> fileElement = domManager.getFileElement(xmlFile, Blueprint.class);

        if (fileElement == null || !fileElement.exists()) return null;

        Blueprint rootElement = fileElement.getRootElement();
        return rootElement;
    }
}

