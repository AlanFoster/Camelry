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
//@SubTag("expressionDefinition")
public interface ExpressionDefinition extends DomElement  {
                    @NotNull
        GenericAttributeValue<String> getId();
                    @NotNull
        GenericAttributeValue<Boolean> getTrim();
    
    
    
                /**
         * Returns the current DomElement value.
         * Originally named as Expression
         */
        @Required
        @NotNull
        String getValue();
        /**
         * Sets the current DomElement value.
         * Originally named as Expression
         */
        String setValue();
    }