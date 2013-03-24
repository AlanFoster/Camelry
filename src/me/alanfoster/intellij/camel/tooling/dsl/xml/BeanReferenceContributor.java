package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.XmlNamedElementPattern;
import com.intellij.psi.*;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.XmlPatterns.xmlAttribute;
import static com.intellij.patterns.XmlPatterns.xmlTag;


/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BeanReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {

        // Attempt to match the following
        //  <bean ref="bean-name" />
        // for auto completion

        //xmlAttributeValue().withLocalName("bean-ref")
        //.withSuperParent(1, withDom(domElement(Bean.class)))

        final XmlNamedElementPattern beanRefPattern = xmlAttribute().withLocalName("ref")
                .withSuperParent(1, xmlTag().withLocalName("bean"));

        registrar.registerReferenceProvider(beanRefPattern,
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement psiElement,
                                                                 @NotNull ProcessingContext context) {
                        XmlAttribute xmlAttribute = (XmlAttribute) psiElement;
                        XmlAttributeValue valueElement = xmlAttribute.getValueElement();

                        TextRange valueTextRange = valueElement.getValueTextRange();

                        return new PsiReference[]{new BeanReference(valueElement, new TextRange(1, valueElement.getTextLength() - 1))};
                    }
                });
    }


}
