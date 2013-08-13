package me.alanfoster.camelry.camel.simple.language.psi;

import com.intellij.psi.tree.IElementType;
import me.alanfoster.camelry.camel.simple.language.SimpleLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an Element. IE, a top level element of a tree
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SimpleElementType extends IElementType {
    SimpleElementType(@NotNull @NonNls String debugName) {
        super(debugName, SimpleLanguage.INSTANCE);
    }
}
