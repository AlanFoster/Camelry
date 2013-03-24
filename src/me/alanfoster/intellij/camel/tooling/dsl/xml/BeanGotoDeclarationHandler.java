package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandlerBase;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.psi.xml.XmlTag;
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

        // TODO Rewrite this mess :)


        // Bean method handling

        boolean isMethod =
                xmlAttributeValue(
                        xmlAttribute("method")
                                .withSuperParent(1, xmlTag().withLocalName("bean"))
                )
                .accepts(parent);


        if(isMethod) {
            Project project = sourceElement.getProject();
            XmlAttributeValue methodAttributeValue = (XmlAttributeValue) parent;
            final String methodAttributeValueString = methodAttributeValue.getValue();

            XmlAttribute xmlAttribute = (XmlAttribute) methodAttributeValue.getParent();
            XmlTag xmlTag = xmlAttribute.getParent();

            String beanId = xmlTag.getAttributeValue("ref");

            List<Bean> beans = BeanHelper.findBeans(project, beanId);

            if(beans.size() == 1) {
                Bean bean = beans.get(0);

                String className = bean.getClassAttribute().getStringValue();

                PsiClass psiClass = bean.getClassAttribute().getValue();


                PsiMethod[] methods = psiClass.findMethodsByName(methodAttributeValueString, true);

                if(methods.length == 0) {
                    return null;
                }

                return methods[0];
            }

            //List<Bean> beans = BeanHelper.findBeans(project, )
            //final GenericAttributeValue domElement = DomManager.getDomManager(project).getDomElement(xmlAttribute);

        }


        // Bean reference handling

        boolean isRef =
                xmlAttributeValue(
                        xmlAttribute("ref")
                                .withSuperParent(1, xmlTag().withLocalName("bean"))
                )
                .accepts(parent);
        if (isRef) {
            Project project = sourceElement.getProject();
            XmlAttributeValue attributeValue = (XmlAttributeValue) parent;
            final String value = attributeValue.getValue();

            List<Bean> beans = BeanHelper.findBeans(project, value);
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
