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
//@SubTag("tokenize")
public interface TokenizerExpression extends ExpressionDefinition, DomElement  {
            
        @Required
                @NotNull
        @Attribute("token")
        GenericAttributeValue<String> getToken();
                    @NotNull
        @Attribute("endToken")
        GenericAttributeValue<String> getEndToken();
                    @NotNull
        @Attribute("inheritNamespaceTagName")
        GenericAttributeValue<String> getInheritNamespaceTagName();
                    @NotNull
        @Attribute("headerName")
        GenericAttributeValue<String> getHeaderName();
                    @NotNull
        @Attribute("regex")
        GenericAttributeValue<Boolean> getRegex();
                    @NotNull
        @Attribute("xml")
        GenericAttributeValue<Boolean> getXml();
                    @NotNull
        @Attribute("includeTokens")
        GenericAttributeValue<Boolean> getIncludeTokens();
                    @NotNull
        @Attribute("group")
        GenericAttributeValue<Integer> getGroup();
    
    
    }