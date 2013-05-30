package me.alanfoster.intellij.blueprint.dom;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.HyphenNameStrategy;
import com.intellij.util.xml.NameStrategy;
import com.intellij.util.xml.Namespace;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@NameStrategy(value = HyphenNameStrategy.class)
@Namespace("http://aries.apache.org/blueprint/xmlns/blueprint-cm/v1.0.0")
public interface PropertyPlaceholder extends DomElement {
    @NotNull
    DefaultProperties getDefaultProperties();
}
