package me.alanfoster.intellij.camel.simple.language.psi
import com.intellij.psi.tree.IElementType
import me.alanfoster.intellij.camel.simple.language.SimpleLanguage
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.NotNull

/**
 * Represents a leaf in a tree
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
class SimpleTokenType extends IElementType {
    SimpleTokenType(@NotNull @NonNls String debugName) {
        super(debugName, SimpleLanguage.INSTANCE)
    }
}
