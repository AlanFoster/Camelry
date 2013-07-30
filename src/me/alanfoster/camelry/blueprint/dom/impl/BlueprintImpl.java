package me.alanfoster.camelry.blueprint.dom.impl;

import me.alanfoster.camelry.blueprint.dom.model.Blueprint;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBean;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintReference;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the implementation of the helper methods within the blueprint interface
 * that are not explicitly DomElements.
 *
 * @see me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer
 */
public abstract class BlueprintImpl implements Blueprint {
    /**
     * @see me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer
     */
    @Override
    @NotNull
    public List<BlueprintBeanPointer> getBlueprintBeanPointers() {
        List<BlueprintBeanPointer> pointers = new ArrayList<BlueprintBeanPointer>();
        for(BlueprintBean bean : getBeans()) {
            pointers.add(bean);
        }
        for(BlueprintReference reference : getReferences()){
            pointers.add(reference);
        }
        return pointers;
    }
}
