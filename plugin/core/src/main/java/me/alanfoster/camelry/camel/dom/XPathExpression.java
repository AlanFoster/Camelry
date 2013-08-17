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
//@SubTag("xpath")
public interface XPathExpression extends NamespaceAwareExpression, DomElement  {
                    @NotNull
        @Attribute("resultTypeName")
        GenericAttributeValue<String> getResultTypeName();
                    @NotNull
        @Attribute("saxon")
        GenericAttributeValue<Boolean> getSaxon();
                    @NotNull
        @Attribute("factoryRef")
        GenericAttributeValue<String> getFactoryRef();
                    @NotNull
        @Attribute("objectModel")
        GenericAttributeValue<String> getObjectModel();
                    @NotNull
        @Attribute("logNamespaces")
        GenericAttributeValue<Boolean> getLogNamespaces();
    
    
    }