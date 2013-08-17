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
//@SubTag("jaxb")
public interface JaxbDataFormat extends DataFormatDefinition, DomElement  {
            
        @Required
                @NotNull
        @Attribute("contextPath")
        GenericAttributeValue<String> getContextPath();
                    @NotNull
        @Attribute("prettyPrint")
        GenericAttributeValue<Boolean> getPrettyPrint();
                    @NotNull
        @Attribute("ignoreJAXBElement")
        GenericAttributeValue<Boolean> getIgnoreJAXBElement();
                    @NotNull
        @Attribute("filterNonXmlChars")
        GenericAttributeValue<Boolean> getFilterNonXmlChars();
                    @NotNull
        @Attribute("encoding")
        GenericAttributeValue<String> getEncoding();
                    @NotNull
        @Attribute("fragment")
        GenericAttributeValue<Boolean> getFragment();
                    @NotNull
        @Attribute("partClass")
        GenericAttributeValue<String> getPartClass();
                    @NotNull
        @Attribute("partNamespace")
        GenericAttributeValue<String> getPartNamespace();
    
    
    }