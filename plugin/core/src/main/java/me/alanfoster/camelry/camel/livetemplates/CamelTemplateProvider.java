package me.alanfoster.camelry.camel.livetemplates;

import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider;
import org.jetbrains.annotations.Nullable;

/**
 * Provides a set of useful live templates for apache camel. These can be invoked via the normal methods of
 * ctrl+j/ctrl+alt+j where appropriate.
 */
public class CamelTemplateProvider implements DefaultLiveTemplatesProvider {
    private static final String[] TEMPLATES = {
      "/liveTemplates/CamelTemplates"
    };

    @Override
    public String[] getDefaultLiveTemplateFiles() {
        return TEMPLATES;
    }

    @Nullable
    @Override
    public String[] getHiddenLiveTemplateFiles() {
        return null;
    }
}
