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
//@SubTag("errorHandler")
public interface CamelErrorHandlerFactoryBean extends AbstractCamelFactoryBean, DomElement  {
                    @NotNull
        @Attribute("type")
        GenericAttributeValue<ErrorHandlerType> getType();
                    @NotNull
        @Attribute("deadLetterUri")
        GenericAttributeValue<String> getDeadLetterUri();
                    @NotNull
        @Attribute("level")
        GenericAttributeValue<LoggingLevel> getLevel();
                    @NotNull
        @Attribute("logName")
        GenericAttributeValue<String> getLogName();
                    @NotNull
        @Attribute("useOriginalMessage")
        GenericAttributeValue<Boolean> getUseOriginalMessage();
                    @NotNull
        @Attribute("onRedeliveryRef")
        GenericAttributeValue<String> getOnRedeliveryRef();
                    @NotNull
        @Attribute("retryWhileRef")
        GenericAttributeValue<String> getRetryWhileRef();
                    @NotNull
        @Attribute("executorServiceRef")
        GenericAttributeValue<String> getExecutorServiceRef();
                    @NotNull
        @Attribute("redeliveryPolicyRef")
        GenericAttributeValue<String> getRedeliveryPolicyRef();
    
        
        
                RedeliveryPolicyDefinition getRedeliveryPolicy();

        
            
    }