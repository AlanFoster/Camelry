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
//@SubTag("AbstractClass")
public interface AbstractCamelRedeliveryPolicyFactoryBean extends AbstractCamelFactoryBean, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getMaximumRedeliveries();
                    @NotNull
        GenericAttributeValue<String> getRedeliveryDelay();
                    @NotNull
        GenericAttributeValue<String> getAsyncDelayedRedelivery();
                    @NotNull
        GenericAttributeValue<String> getBackOffMultiplier();
                    @NotNull
        GenericAttributeValue<String> getUseExponentialBackOff();
                    @NotNull
        GenericAttributeValue<String> getCollisionAvoidanceFactor();
                    @NotNull
        GenericAttributeValue<String> getUseCollisionAvoidance();
                    @NotNull
        GenericAttributeValue<String> getMaximumRedeliveryDelay();
                    @NotNull
        GenericAttributeValue<LoggingLevel> getRetriesExhaustedLogLevel();
                    @NotNull
        GenericAttributeValue<LoggingLevel> getRetryAttemptedLogLevel();
                    @NotNull
        GenericAttributeValue<String> getLogRetryAttempted();
                    @NotNull
        GenericAttributeValue<String> getLogStackTrace();
                    @NotNull
        GenericAttributeValue<String> getLogRetryStackTrace();
                    @NotNull
        GenericAttributeValue<String> getLogHandled();
                    @NotNull
        GenericAttributeValue<String> getLogContinued();
                    @NotNull
        GenericAttributeValue<String> getLogExhausted();
                    @NotNull
        GenericAttributeValue<String> getDisableRedelivery();
                    @NotNull
        GenericAttributeValue<String> getDelayPattern();
    
    
    }