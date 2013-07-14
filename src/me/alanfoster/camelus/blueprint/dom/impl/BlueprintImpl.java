package me.alanfoster.camelus.blueprint.dom.impl;

import me.alanfoster.camelus.blueprint.dom.Blueprint;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import me.alanfoster.camelus.blueprint.dom.BlueprintBeanPointer;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Provides the implementation of the helper methods within the blueprint interface
 * that are not explicitly DomElements.
 */
public abstract class BlueprintImpl implements Blueprint {
    /**
     * @see BlueprintBeanPointer
     */
    @Override
    @NotNull
    public List<? extends BlueprintBeanPointer> getBlueprintBeanPointers() {
        return getBeans();
    }
}
