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
//@SubTag("camelContext")
public interface CamelContextFactoryBean extends AbstractCamelContextFactoryBean, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getDependsOn();
                    @NotNull
        GenericAttributeValue<String> getTrace();
                    @NotNull
        GenericAttributeValue<String> getStreamCache();
                    @NotNull
        GenericAttributeValue<String> getDelayer();
                    @NotNull
        GenericAttributeValue<String> getHandleFault();
                    @NotNull
        GenericAttributeValue<String> getErrorHandlerRef();
                    @NotNull
        GenericAttributeValue<String> getAutoStartup();
                    @NotNull
        GenericAttributeValue<String> getUseMDCLogging();
                    @NotNull
        GenericAttributeValue<String> getUseBreadcrumb();
                    @NotNull
        GenericAttributeValue<String> getManagementNamePattern();
                    @NotNull
        GenericAttributeValue<Boolean> getUseBlueprintPropertyResolver();
                    @NotNull
        GenericAttributeValue<ShutdownRoute> getShutdownRoute();
                    @NotNull
        GenericAttributeValue<ShutdownRunningTask> getShutdownRunningTask();
                    @NotNull
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

        
                
        
                List<OnExceptionDefinition> getOnExceptions();

        
                
        
                List<OnCompletionDefinition> getOnCompletions();

        
                
        
                List<InterceptDefinition> getIntercepts();

        
                
        
                List<InterceptFromDefinition> getInterceptFroms();

        
                
        
                List<InterceptSendToEndpointDefinition> getInterceptSendToEndpoints();

        
                
        
                List<RouteDefinition> getRoutes();

        
            
    }