package me.alanfoster.camelry.camel.converters;

import me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelry.camel.dom.CamelBean;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class CamelBeanMethodConverter extends BeanPointerMethodConverter<CamelBean> {

    public CamelBeanMethodConverter() {
        super(CamelBean.class);
    }

    @Override
    public BlueprintBeanPointer getBlueprintBeanPointer(CamelBean parent) {
        return parent.getRef().getValue();
    }

}