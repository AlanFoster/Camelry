package me.alanfoster.camelus.blueprint.dom.model;

import com.intellij.util.xml.*;
import me.alanfoster.camelus.blueprint.BlueprintConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@NameStrategy(value = HyphenNameStrategy.class)
@Stubbed
@Namespace(value = BlueprintConstants.BLUEPRINT_CM)
public interface PropertyPlaceholder extends DomElement {
    @NotNull
    @Required(identifier = true)
    GenericAttributeValue<String> getPersistentId();

    @NotNull
    DefaultProperties getDefaultProperties();
}
