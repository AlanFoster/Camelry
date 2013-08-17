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
//@SubTag("xmljson")
public interface XmlJsonDataFormat extends DataFormatDefinition, DomElement  {
                    @NotNull
        @Attribute("encoding")
        GenericAttributeValue<String> getEncoding();
                    @NotNull
        @Attribute("elementName")
        GenericAttributeValue<String> getElementName();
                    @NotNull
        @Attribute("arrayName")
        GenericAttributeValue<String> getArrayName();
                    @NotNull
        @Attribute("forceTopLevelObject")
        GenericAttributeValue<Boolean> getForceTopLevelObject();
                    @NotNull
        @Attribute("namespaceLenient")
        GenericAttributeValue<Boolean> getNamespaceLenient();
                    @NotNull
        @Attribute("rootName")
        GenericAttributeValue<String> getRootName();
                    @NotNull
        @Attribute("skipWhitespace")
        GenericAttributeValue<Boolean> getSkipWhitespace();
                    @NotNull
        @Attribute("trimSpaces")
        GenericAttributeValue<Boolean> getTrimSpaces();
                    @NotNull
        @Attribute("skipNamespaces")
        GenericAttributeValue<Boolean> getSkipNamespaces();
                    @NotNull
        @Attribute("removeNamespacePrefixes")
        GenericAttributeValue<Boolean> getRemoveNamespacePrefixes();
                    @NotNull
        @Attribute("expandableProperties")
        GenericAttributeValue<String> getExpandableProperties();
                    @NotNull
        @Attribute("typeHints")
        GenericAttributeValue<String> getTypeHints();
    
    
    }