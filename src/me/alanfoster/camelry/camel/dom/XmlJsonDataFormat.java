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
//@SubTag("xmljson")
public interface XmlJsonDataFormat extends DataFormatDefinition, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getEncoding();
                    @NotNull
        GenericAttributeValue<String> getElementName();
                    @NotNull
        GenericAttributeValue<String> getArrayName();
                    @NotNull
        GenericAttributeValue<Boolean> getForceTopLevelObject();
                    @NotNull
        GenericAttributeValue<Boolean> getNamespaceLenient();
                    @NotNull
        GenericAttributeValue<String> getRootName();
                    @NotNull
        GenericAttributeValue<Boolean> getSkipWhitespace();
                    @NotNull
        GenericAttributeValue<Boolean> getTrimSpaces();
                    @NotNull
        GenericAttributeValue<Boolean> getSkipNamespaces();
                    @NotNull
        GenericAttributeValue<Boolean> getRemoveNamespacePrefixes();
                    @NotNull
        GenericAttributeValue<String> getExpandableProperties();
                    @NotNull
        GenericAttributeValue<String> getTypeHints();
    
    
    }