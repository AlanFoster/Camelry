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
//@SubTag("csv")
public interface CsvDataFormat extends DataFormatDefinition, DomElement  {
                    @NotNull
        @Attribute("autogenColumns")
        GenericAttributeValue<Boolean> getAutogenColumns();
                    @NotNull
        @Attribute("delimiter")
        GenericAttributeValue<String> getDelimiter();
                    @NotNull
        @Attribute("configRef")
        GenericAttributeValue<String> getConfigRef();
                    @NotNull
        @Attribute("strategyRef")
        GenericAttributeValue<String> getStrategyRef();
                    @NotNull
        @Attribute("skipFirstLine")
        GenericAttributeValue<Boolean> getSkipFirstLine();
    
    
    }