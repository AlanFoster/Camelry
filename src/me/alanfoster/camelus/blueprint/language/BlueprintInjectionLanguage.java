package me.alanfoster.camelus.blueprint.language;

import com.intellij.lang.Language;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BlueprintInjectionLanguage extends Language {
    public static final BlueprintInjectionLanguage INSTANCE = new BlueprintInjectionLanguage();
    public static final String BLUEPRINT_INJECTION_LANGUAGE = "BlueprintInjectionLanguage";

    private BlueprintInjectionLanguage() {
        super(BLUEPRINT_INJECTION_LANGUAGE);
    }
}
