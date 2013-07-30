package me.alanfoster.camelry.blueprint.language.highlight;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import me.alanfoster.camelry.blueprint.language.lexer.InjectionLexer;
import me.alanfoster.camelus.blueprint.language.InjectionTypes;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Adds syntax highlighting for the Blueprint injection language.
 */
public class InjectionHighlighter extends SyntaxHighlighterBase {
    /**
     * Maintains an internal map of tokens to colors, for a fast lookup time.
     */
    private static final Map<IElementType, TextAttributesKey> elementTypeAttributeKeyMap;

    static {
        elementTypeAttributeKeyMap = new HashMap<IElementType, TextAttributesKey>();

        elementTypeAttributeKeyMap.put(InjectionTypes.FUNCTION_START, InjectionSyntaxHighlighterColors.FUNCTION_START);
        elementTypeAttributeKeyMap.put(InjectionTypes.PROPERTY_NAME, InjectionSyntaxHighlighterColors.PROPERTY_NAME);
        elementTypeAttributeKeyMap.put(InjectionTypes.FUNCTION_END, InjectionSyntaxHighlighterColors.FUNCTION_END);
        elementTypeAttributeKeyMap.put(InjectionTypes.TEXT, InjectionSyntaxHighlighterColors.TEXT);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FlexAdapter(new InjectionLexer((Reader) null));
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(elementTypeAttributeKeyMap.get(tokenType));
    }
}
