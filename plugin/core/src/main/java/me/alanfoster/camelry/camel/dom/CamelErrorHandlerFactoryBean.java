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
//@SubTag("errorHandler")
public interface CamelErrorHandlerFactoryBean extends AbstractCamelFactoryBean, DomElement  {
                    @NotNull
        GenericAttributeValue<ErrorHandlerType> getType();
                    @NotNull
        GenericAttributeValue<String> getDeadLetterUri();
                    @NotNull
        GenericAttributeValue<LoggingLevel> getLevel();
                    @NotNull
        GenericAttributeValue<String> getLogName();
                    @NotNull
        GenericAttributeValue<Boolean> getUseOriginalMessage();
                    @NotNull
        GenericAttributeValue<String> getOnRedeliveryRef();
                    @NotNull
        GenericAttributeValue<String> getRetryWhileRef();
                    @NotNull
        GenericAttributeValue<String> getExecutorServiceRef();
                    @NotNull
        GenericAttributeValue<String> getRedeliveryPolicyRef();
    
        
        
                RedeliveryPolicyDefinition getRedeliveryPolicy();

        
            
    }