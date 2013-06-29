package me.alanfoster.camelus.blueprint.language.injector;

import com.intellij.lang.Language;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.xml.XmlAttributeValue;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import me.alanfoster.camelus.blueprint.language.BlueprintInjectionLanguage;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.DomPatterns.domElement;
import static com.intellij.patterns.DomPatterns.withDom;
import static com.intellij.patterns.XmlPatterns.xmlAttributeValue;

/**
 * Injects the blueprint language into all properties/argument values of a blueprint bean.
 * <br />
 * For example
 * <pre>
 *     {@code
 *     <bean id="foo" class="foo.bar.Baz">
 *          <argument value="${hello.world}" />
 *          <property name="quux" value="${hello.world}" />
 *     </bean>
 *      }
 * </pre>
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see me.alanfoster.camelus.blueprint.dom.BlueprintBean
 */
public class BlueprintInjectionLanguageInjector implements LanguageInjector {
    private static final Logger logger = Logger.getInstance(BlueprintInjectionLanguageInjector.class);


    @Override
    public void getLanguagesToInject(final @NotNull PsiLanguageInjectionHost psiLanguageInjectionHost,
                                     final @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {

        boolean isBlueprintPropertyAttribute =
                xmlAttributeValue()
                        .withLocalName("value")
                        .withSuperParent(3, withDom(domElement(BlueprintBean.class)))
                        .accepts(psiLanguageInjectionHost);


        if (isBlueprintPropertyAttribute) {
            final XmlAttributeValue attribute = (XmlAttributeValue) psiLanguageInjectionHost;
            final TextRange injectionRange = new TextRange(1, attribute.getTextLength() - 1);

            Language blueprintLanguage = Language.findLanguageByID(BlueprintInjectionLanguage.BLUEPRINT_INJECTION_LANGUAGE);

            injectionPlacesRegistrar.addPlace(blueprintLanguage, injectionRange, null, null);
        }
    }

}
