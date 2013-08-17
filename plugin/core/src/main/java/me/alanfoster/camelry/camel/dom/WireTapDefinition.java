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
//@SubTag("wireTap")
public interface WireTapDefinition extends NoOutputDefinition, DomElement  {
                    @NotNull
        @Attribute("uri")
        GenericAttributeValue<String> getUri();
                    @NotNull
        @Attribute("ref")
        GenericAttributeValue<String> getRef();
                    @NotNull
        @Attribute("newExchangeProcessorRef")
        GenericAttributeValue<String> getNewExchangeProcessorRef();
                    @NotNull
        @Attribute("executorServiceRef")
        GenericAttributeValue<String> getExecutorServiceRef();
                    @NotNull
        @Attribute("copy")
        GenericAttributeValue<Boolean> getCopy();
                    @NotNull
        @Attribute("onPrepareRef")
        GenericAttributeValue<String> getOnPrepareRef();
    
        
        
                ExpressionSubElementDefinition getNewExchangeExpression();

        
                
        
        
        @SubTagsList({"setHeader"})
                List<SetHeaderDefinition> getHeaders();

        
        
                                            @SubTagList("setHeader")
                List<SetHeaderDefinition> getSetHeaders();
                        
    }