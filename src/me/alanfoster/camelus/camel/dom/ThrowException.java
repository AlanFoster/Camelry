package me.alanfoster.camelus.camel.dom;

import com.intellij.util.xml.*;
import me.alanfoster.camelus.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelus.blueprint.dom.inspectors.BlueprintBeanRefExtends;
import me.alanfoster.camelus.blueprint.dom.converters.ThrowableBlueprintBeanConverter;

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
