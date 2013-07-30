package me.alanfoster.camelry.camel.dom;

import com.intellij.util.xml.DomElement;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */

public interface Expression extends DomElement {
    /**
     * Get the value of the expression element.
     * Note - LangaugeInjection will automatically happen via CamelLanguageInjector
     *
     * @return The string value
     */
    String getValue();
}
