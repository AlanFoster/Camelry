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
//@SubTag("castor")
public interface CastorDataFormat extends DataFormatDefinition, DomElement  {
                    @NotNull
        @Attribute("mappingFile")
        GenericAttributeValue<String> getMappingFile();
                    @NotNull
        @Attribute("validation")
        GenericAttributeValue<Boolean> getValidation();
                    @NotNull
        @Attribute("encoding")
        GenericAttributeValue<String> getEncoding();
                    @NotNull
        @Attribute("packages")
        GenericAttributeValue<String> getPackages();
                    @NotNull
        @Attribute("classes")
        GenericAttributeValue<String> getClasses();
    
    
    }