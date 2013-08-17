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
//@SubTag("threadPoolProfile")
public interface ThreadPoolProfileDefinition extends OptionalIdentifiedDefinition, DomElement  {
                    @NotNull
        @Attribute("defaultProfile")
        GenericAttributeValue<Boolean> getDefaultProfile();
                    @NotNull
        @Attribute("poolSize")
        GenericAttributeValue<String> getPoolSize();
                    @NotNull
        @Attribute("maxPoolSize")
        GenericAttributeValue<String> getMaxPoolSize();
                    @NotNull
        @Attribute("keepAliveTime")
        GenericAttributeValue<String> getKeepAliveTime();
                    @NotNull
        @Attribute("maxQueueSize")
        GenericAttributeValue<String> getMaxQueueSize();
                    @NotNull
        @Attribute("rejectedPolicy")
        GenericAttributeValue<ThreadPoolRejectedPolicy> getRejectedPolicy();
    
        
        
                //TimeUnit getTimeUnit();

        
            
    }