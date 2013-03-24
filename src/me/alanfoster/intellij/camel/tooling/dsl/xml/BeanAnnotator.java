package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.XmlNamedElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.patterns.XmlPatterns.xmlAttribute;
import static com.intellij.patterns.XmlPatterns.xmlTag;

/**
 * Register the bean Annotator which will highlight a reference id as wrong if it can't find it
 * Or say mark it matching a bean, and provide a text annotation when hovered over
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BeanAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element,
                         @NotNull AnnotationHolder holder) {

        final XmlNamedElementPattern beanRefPattern = xmlAttribute().withLocalName("ref")
                .withSuperParent(1, xmlTag().withLocalName("bean"));

        if (beanRefPattern.accepts(element)) {
            XmlAttribute xmlAttribute = (XmlAttribute) element;
            final XmlAttributeValue valueElement = xmlAttribute.getValueElement();
            String beanName = valueElement.getValue();

            TextRange valueTextRange = valueElement.getValueTextRange();
            Project project = element.getProject();

            List<Bean> beans = BeanReference.findBeans(project, beanName);

            if (beans.size() == 1) {
                final String beanClassValue = beans.get(0).getClassAttribute().getStringValue();
                Annotation annotation = holder.createInfoAnnotation(valueTextRange, "Class : " + beanClassValue);
                annotation.setTextAttributes(SyntaxHighlighterColors.KEYWORD);
            } else {
                Annotation annotation = holder.createErrorAnnotation(valueTextRange, "Could not resolve bean id");
            }
        }
    }
}
