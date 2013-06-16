package me.alanfoster.intellij.blueprint.language.psi;

import com.intellij.psi.tree.IElementType;
import me.alanfoster.intellij.blueprint.language.BlueprintInjectionLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class InjectionElementType extends IElementType {
    public InjectionElementType(@NotNull @NonNls String debugName) {
        super(debugName, BlueprintInjectionLanguage.INSTANCE);
    }
}
