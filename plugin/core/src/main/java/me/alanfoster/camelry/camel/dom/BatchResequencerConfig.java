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
//@SubTag("batchResequencerConfig")
public interface BatchResequencerConfig extends ResequencerConfig, DomElement  {
                    @NotNull
        GenericAttributeValue<Integer> getBatchSize();
                    @NotNull
        GenericAttributeValue<Long> getBatchTimeout();
                    @NotNull
        GenericAttributeValue<Boolean> getAllowDuplicates();
                    @NotNull
        GenericAttributeValue<Boolean> getReverse();
                    @NotNull
        GenericAttributeValue<Boolean> getIgnoreInvalidExchanges();
    
    
    }