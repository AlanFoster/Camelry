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
//@SubTag("xstream")
public interface XStreamDataFormat extends DataFormatDefinition, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getEncoding();
                    @NotNull
        GenericAttributeValue<String> getDriver();
                    @NotNull
        GenericAttributeValue<String> getDriverRef();
    
        
        
                List<String> getConverters();

        
/*
        
                Map<String, String> getAliases();

        
                
        
                Map<String, String[]> getOmitFields();

        
                
        
                Map<String, String[]> getImplicitCollections();*/

        
            
    }