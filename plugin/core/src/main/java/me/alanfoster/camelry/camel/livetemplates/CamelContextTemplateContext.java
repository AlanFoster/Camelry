package me.alanfoster.camelry.camel.livetemplates;

import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.ide.impl.ProjectUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.ex.ProjectRoot;
import com.intellij.psi.PsiFile;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomManager;
import me.alanfoster.camelry.camel.dom.CamelContextFactoryBean;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Defines a the context for a live template which must be injected as a direct child
 * of a CamelContext node.
 */
public class CamelContextTemplateContext extends CamelryTemplateContext {
    private static final String ID = "CAMEL_CONTEXT";
    private static final String PRESENTABLE_NAME = "Camel Context";

    public CamelContextTemplateContext() {
        super(ID, PRESENTABLE_NAME);
    }

    @Override
    public boolean isInContext(@NotNull Project project, @NotNull DomElement domElement) {
        return domElement instanceof CamelContextFactoryBean;
    }
}
