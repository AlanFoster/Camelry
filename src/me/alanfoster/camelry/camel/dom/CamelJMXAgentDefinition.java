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
//@SubTag("jmxAgent")
public interface CamelJMXAgentDefinition extends IdentifiedType, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getDisabled();
                    @NotNull
        GenericAttributeValue<String> getOnlyRegisterProcessorWithCustomId();
                    @NotNull
        GenericAttributeValue<String> getRegistryPort();
                    @NotNull
        GenericAttributeValue<String> getConnectorPort();
                    @NotNull
        GenericAttributeValue<String> getMbeanServerDefaultDomain();
                    @NotNull
        GenericAttributeValue<String> getMbeanObjectDomainName();
                    @NotNull
        GenericAttributeValue<String> getServiceUrlPath();
                    @NotNull
        GenericAttributeValue<String> getCreateConnector();
                    @NotNull
        GenericAttributeValue<String> getUsePlatformMBeanServer();
                    @NotNull
        GenericAttributeValue<String> getRegisterAlways();
                    @NotNull
        GenericAttributeValue<String> getRegisterNewRoutes();
                    @NotNull
        GenericAttributeValue<ManagementStatisticsLevel> getStatisticsLevel();
    
    
    }