package me.alanfoster.intellij.camel.simple.language.psi;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandlerBase;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttributeValue;
import me.alanfoster.intellij.camel.tooling.dsl.xml.Bean;
import me.alanfoster.intellij.camel.tooling.dsl.xml.BeanReference;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.intellij.patterns.XmlPatterns.*;

/**
 * Handler that allows us to go from a camel bean call
 * to the bean definition itself with a ctrl+click
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BeanGotoDeclarationHandler extends GotoDeclarationHandlerBase {
    @Nullable
    @Override
    public PsiElement getGotoDeclarationTarget(PsiElement sourceElement, Editor editor) {
        final PsiElement parent = sourceElement.getParent();
        if (parent == null) {
            return null;
        }

        boolean isValid =
                xmlAttributeValue(
                        xmlAttribute("ref")
                                .withSuperParent(1, xmlTag().withLocalName("bean"))
                )
                        .accepts(parent);
        if (isValid) {
            Project project = sourceElement.getProject();
            XmlAttributeValue attributeValue = (XmlAttributeValue) parent;

            List<Bean> beans = BeanReference.findBeans(project, attributeValue.getValue());
            if (beans.size() == 1) {
                Bean bean = beans.get(0);
                return bean.getId().getXmlAttributeValue();
            }
        }

        return null;
    }

    @Nullable
    @Override
    public String getActionText(DataContext context) {
        return null;
    }
}
