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
//@SubTag("sslContextParameters")
public interface SSLContextParametersFactoryBean extends AbstractJsseUtilFactoryBean, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getSessionTimeout();
                    @NotNull
        GenericAttributeValue<String> getProvider();
                    @NotNull
        GenericAttributeValue<String> getSecureSocketProtocol();
    
        
        
                CipherSuitesParametersDefinition getCipherSuites();

        
                
        
                FilterParametersDefinition getCipherSuitesFilter();

        
                
        
                SecureSocketProtocolsParametersDefinition getSecureSocketProtocols();

        
                
        
                FilterParametersDefinition getSecureSocketProtocolsFilter();

        
                
        
                KeyManagersParametersFactoryBean getKeyManagers();

        
                
        
                TrustManagersParametersFactoryBean getTrustManagers();

        
                
        
                SecureRandomParametersFactoryBean getSecureRandom();

        
                
        
                SSLContextClientParametersFactoryBean getClientParameters();

        
                
        
                SSLContextServerParametersFactoryBean getServerParameters();

        
            
    }