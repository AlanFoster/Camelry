package me.alanfoster.camelry.camel.converters;

import me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelry.camel.dom.BeanDefinition;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class CamelBeanMethodConverter extends BeanPointerMethodConverter<BeanDefinition> {

    public CamelBeanMethodConverter() {
        super(BeanDefinition.class);
    }

    @Override
    public BlueprintBeanPointer getBlueprintBeanPointer(BeanDefinition parent) {
        return parent.getRef().getValue();
    }

}