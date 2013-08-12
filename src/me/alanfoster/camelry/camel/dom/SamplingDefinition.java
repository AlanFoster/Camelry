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
//@SubTag("sample")
public interface SamplingDefinition extends OutputDefinition, DomElement  {
                    @NotNull
        GenericAttributeValue<Long> getSamplePeriod();
                    @NotNull
        GenericAttributeValue<Long> getMessageFrequency();
                    @NotNull
        GenericAttributeValue<String> getUnits();
    
    
    }