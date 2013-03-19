package me.alanfoster.intellij.camel.simple.language.tooling.languageinjector;

import com.intellij.lang.Language;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlText;
import me.alanfoster.intellij.camel.simple.language.SimpleLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * This injector deals with injecting the Simple language into the camel
 * DSL roots
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SimpleLanguageInjector implements LanguageInjector {

    @Override
    public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost psiLanguageInjectionHost,
                                     @NotNull InjectedLanguagePlaces injectedLanguagePlaces) {
        // Attempt to match an xml element with a parent node which has a local name
        // of simple, IE <simple>${simple-expression}</simple>
        if (psiLanguageInjectionHost instanceof XmlText) {
            XmlText xmlText = (XmlText) psiLanguageInjectionHost;
            XmlTag xmlTagParent = xmlText.getParentTag();

            String xmlTagParentLocalName = xmlTagParent.getLocalName();

            if(xmlTagParentLocalName != null && xmlTagParentLocalName.equals("simple")){
                TextRange xmlTextRange = psiLanguageInjectionHost.getTextRange();
                TextRange expressionTextRange = TextRange.from(0, psiLanguageInjectionHost.getTextLength());
                injectedLanguagePlaces.addPlace(SimpleLanguage.INSTANCE, expressionTextRange, null, null);
            }

            if(xmlTagParentLocalName != null && xmlTagParentLocalName.equals("xpath")){
                // Find the XPath language
                Language xpathLanguage = Language.findLanguageByID("XPath");

                TextRange xmlTextRange = psiLanguageInjectionHost.getTextRange();
                TextRange expressionTextRange = TextRange.from(0, psiLanguageInjectionHost.getTextLength());
                injectedLanguagePlaces.addPlace(xpathLanguage, expressionTextRange, null, null);
            }
        }
    }

}
