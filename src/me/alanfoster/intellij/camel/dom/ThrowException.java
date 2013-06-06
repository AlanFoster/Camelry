package me.alanfoster.intellij.camel.dom;

import com.intellij.util.xml.*;
import me.alanfoster.intellij.blueprint.converters.ThrowableBlueprintBeanConverter;
import me.alanfoster.intellij.blueprint.dom.BlueprintBean;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ThrowException extends DomElement {

    @Convert(ThrowableBlueprintBeanConverter.class)
    @Attribute("ref")
    @Required
    GenericAttributeValue<BlueprintBean> getRef();

}
