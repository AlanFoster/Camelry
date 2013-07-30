package me.alanfoster.camelry.blueprint.dom.model;

import com.intellij.util.xml.*;
import me.alanfoster.camelry.blueprint.BlueprintConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@NameStrategy(value = HyphenNameStrategy.class)
@Stubbed
@Namespace(value = BlueprintConstants.BLUEPRINT_CM)
public interface PropertyPlaceholder extends DomElement {
    String TAG_NAME = "property-placeholder";

    @NotNull
    @Required
    GenericAttributeValue<String> getPersistentId();

    @NotNull
    DefaultProperties getDefaultProperties();
}
