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
//@SubTag("threads")
public interface ThreadsDefinition extends OutputDefinition, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getExecutorServiceRef();
                    @NotNull
        GenericAttributeValue<Integer> getPoolSize();
                    @NotNull
        GenericAttributeValue<Integer> getMaxPoolSize();
                    @NotNull
        GenericAttributeValue<Long> getKeepAliveTime();
                    @NotNull
        GenericAttributeValue<String> getTimeUnit();
                    @NotNull
        GenericAttributeValue<Integer> getMaxQueueSize();
                    @NotNull
        GenericAttributeValue<String> getThreadName();
                    @NotNull
        GenericAttributeValue<ThreadPoolRejectedPolicy> getRejectedPolicy();
                    @NotNull
        GenericAttributeValue<Boolean> getCallerRunsWhenRejected();
    
    
    }