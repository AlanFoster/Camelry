//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 12 August 2013
// Please do not manually modify this class.
//
package me.alanfoster.camelry.camel.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.*;
import java.util.List;


/**
 * @author Alan
 */
//@SubTag("description")
public interface DescriptionDefinition extends DomElement  {
                    @NotNull
        GenericAttributeValue<String> getLang();
                    @NotNull
        GenericAttributeValue<Double> getLayoutX();
                    @NotNull
        GenericAttributeValue<Double> getLayoutY();
                    @NotNull
        GenericAttributeValue<Double> getLayoutWidth();
                    @NotNull
        GenericAttributeValue<Double> getLayoutHeight();
    
    
    
                /**
         * Returns the current DomElement value.
         * Originally named as Text
         */
        @Required
        @NotNull
        String getValue();
        /**
         * Sets the current DomElement value.
         * Originally named as Text
         */
        String setValue();
    }