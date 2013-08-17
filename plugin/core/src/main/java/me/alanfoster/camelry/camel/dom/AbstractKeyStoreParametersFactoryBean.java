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
public interface AbstractKeyStoreParametersFactoryBean extends AbstractJsseUtilFactoryBean, DomElement  {
                    @NotNull
        @Attribute("type")
        GenericAttributeValue<String> getType();
                    @NotNull
        @Attribute("password")
        GenericAttributeValue<String> getPassword();
                    @NotNull
        @Attribute("provider")
        GenericAttributeValue<String> getProvider();
                    @NotNull
        @Attribute("resource")
        GenericAttributeValue<String> getResource();
    
    
    }