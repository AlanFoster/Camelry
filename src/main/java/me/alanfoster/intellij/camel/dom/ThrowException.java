package me.alanfoster.intellij.camel.dom;

import com.intellij.util.xml.*;
import me.alanfoster.intellij.blueprint.converters.ThrowableBlueprintBeanConverter;
import me.alanfoster.intellij.blueprint.dom.BlueprintBean;
import me.alanfoster.intellij.blueprint.inspectors.BlueprintBeanRefExtends;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface ThrowException extends DomElement {

    @BlueprintBeanRefExtends(Throwable.class)
    @Convert(ThrowableBlueprintBeanConverter.class)
    @Attribute("ref")
    @Required
    GenericAttributeValue<BlueprintBean> getRef();

}
