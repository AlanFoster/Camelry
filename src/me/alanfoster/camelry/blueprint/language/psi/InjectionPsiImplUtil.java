package me.alanfoster.camelry.blueprint.language.psi;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.xml.DomManager;
import com.intellij.util.xml.GenericAttributeValue;
import me.alanfoster.camelry.blueprint.dom.model.Property;
import me.alanfoster.camelry.blueprint.language.contributors.InjectionPsiReference;
import me.alanfoster.camelry.blueprint.language.InjectionTypes;
import me.alanfoster.camelry.blueprint.language.psi.InjectionPropertyDefinition;
import org.jetbrains.annotations.Nullable;

/**
 * This util class will automatically be injected into generated PsiElements from
 * the Injection.bnf grammar.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see me.alanfoster.camelry.blueprint.language.grammar
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

    @Nullable
    public static Property getReferencedProperty(InjectionPropertyDefinition element) {
        Project project = element.getProject();
        PsiElement reference = new InjectionPsiReference(element).resolve();
        if(!(reference instanceof XmlAttributeValue)) return null;
        XmlAttribute attribute = (XmlAttribute) reference.getParent();
        DomManager domManager = DomManager.getDomManager(project);
        GenericAttributeValue domElement = domManager.getDomElement(attribute);
        if(domElement == null) return null;
        Property property = domElement.getParentOfType(Property.class, true);
        return property;
    }
}
