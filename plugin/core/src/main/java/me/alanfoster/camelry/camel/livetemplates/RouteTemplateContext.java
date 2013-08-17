package me.alanfoster.camelry.camel.livetemplates;

import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import me.alanfoster.camelry.camel.dom.RouteDefinition;
import org.jetbrains.annotations.NotNull;

/**
 * Defines the Live Template context for a Camel Route. Which defines a possible place for a live template
 * to be performed, IE ctrl+j
 */
public class RouteTemplateContext extends CamelryTemplateContext {
    private static final String ID = "CAMEL_ROUTE";
    private static final String PRESENTABLE_NAME = "Camel Route";

    public RouteTemplateContext() {
        super(ID, PRESENTABLE_NAME);
    }

    /**
     * @param domElement The currently selected domElement
     * @return True if the offset is contained within a camel route definition DomElement
     *         false otherwise.
     */
    @Override
    public boolean isInContext(@NotNull Project project, @NotNull DomElement domElement) {
        RouteDefinition routeDefinitionParent = domElement.getParentOfType(RouteDefinition.class, false);
        boolean isRouteDefinitionChild = routeDefinitionParent != null;

        return isRouteDefinitionChild;
    }
}
