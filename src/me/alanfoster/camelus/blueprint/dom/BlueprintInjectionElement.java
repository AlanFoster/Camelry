package me.alanfoster.camelus.blueprint.dom;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;

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
 //   @Referencing()
    GenericAttributeValue<String> getValue();

    @Attribute("ref")
    GenericAttributeValue<String> getRef();
}
