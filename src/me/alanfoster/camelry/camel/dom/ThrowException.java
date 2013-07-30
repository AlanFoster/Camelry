package me.alanfoster.camelry.camel.dom;

import com.intellij.util.xml.*;
import me.alanfoster.camelry.blueprint.dom.converters.ThrowableBlueprintBeanConverter;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelry.blueprint.dom.inspectors.BlueprintBeanRefExtends;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ThrowException extends DomElement {

    @BlueprintBeanRefExtends(Throwable.class)
    @Convert(ThrowableBlueprintBeanConverter.class)
    @Attribute("ref")
    @Required
    GenericAttributeValue<BlueprintBeanPointer> getRef();

}
