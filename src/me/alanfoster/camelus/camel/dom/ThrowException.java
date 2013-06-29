package me.alanfoster.camelus.camel.dom;

import com.intellij.util.xml.*;
import me.alanfoster.camelus.blueprint.inspectors.BlueprintBeanRefExtends;
import me.alanfoster.camelus.blueprint.converters.ThrowableBlueprintBeanConverter;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;

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
