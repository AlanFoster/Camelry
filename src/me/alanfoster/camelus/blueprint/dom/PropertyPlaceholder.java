package me.alanfoster.camelus.blueprint.dom;

import com.intellij.util.xml.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@NameStrategy(value = HyphenNameStrategy.class)
@Stubbed
public interface PropertyPlaceholder extends DomElement {
    @NotNull
    DefaultProperties getDefaultProperties();
}
