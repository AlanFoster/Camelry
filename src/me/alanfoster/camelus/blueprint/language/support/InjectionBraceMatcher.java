package me.alanfoster.camelus.blueprint.language.support;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static me.alanfoster.camelus.blueprint.language.InjectionTypes.*;

/**
 * Brace matching definition for the Blueprint injection language.
 *
 * Note, this class allows the auto-closing of the blueprint FUNCTION_START
 * and FUNCTION_END elements - but this only appears to work if you register
 * a syntax highlighter too!
 *
 * @see me.alanfoster.camelus.blueprint.language.highlight.InjectionHighlighterFactory
 */
public class InjectionBraceMatcher implements PairedBraceMatcher {
    public static final BracePair[] BRACE_PAIRS = new BracePair[]{
            new BracePair(FUNCTION_START, FUNCTION_END, true)
    };

    @Override
    public BracePair[] getPairs() {
        return BRACE_PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
