package me.alanfoster.intellij.camel.simple.language.tooling.highlighter;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import me.alanfoster.intellij.camel.simple.language.lexer.SimpleLexer;
import me.alanfoster.intellij.camel.simple.language.psi.SimpleTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SimpleSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey BRACKET = TextAttributesKey.createTextAttributesKey(
            "SIMPLE_BRACE",
            DefaultLanguageHighlighterColors.BRACES
    );

    public static final TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(
            "SIMPLE_NUMBER",
            new TextAttributes(Color.blue, null, null, null, Font.BOLD)
    );

    public static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(
            "SIMPLE_STRING",
            DefaultLanguageHighlighterColors.STRING
    );

    public static final TextAttributesKey ERROR = TextAttributesKey.createTextAttributesKey(
            "SIMPLE_ERROR",
            new TextAttributes(Color.red, null, null, null, Font.BOLD)
    );

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[]{STRING};
    //new TextAttributesKey[0];

    private static final Map<IElementType, TextAttributesKey> elementTypeAttributeKeyMap;

    static {
        elementTypeAttributeKeyMap = new HashMap<IElementType, TextAttributesKey>();

        // Non-simple, ie just normal text
        elementTypeAttributeKeyMap.put(SimpleTypes.NON_SIMPLE, DefaultLanguageHighlighterColors.STRING);
        elementTypeAttributeKeyMap.put(SimpleTypes.SINGLE_CHARACTER, DefaultLanguageHighlighterColors.STRING);

        // Simple
        elementTypeAttributeKeyMap.put(SimpleTypes.IDENTIFIER, HighlighterColors.TEXT);
        elementTypeAttributeKeyMap.put(SimpleTypes.NUMBER, NUMBER);

        elementTypeAttributeKeyMap.put(SimpleTypes.FUNCTION_START, DefaultLanguageHighlighterColors.BRACES);
        elementTypeAttributeKeyMap.put(SimpleTypes.FUNCTION_END, DefaultLanguageHighlighterColors.BRACES);

        elementTypeAttributeKeyMap.put(SimpleTypes.LEFTSQUARE, DefaultLanguageHighlighterColors.BRACKETS);
        elementTypeAttributeKeyMap.put(SimpleTypes.RIGHTSQUARE, DefaultLanguageHighlighterColors.BRACKETS);
        elementTypeAttributeKeyMap.put(SimpleTypes.DOT, HighlighterColors.TEXT);

        // Operators
        elementTypeAttributeKeyMap.put(SimpleTypes.EQUALS_EQUALS, DefaultLanguageHighlighterColors.OPERATION_SIGN);
        elementTypeAttributeKeyMap.put(SimpleTypes.NOT_EQUALS, DefaultLanguageHighlighterColors.OPERATION_SIGN);

        elementTypeAttributeKeyMap.put(SimpleTypes.GREATER, DefaultLanguageHighlighterColors.OPERATION_SIGN);
        elementTypeAttributeKeyMap.put(SimpleTypes.GREATER_OR_EQUAL, DefaultLanguageHighlighterColors.OPERATION_SIGN);

        elementTypeAttributeKeyMap.put(SimpleTypes.LESS, DefaultLanguageHighlighterColors.OPERATION_SIGN);
        elementTypeAttributeKeyMap.put(SimpleTypes.LESS_OR_EQUAL, DefaultLanguageHighlighterColors.OPERATION_SIGN);

        // Reserved words
        elementTypeAttributeKeyMap.put(SimpleTypes.TRUE, DefaultLanguageHighlighterColors.CONSTANT);
        elementTypeAttributeKeyMap.put(SimpleTypes.FALSE, DefaultLanguageHighlighterColors.CONSTANT);

        // Fall through cases
        elementTypeAttributeKeyMap.put(TokenType.WHITE_SPACE, DefaultLanguageHighlighterColors.CONSTANT);
        elementTypeAttributeKeyMap.put(SimpleTypes.CRLF, HighlighterColors.TEXT);
        elementTypeAttributeKeyMap.put(TokenType.BAD_CHARACTER, ERROR);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FlexAdapter(new SimpleLexer((Reader) null));
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType iElementType) {
        TextAttributesKey textAttributesKey = elementTypeAttributeKeyMap.get(iElementType);
        // Attempt to match an element from our hashmap of element type to colors
        // And if it doesn't exist, don't throw an error. Instead return the EMPTY_KEYS,
        // This is so the user doesn't get a horrible red exception shown to them as they type!
        if (textAttributesKey != null) {
            return pack(textAttributesKey);
        }
        return EMPTY_KEYS;
    }
}
