package me.alanfoster.intellij.blueprint.language.psi;

import com.intellij.psi.PsiElement;

/**
 * This util class will automatically be injected into generated PsiElements from
 * the Injection.bnf grammar.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see me.alanfoster.intellij.blueprint.language.grammar
 */
public class InjectionPsiImplUtil {

    // TODO - This isn't called yet
    public static String getName(InjectionPropertyDefinition element) {
        return element.getText();
    }

    // TODO - This isn't called yet
    public static PsiElement setName(InjectionPropertyDefinition element, String newName) {
        return null;
    }

    // TODO - This isn't called yet
    public static PsiElement getNameIdentifier(InjectionPropertyDefinition element) {
        return null;
    }

}
