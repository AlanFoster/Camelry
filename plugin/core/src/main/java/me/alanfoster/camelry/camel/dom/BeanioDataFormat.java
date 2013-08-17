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
//@SubTag("beanio")
public interface BeanioDataFormat extends DataFormatDefinition, DomElement  {
            
        @Required
                @NotNull
        @Attribute("mapping")
        GenericAttributeValue<String> getMapping();
            
        @Required
                @NotNull
        @Attribute("streamName")
        GenericAttributeValue<String> getStreamName();
                    @NotNull
        @Attribute("ignoreUnidentifiedRecords")
        GenericAttributeValue<Boolean> getIgnoreUnidentifiedRecords();
                    @NotNull
        @Attribute("ignoreUnexpectedRecords")
        GenericAttributeValue<Boolean> getIgnoreUnexpectedRecords();
                    @NotNull
        @Attribute("ignoreInvalidRecords")
        GenericAttributeValue<Boolean> getIgnoreInvalidRecords();
                    @NotNull
        @Attribute("encoding")
        GenericAttributeValue<String> getEncoding();
    
    
    }