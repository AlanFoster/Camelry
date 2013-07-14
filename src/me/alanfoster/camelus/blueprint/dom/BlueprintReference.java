package me.alanfoster.camelus.blueprint.dom;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.Required;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a Blueprint OSGi service reference DomElement
 */
public interface BlueprintReference extends DomElement, BlueprintBeanPointer {
    @Attribute("interface")
    @NotNull
    @Required(nonEmpty = true, value = true)
    GenericAttributeValue<PsiClass> getInterface();
}
