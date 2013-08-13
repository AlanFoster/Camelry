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
//@SubTag("log")
public interface LogDefinition extends NoOutputDefinition, DomElement  {
            
        @Required
                @NotNull
        GenericAttributeValue<String> getMessage();
                    @NotNull
        GenericAttributeValue<LoggingLevel> getLoggingLevel();
                    @NotNull
        GenericAttributeValue<String> getLogName();
                    @NotNull
        GenericAttributeValue<String> getMarker();
    
    
    }