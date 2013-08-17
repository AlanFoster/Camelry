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
//@SubTag("export")
public interface CamelServiceExporterDefinition extends IdentifiedType, DomElement  {
                    @NotNull
        @Attribute("uri")
        GenericAttributeValue<String> getUri();
                    @NotNull
        @Attribute("serviceRef")
        GenericAttributeValue<String> getServiceRef();
                    @NotNull
        @Attribute("serviceInterface")
        GenericAttributeValue<Class> getServiceInterface();
                    @NotNull
        @Attribute("camelContextId")
        GenericAttributeValue<String> getCamelContextId();
    
    
    }