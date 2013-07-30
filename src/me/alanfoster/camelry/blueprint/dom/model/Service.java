package me.alanfoster.camelry.blueprint.dom.model;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.*;
import me.alanfoster.camelry.blueprint.dom.converters.BlueprintBeanPointerConverter;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a Blueprint service element.
 */
public interface Service extends DomElement {
    @Attribute("ref")
    @Convert(BlueprintBeanPointerConverter.class)
    @NotNull
    GenericAttributeValue<String> getRef();

    @Attribute("interface")
    @NotNull
    @Required(nonEmpty = true, value = true)
    @ExtendClass(allowInterface = true, instantiatable = false)
    GenericAttributeValue<PsiClass> getInterface();
}
