//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 12 August 2013
// Please do not manually modify this class.
//
package me.alanfoster.camelry.camel.dom;

import com.intellij.util.xml.*;
import me.alanfoster.camelry.blueprint.dom.converters.ThrowableBlueprintBeanConverter;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelry.blueprint.dom.inspectors.BlueprintBeanRefExtends;

/**
 * @author Alan
 */
//@SubTag("throwException")
public interface ThrowExceptionDefinition extends NoOutputDefinition, DomElement  {

    @BlueprintBeanRefExtends(Throwable.class)
    @Convert(ThrowableBlueprintBeanConverter.class)
    @Attribute("ref")
    @Required
    GenericAttributeValue<BlueprintBeanPointer> getRef();

}
