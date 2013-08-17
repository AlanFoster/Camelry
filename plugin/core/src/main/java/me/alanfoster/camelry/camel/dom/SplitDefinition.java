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
//@SubTag("split")
public interface SplitDefinition extends ExpressionNode, DomElement  {
                    @NotNull
        @Attribute("parallelProcessing")
        GenericAttributeValue<Boolean> getParallelProcessing();
                    @NotNull
        @Attribute("strategyRef")
        GenericAttributeValue<String> getStrategyRef();
                    @NotNull
        @Attribute("executorServiceRef")
        GenericAttributeValue<String> getExecutorServiceRef();
                    @NotNull
        @Attribute("streaming")
        GenericAttributeValue<Boolean> getStreaming();
                    @NotNull
        @Attribute("stopOnException")
        GenericAttributeValue<Boolean> getStopOnException();
                    @NotNull
        @Attribute("timeout")
        GenericAttributeValue<Long> getTimeout();
                    @NotNull
        @Attribute("onPrepareRef")
        GenericAttributeValue<String> getOnPrepareRef();
                    @NotNull
        @Attribute("shareUnitOfWork")
        GenericAttributeValue<Boolean> getShareUnitOfWork();
    
    
    }