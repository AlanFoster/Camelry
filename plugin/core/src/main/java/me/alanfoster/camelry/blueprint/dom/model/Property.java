package me.alanfoster.camelry.blueprint.dom.model;

import com.intellij.util.xml.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
// TODO Deal with more than just key/value pairs, and handle the List/Map notation etc
@NameStrategy(value = JavaNameStrategy.class)
public interface Property extends DomElement {
    @NotNull
    @Attribute("name")
    @NameValue(unique = true)
    @Required(nonEmpty = true, value = true)
    GenericAttributeValue<String> getName();

    @Attribute("value")
    @NotNull
    @Required(nonEmpty = true, value = false)
    GenericAttributeValue<String> getValue();
}
