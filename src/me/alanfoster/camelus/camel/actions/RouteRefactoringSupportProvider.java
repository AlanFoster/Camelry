package me.alanfoster.camelus.camel.actions;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomManager;
import com.intellij.util.xml.DomUtil;
import me.alanfoster.camelus.blueprint.dom.BlueprintFileDescription;
import me.alanfoster.camelus.blueprint.model.BlueprintManager;
import me.alanfoster.camelus.camel.dom.Route;
import org.jetbrains.annotations.NotNull;

/**
 * Provides refactoring support for the highlighted section of a camel route and extracts it into a
 * new camel route.
 *
 * @see ExtractRouteTemplateActionHandler
 */
public class RouteRefactoringSupportProvider extends RefactoringSupportProvider {

    /**
     * Route Refactoring support should only be available when calling refactoring
     * under the context of a blueprint file.
     * Specific user error reporting will occur within the handler, if required.
     *
     * @param context refactoring context
     * @return True if the psiElement is within a Blueprint PsiFile
     *         Otherwise false.
     */
    // TODO Add extract this logic and add it to invoke to provide more meaningful error/usage messages
    @Override
    public boolean isAvailable(@NotNull PsiElement context) {
        PsiFile containingFile = context.getContainingFile();
        Project project = context.getProject();
        return containingFile instanceof XmlFile
                && DomManager.getDomManager(project).getDomFileDescription((XmlFile) containingFile) instanceof BlueprintFileDescription
                && super.isAvailable(context);
    }

    @Override
    public RefactoringActionHandler getExtractMethodHandler() {
        return new ExtractRouteTemplateActionHandler();
    }
}
