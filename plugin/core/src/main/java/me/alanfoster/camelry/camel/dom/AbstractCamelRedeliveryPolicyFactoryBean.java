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
//@SubTag("AbstractClass")
public interface AbstractCamelRedeliveryPolicyFactoryBean extends AbstractCamelFactoryBean, DomElement  {
                    @NotNull
        @Attribute("maximumRedeliveries")
        GenericAttributeValue<String> getMaximumRedeliveries();
                    @NotNull
        @Attribute("redeliveryDelay")
        GenericAttributeValue<String> getRedeliveryDelay();
                    @NotNull
        @Attribute("asyncDelayedRedelivery")
        GenericAttributeValue<String> getAsyncDelayedRedelivery();
                    @NotNull
        @Attribute("backOffMultiplier")
        GenericAttributeValue<String> getBackOffMultiplier();
                    @NotNull
        @Attribute("useExponentialBackOff")
        GenericAttributeValue<String> getUseExponentialBackOff();
                    @NotNull
        @Attribute("collisionAvoidanceFactor")
        GenericAttributeValue<String> getCollisionAvoidanceFactor();
                    @NotNull
        @Attribute("useCollisionAvoidance")
        GenericAttributeValue<String> getUseCollisionAvoidance();
                    @NotNull
        @Attribute("maximumRedeliveryDelay")
        GenericAttributeValue<String> getMaximumRedeliveryDelay();
                    @NotNull
        @Attribute("retriesExhaustedLogLevel")
        GenericAttributeValue<LoggingLevel> getRetriesExhaustedLogLevel();
                    @NotNull
        @Attribute("retryAttemptedLogLevel")
        GenericAttributeValue<LoggingLevel> getRetryAttemptedLogLevel();
                    @NotNull
        @Attribute("logRetryAttempted")
        GenericAttributeValue<String> getLogRetryAttempted();
                    @NotNull
        @Attribute("logStackTrace")
        GenericAttributeValue<String> getLogStackTrace();
                    @NotNull
        @Attribute("logRetryStackTrace")
        GenericAttributeValue<String> getLogRetryStackTrace();
                    @NotNull
        @Attribute("logHandled")
        GenericAttributeValue<String> getLogHandled();
                    @NotNull
        @Attribute("logContinued")
        GenericAttributeValue<String> getLogContinued();
                    @NotNull
        @Attribute("logExhausted")
        GenericAttributeValue<String> getLogExhausted();
                    @NotNull
        @Attribute("disableRedelivery")
        GenericAttributeValue<String> getDisableRedelivery();
                    @NotNull
        @Attribute("delayPattern")
        GenericAttributeValue<String> getDelayPattern();
    
    
    }