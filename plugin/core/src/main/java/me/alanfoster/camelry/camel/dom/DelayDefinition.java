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
//@SubTag("delay")
public interface DelayDefinition extends ExpressionNode, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getExecutorServiceRef();
                    @NotNull
        GenericAttributeValue<Boolean> getAsyncDelayed();
                    @NotNull
        GenericAttributeValue<Boolean> getCallerRunsWhenRejected();
    
    
    }