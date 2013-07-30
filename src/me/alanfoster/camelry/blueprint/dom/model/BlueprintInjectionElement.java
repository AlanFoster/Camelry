package me.alanfoster.camelry.blueprint.dom.model;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.Convert;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import me.alanfoster.camelry.blueprint.dom.converters.BlueprintBeanPointerConverter;

/**
 * Represents the root interface of an Element which can have injectable properties.
 *
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
// TODO Support more than just key/value pairs, and support map/list etc too
public interface BlueprintInjectionElement extends DomElement {
    @Attribute("value")
    GenericAttributeValue<String> getValue();

    @Attribute("ref")
    @Convert(BlueprintBeanPointerConverter.class)
    GenericAttributeValue<String> getRef();
}

