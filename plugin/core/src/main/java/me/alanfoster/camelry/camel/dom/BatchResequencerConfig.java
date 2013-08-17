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
//@SubTag("batchResequencerConfig")
public interface BatchResequencerConfig extends ResequencerConfig, DomElement  {
                    @NotNull
        @Attribute("batchSize")
        GenericAttributeValue<Integer> getBatchSize();
                    @NotNull
        @Attribute("batchTimeout")
        GenericAttributeValue<Long> getBatchTimeout();
                    @NotNull
        @Attribute("allowDuplicates")
        GenericAttributeValue<Boolean> getAllowDuplicates();
                    @NotNull
        @Attribute("reverse")
        GenericAttributeValue<Boolean> getReverse();
                    @NotNull
        @Attribute("ignoreInvalidExchanges")
        GenericAttributeValue<Boolean> getIgnoreInvalidExchanges();
    
    
    }