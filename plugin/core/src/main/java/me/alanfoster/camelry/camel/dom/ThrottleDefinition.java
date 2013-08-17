//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 16 August 2013
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
//@SubTag("throttle")
public interface ThrottleDefinition extends ExpressionNode, DomElement  {
                    @NotNull
        @Attribute("executorServiceRef")
        GenericAttributeValue<String> getExecutorServiceRef();
                    @NotNull
        @Attribute("timePeriodMillis")
        GenericAttributeValue<Long> getTimePeriodMillis();
                    @NotNull
        @Attribute("asyncDelayed")
        GenericAttributeValue<Boolean> getAsyncDelayed();
                    @NotNull
        @Attribute("callerRunsWhenRejected")
        GenericAttributeValue<Boolean> getCallerRunsWhenRejected();
    
    
    }