package me.alanfoster.camelus.blueprint.dom.impl;

import com.intellij.psi.PsiClass;
import me.alanfoster.camelus.blueprint.dom.model.BlueprintBean;
import org.jetbrains.annotations.Nullable;

/**
 * A concrete implementation of a blueprint bean.
 *
 * @see me.alanfoster.camelus.blueprint.dom.model.BlueprintBeanPointer
 */
public abstract class BlueprintBeanImpl implements BlueprintBean {
    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public PsiClass getReferencedClass() {
        return getClassAttribute().getValue();
    }
}