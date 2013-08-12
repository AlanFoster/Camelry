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
//@SubTag("jaxb")
public interface JaxbDataFormat extends DataFormatDefinition, DomElement  {
            
        @Required
                @NotNull
        GenericAttributeValue<String> getContextPath();
                    @NotNull
        GenericAttributeValue<Boolean> getPrettyPrint();
                    @NotNull
        GenericAttributeValue<Boolean> getIgnoreJAXBElement();
                    @NotNull
        GenericAttributeValue<Boolean> getFilterNonXmlChars();
                    @NotNull
        GenericAttributeValue<String> getEncoding();
                    @NotNull
        GenericAttributeValue<Boolean> getFragment();
                    @NotNull
        GenericAttributeValue<String> getPartClass();
                    @NotNull
        GenericAttributeValue<String> getPartNamespace();
    
    
    }