package me.alanfoster.camelus.camel.converters;

import me.alanfoster.camelus.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelus.camel.dom.CamelBean;

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