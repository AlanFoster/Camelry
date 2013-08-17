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
//@SubTag("weighted")
public interface WeightedLoadBalancerDefinition extends LoadBalancerDefinition, DomElement  {
                    @NotNull
        @Attribute("roundRobin")
        GenericAttributeValue<Boolean> getRoundRobin();
            
        @Required
                @NotNull
        @Attribute("distributionRatio")
        GenericAttributeValue<String> getDistributionRatio();
                    @NotNull
        @Attribute("distributionRatioDelimiter")
        GenericAttributeValue<String> getDistributionRatioDelimiter();
    
    
    }