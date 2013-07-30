package me.alanfoster.camelry.camel.simple.language;

import com.intellij.lang.Language;

/**
 * Represents a Language class.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SimpleLanguage extends Language {
    /**
     * A public non-lazyily instantiated singleton seems to be
     * the common pattern used for IntelliJ plugins.
     */
    public static SimpleLanguage INSTANCE = new SimpleLanguage();

    private SimpleLanguage() {
        super("Simple");
    }
}
