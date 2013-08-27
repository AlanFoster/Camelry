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
//@SubTag("camelContext")
public interface CamelContextFactoryBean extends AbstractCamelContextFactoryBean, DomElement  {
                    @NotNull
        @Attribute("dependsOn")
        GenericAttributeValue<String> getDependsOn();
                    @NotNull
        @Attribute("trace")
        GenericAttributeValue<String> getTrace();
                    @NotNull
        @Attribute("streamCache")
        GenericAttributeValue<String> getStreamCache();
                    @NotNull
        @Attribute("delayer")
        GenericAttributeValue<String> getDelayer();
                    @NotNull
        @Attribute("handleFault")
        GenericAttributeValue<String> getHandleFault();
                    @NotNull
        @Attribute("errorHandlerRef")
        GenericAttributeValue<String> getErrorHandlerRef();
                    @NotNull
        @Attribute("autoStartup")
        GenericAttributeValue<String> getAutoStartup();
                    @NotNull
        @Attribute("useMDCLogging")
        GenericAttributeValue<String> getUseMDCLogging();
                    @NotNull
        @Attribute("useBreadcrumb")
        GenericAttributeValue<String> getUseBreadcrumb();
                    @NotNull
        @Attribute("managementNamePattern")
        GenericAttributeValue<String> getManagementNamePattern();
                    @NotNull
        @Attribute("useBlueprintPropertyResolver")
        GenericAttributeValue<Boolean> getUseBlueprintPropertyResolver();
                    @NotNull
        @Attribute("shutdownRoute")
        GenericAttributeValue<ShutdownRoute> getShutdownRoute();
                    @NotNull
        @Attribute("shutdownRunningTask")
        GenericAttributeValue<ShutdownRunningTask> getShutdownRunningTask();
                    @NotNull
        @Attribute("lazyLoadTypeConverters")
        GenericAttributeValue<Boolean> getLazyLoadTypeConverters();
    
        
        
                PropertiesDefinition getProperties();

        
                
        
                CamelPropertyPlaceholderDefinition getCamelPropertyPlaceholder();

        
                
        
                String[] getPackages();

        
                
        
                PackageScanDefinition getPackageScan();

        
                
        
                ContextScanDefinition getContextScan();

        
                
        
                CamelJMXAgentDefinition getCamelJMXAgent();

        
                
        
                List<?> getBeans();

        
                
        
                List<RouteBuilderDefinition> getBuilderRefs();

        
                
        
                List<RouteContextRefDefinition> getRouteRefs();

        
                
        
                List<ThreadPoolProfileDefinition> getThreadPoolProfiles();

        
                
        
                List<CamelThreadPoolFactoryBean> getThreadPools();

        
                
        
                List<CamelEndpointFactoryBean> getEndpoints();

        
                
        
                DataFormatsDefinition getDataFormats();

        
                
        
                List<CamelRedeliveryPolicyFactoryBean> getRedeliveryPolicies();

        
                
                @SubTagList("onException")
                List<OnExceptionDefinition> getOnExceptions();

        
                
        
                List<OnCompletionDefinition> getOnCompletions();

        
                
        
                List<InterceptDefinition> getIntercepts();

        
                
        
                List<InterceptFromDefinition> getInterceptFroms();

        
                
        
                List<InterceptSendToEndpointDefinition> getInterceptSendToEndpoints();

        
                
        
                List<RouteDefinition> getRoutes();

        
            
    }