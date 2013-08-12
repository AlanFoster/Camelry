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
//@SubTag("wireTap")
public interface WireTapDefinition extends NoOutputDefinition, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getUri();
                    @NotNull
        GenericAttributeValue<String> getRef();
                    @NotNull
        GenericAttributeValue<String> getNewExchangeProcessorRef();
                    @NotNull
        GenericAttributeValue<String> getExecutorServiceRef();
                    @NotNull
        GenericAttributeValue<Boolean> getCopy();
                    @NotNull
        GenericAttributeValue<String> getOnPrepareRef();
    
        
        
                ExpressionSubElementDefinition getNewExchangeExpression();

        
                
        
        
        @SubTagsList({"setHeader"})
                List<SetHeaderDefinition> getHeaders();

        
        
                                            @SubTagList("setHeader")
                List<SetHeaderDefinition> getSetHeaders();
                        
    }