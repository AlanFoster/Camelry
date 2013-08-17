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
//@SubTag("recipientList")
public interface RecipientListDefinition extends NoOutputExpressionNode, DomElement  {
                    @NotNull
        @Attribute("delimiter")
        GenericAttributeValue<String> getDelimiter();
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
        @Attribute("stopOnException")
        GenericAttributeValue<Boolean> getStopOnException();
                    @NotNull
        @Attribute("ignoreInvalidEndpoints")
        GenericAttributeValue<Boolean> getIgnoreInvalidEndpoints();
                    @NotNull
        @Attribute("streaming")
        GenericAttributeValue<Boolean> getStreaming();
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