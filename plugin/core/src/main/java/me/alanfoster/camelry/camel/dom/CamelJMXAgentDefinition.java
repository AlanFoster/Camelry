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
//@SubTag("jmxAgent")
public interface CamelJMXAgentDefinition extends IdentifiedType, DomElement  {
                    @NotNull
        @Attribute("disabled")
        GenericAttributeValue<String> getDisabled();
                    @NotNull
        @Attribute("onlyRegisterProcessorWithCustomId")
        GenericAttributeValue<String> getOnlyRegisterProcessorWithCustomId();
                    @NotNull
        @Attribute("registryPort")
        GenericAttributeValue<String> getRegistryPort();
                    @NotNull
        @Attribute("connectorPort")
        GenericAttributeValue<String> getConnectorPort();
                    @NotNull
        @Attribute("mbeanServerDefaultDomain")
        GenericAttributeValue<String> getMbeanServerDefaultDomain();
                    @NotNull
        @Attribute("mbeanObjectDomainName")
        GenericAttributeValue<String> getMbeanObjectDomainName();
                    @NotNull
        @Attribute("serviceUrlPath")
        GenericAttributeValue<String> getServiceUrlPath();
                    @NotNull
        @Attribute("createConnector")
        GenericAttributeValue<String> getCreateConnector();
                    @NotNull
        @Attribute("usePlatformMBeanServer")
        GenericAttributeValue<String> getUsePlatformMBeanServer();
                    @NotNull
        @Attribute("registerAlways")
        GenericAttributeValue<String> getRegisterAlways();
                    @NotNull
        @Attribute("registerNewRoutes")
        GenericAttributeValue<String> getRegisterNewRoutes();
                    @NotNull
        @Attribute("statisticsLevel")
        GenericAttributeValue<ManagementStatisticsLevel> getStatisticsLevel();
    
    
    }