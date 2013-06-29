package me.alanfoster.camelus.camel.actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomManager;
import me.alanfoster.camelus.blueprint.dom.Blueprint;
import me.alanfoster.camelus.camel.dom.Route;
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
    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile, DataContext dataContext) {
        final SelectionModel selectionModel = editor.getSelectionModel();
        if(!selectionModel.hasSelection()) return;

        final Blueprint blueprintRoot = getBlueprintRoot(project, editor, psiFile);
        if(blueprintRoot == null) return;

        //final String newRouteUri = Messages.showInputDialog(project, "title", "label", Messages.getQuestionIcon());
        final String newRouteUri = "direct:foo";

        // Find Selection start + end to copy into a new route
        PsiElement startElementSelection = psiFile.findElementAt(selectionModel.getSelectionStart());
        PsiElement endElementSelection = psiFile.findElementAt(selectionModel.getSelectionEnd());

        if(startElementSelection == null || endElementSelection == null) return;

        new WriteCommandAction.Simple(project) {
            @Override
            protected void run() throws Throwable {
                // Create a new route with the given route uri
                Route route = blueprintRoot.getCamelContext().addRoute();
                route.getFrom().getUri().setStringValue(newRouteUri);

                // Copy Start + end selection elements into the new route below the `from` domElement
            }
        }.execute();
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
     * @param editor The editor
     * @return the Blueprint root DOM element from the given action event,
     *         this will be null if if not found
     */
    private Blueprint getBlueprintRoot(@NotNull Project project, Editor editor, PsiFile psiFile) {
        if (editor == null || !(psiFile instanceof XmlFile)) return null;

        XmlFile xmlFile = (XmlFile) psiFile;
        DomManager domManager = DomManager.getDomManager(project);
        DomFileElement<Blueprint> fileElement = domManager.getFileElement(xmlFile, Blueprint.class);

        if (fileElement == null || !fileElement.exists()) return null;

        Blueprint rootElement = fileElement.getRootElement();
        return rootElement;
    }
}

