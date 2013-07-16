package me.alanfoster.camelus.blueprint.dom.model;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.*;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a Blueprint OSGi service reference DomElement
 */
public interface BlueprintReference extends DomElement, BlueprintBeanPointer {
    @Attribute("interface")
    @NotNull
    @Required(nonEmpty = true, value = true)
    @ExtendClass(allowInterface = true, instantiatable = false)
    GenericAttributeValue<PsiClass> getInterface();
}
