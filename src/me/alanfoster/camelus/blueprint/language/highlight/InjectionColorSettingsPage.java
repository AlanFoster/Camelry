package me.alanfoster.camelus.blueprint.language.highlight;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import me.alanfoster.camelus.CamelusBundle;
import me.alanfoster.camelus.icons.PluginIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * Allows the user to edit the colors for the Blueprint Injection language
 * through the settings page.
 */
public class InjectionColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = {
        new AttributesDescriptor(CamelusBundle.message("camelus.blueprint.language.color.settings.description.starttoken"), InjectionSyntaxHighlighterColors.FUNCTION_START),
        new AttributesDescriptor(CamelusBundle.message("camelus.blueprint.language.color.settings.description.propertyname"), InjectionSyntaxHighlighterColors.PROPERTY_NAME),
        new AttributesDescriptor(CamelusBundle.message("camelus.blueprint.language.color.settings.description.endtoken"), InjectionSyntaxHighlighterColors.FUNCTION_END),
        new AttributesDescriptor(CamelusBundle.message("camelus.blueprint.language.color.settings.description.text"), InjectionSyntaxHighlighterColors.TEXT)
    };

    public static final String DEMO_TEXT =
            "${hello} ${world} " +
            "1234567890!\"£$%^&*()";

    @Nullable
    @Override
    public Icon getIcon() {
        return PluginIcons.BLUEPRINT;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new InjectionHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return DEMO_TEXT;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return CamelusBundle.message("camelus.blueprint.language.color.settings.title");
    }
}
