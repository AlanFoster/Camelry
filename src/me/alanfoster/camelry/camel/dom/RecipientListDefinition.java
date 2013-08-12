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
//@SubTag("recipientList")
public interface RecipientListDefinition extends NoOutputExpressionNode, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getDelimiter();
                    @NotNull
        GenericAttributeValue<Boolean> getParallelProcessing();
                    @NotNull
        GenericAttributeValue<String> getStrategyRef();
                    @NotNull
        GenericAttributeValue<String> getExecutorServiceRef();
                    @NotNull
        GenericAttributeValue<Boolean> getStopOnException();
                    @NotNull
        GenericAttributeValue<Boolean> getIgnoreInvalidEndpoints();
                    @NotNull
        GenericAttributeValue<Boolean> getStreaming();
                    @NotNull
        GenericAttributeValue<Long> getTimeout();
                    @NotNull
        GenericAttributeValue<String> getOnPrepareRef();
                    @NotNull
        GenericAttributeValue<Boolean> getShareUnitOfWork();
    
    
    }