package me.alanfoster.camelry.blueprint.language.highlight;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

/**
 * Contains the syntax coloring for the Blueprint Injection Language.
 *
 * @see InjectionColorSettingsPage
 */
public class InjectionSyntaxHighlighterColors {

    public static final TextAttributesKey FUNCTION_START = DefaultLanguageHighlighterColors.PARENTHESES;
    public static final TextAttributesKey PROPERTY_NAME = DefaultLanguageHighlighterColors.IDENTIFIER;
    public static final TextAttributesKey FUNCTION_END = DefaultLanguageHighlighterColors.PARENTHESES;
    public static final TextAttributesKey TEXT = DefaultLanguageHighlighterColors.STRING;

}
