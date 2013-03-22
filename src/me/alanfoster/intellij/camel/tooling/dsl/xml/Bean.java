package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface Bean extends DomElement {
    /**
     * This attribute id must be unique, hence the @NameValue
     * @see com.intellij.util.xml.NameValue
     */
    // TODO How do I actually wire up NameValue to validate? :)
    @NameValue
    @Attribute("id")
    GenericAttributeValue<String> getId();

    @Attribute("class")
    @NotNull
    GenericAttributeValue<String> getClassAttribute();
}
