package me.alanfoster.camelry.camel.tooling.languageinjector;

import com.intellij.lang.Language;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlText;
import me.alanfoster.camelry.configuration.CamelryConfigurationModel;
import me.alanfoster.camelry.configuration.CamelryConfigurationService;
import org.jetbrains.annotations.NotNull;

/**
 * This injector deals with injecting the Simple language into the camel
 * DSL roots
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class CamelLanguageInjector implements LanguageInjector {
    public static Logger logger = Logger.getInstance(CamelLanguageInjector.class);

    /**
     * Attempt to match an xml element with a parent node which has a local name of simple,
     * IE <simple>${simple-expression}</simple>
     *
     * @param psiLanguageInjectionHost
     * @param injectedLanguagePlaces
     */
    @Override
    public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost psiLanguageInjectionHost,
                                     @NotNull InjectedLanguagePlaces injectedLanguagePlaces) {
        // Try to inject a language, somewhat abusing the lazy evaluation of predicates :(
        boolean isLanguageInjected =
                // TODO Simple can also be used within the log component
                (isSimpleLanguageInjected() && tryInjectLanguage("simple", "Simple", psiLanguageInjectionHost, injectedLanguagePlaces))
                        || tryInjectLanguage("xpath", "XPath", psiLanguageInjectionHost, injectedLanguagePlaces)
                        || tryInjectLanguage("javaScript", "JavaScript", psiLanguageInjectionHost, injectedLanguagePlaces)
                        || tryInjectLanguage("groovy", "Groovy", psiLanguageInjectionHost, injectedLanguagePlaces);

    }

    /**
     * Attempts to inject the given language into an element if the local name is as expected.
     *
     * @param expectedXmlTagParentLocalName The expected name of the element, IE 'simple' for the Simple language.
     * @param languageId                    The ID of the language to inject into the required element
     * @param psiLanguageInjectionHost
     * @param injectedLanguagePlaces
     * @return True if the language was successfully injected - IE the element's localname
     *         matched and the language was found
     */
    private boolean tryInjectLanguage(@NotNull String expectedXmlTagParentLocalName,
                                      @NotNull String languageId,
                                      @NotNull PsiLanguageInjectionHost psiLanguageInjectionHost,
                                      @NotNull InjectedLanguagePlaces injectedLanguagePlaces) {

        if (isExpectedLocalName(expectedXmlTagParentLocalName, psiLanguageInjectionHost)) {
            injectLanguage(languageId, psiLanguageInjectionHost, injectedLanguagePlaces);
            return true;
        }
        return false;
    }

    private boolean isExpectedLocalName(@NotNull String expectedXmlTagParentLocalName,
                                        @NotNull PsiLanguageInjectionHost psiLanguageInjectionHost) {
        if (!(psiLanguageInjectionHost instanceof XmlText)) {
            return false;
        }

        XmlText xmlText = (XmlText) psiLanguageInjectionHost;
        XmlTag xmlTagParent = xmlText.getParentTag();
        String xmlTagParentLocalName = xmlTagParent.getLocalName();

        return expectedXmlTagParentLocalName.equals(xmlTagParentLocalName);
    }

    /**
     * Injects the required language into the psiLanguageInjectionHost host
     *
     * @param languageId               The ID of the language to inject into the required element
     * @param psiLanguageInjectionHost
     * @param injectedLanguagePlaces
     */
    private void injectLanguage(@NotNull String languageId,
                                @NotNull PsiLanguageInjectionHost psiLanguageInjectionHost,
                                @NotNull InjectedLanguagePlaces injectedLanguagePlaces) {
        // Find the required Language
        Language requiredLanguage = Language.findLanguageByID(languageId);
        logger.debug("The required languageId '" + languageId + "' value was '" + requiredLanguage + "'");
        if(requiredLanguage == null) return;

        TextRange xmlTextRange = psiLanguageInjectionHost.getTextRange();
        TextRange expressionTextRange = TextRange.from(0, psiLanguageInjectionHost.getTextLength());
        injectedLanguagePlaces.addPlace(requiredLanguage, expressionTextRange, null, null);
    }

    public boolean isSimpleLanguageInjected() {
        CamelryConfigurationModel configuration = CamelryConfigurationService.getConfiguration();
        return configuration.isSimpleLanguageInjected();
    }
}
