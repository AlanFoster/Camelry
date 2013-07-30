package me.alanfoster.camelry.blueprint.dom.impl;

import com.intellij.psi.PsiClass;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintReference;
import org.jetbrains.annotations.Nullable;

/**
 * @see me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer
 * @see BlueprintBeanImpl
 */
public abstract class BlueprintReferenceImpl implements BlueprintReference {
    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public PsiClass getReferencedClass() {
        return getInterface().getValue();
    }
}
