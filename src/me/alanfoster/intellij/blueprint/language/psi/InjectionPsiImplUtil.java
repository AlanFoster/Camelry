package me.alanfoster.intellij.blueprint.language.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import me.alanfoster.intellij.blueprint.language.InjectionTypes;

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
        return element.getPropertyName();
    }

    // TODO - This isn't called yet
    public static PsiElement setName(InjectionPropertyDefinition element, String newName) {
        return null;
    }

    // TODO - This isn't called yet
    public static PsiElement getNameIdentifier(InjectionPropertyDefinition element) {
        return element.getPropertyElement();
    }

    public static String getPropertyName(InjectionPropertyDefinition element) {
        ASTNode propertyNameNode = element.getNode().findChildByType(InjectionTypes.PROPERTY_NAME);
        if(propertyNameNode != null) {
            return propertyNameNode.getText();
        } else {
            return null;
        }
    }

    public static PsiElement getPropertyElement(InjectionPropertyDefinition element) {
        ASTNode propertyNameNode = element.getNode().findChildByType(InjectionTypes.PROPERTY_NAME);
        if(propertyNameNode != null) {
            return propertyNameNode.getPsi();
        } else {
            return null;
        }
    }


}
