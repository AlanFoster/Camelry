package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;


/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BeanReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {

        // Attempt to match the following
        //  <bean bean-ref="bean-name" />
        // for auto completion

        //XmlAttributeValuePattern beanRefPattern = xmlAttributeValue().withLocalName("bean-ref")
        //        .withSuperParent(1, xmlTag().withLocalName("bean"));

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(XmlAttribute.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement psiElement,
                                                                 @NotNull ProcessingContext context) {
                        XmlAttribute xmlAttribute = (XmlAttribute) psiElement;
                        String attributeName = xmlAttribute.getLocalName();
                        String parentElementName = xmlAttribute.getParent().getLocalName();

                        if("bean-ref".equals(attributeName) && "bean".equals(parentElementName)) {
                            String beanName = xmlAttribute.getDisplayValue();

                            final XmlAttributeValue valueElement = xmlAttribute.getValueElement();

                            return new PsiReference[] { new BeanReference(valueElement, new TextRange(1, valueElement.getTextLength() -1))};
                            //return new PsiReference[]{new BeanReference(psiElement, new TextRange("bean-ref=\"".length() + 1, xmlAttribute.getTextLength()))};
                        }

                        return PsiReference.EMPTY_ARRAY;

                        //return CommonReferenceProviderTypes.PROPERTIES_FILE_KEY_PROVIDER.getProvider().getReferencesByElement(psiElement, context);
                    }
                });
    }


}
