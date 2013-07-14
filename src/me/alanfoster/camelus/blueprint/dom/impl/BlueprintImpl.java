package me.alanfoster.camelus.blueprint.dom.impl;

import me.alanfoster.camelus.blueprint.dom.Blueprint;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import me.alanfoster.camelus.blueprint.dom.BlueprintBeanPointer;
import me.alanfoster.camelus.blueprint.dom.BlueprintReference;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides the implementation of the helper methods within the blueprint interface
 * that are not explicitly DomElements.
 *
 * @see BlueprintBeanPointer
 */
public abstract class BlueprintImpl implements Blueprint {
    /**
     * @see BlueprintBeanPointer
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
