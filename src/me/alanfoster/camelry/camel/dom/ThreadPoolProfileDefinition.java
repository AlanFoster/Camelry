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
//@SubTag("threadPoolProfile")
public interface ThreadPoolProfileDefinition extends OptionalIdentifiedDefinition, DomElement  {
                    @NotNull
        GenericAttributeValue<Boolean> getDefaultProfile();
                    @NotNull
        GenericAttributeValue<String> getPoolSize();
                    @NotNull
        GenericAttributeValue<String> getMaxPoolSize();
                    @NotNull
        GenericAttributeValue<String> getKeepAliveTime();
                    @NotNull
        GenericAttributeValue<String> getMaxQueueSize();
                    @NotNull
        GenericAttributeValue<ThreadPoolRejectedPolicy> getRejectedPolicy();
    
        
        
/*                TimeUnit getTimeUnit();*/

        
            
    }