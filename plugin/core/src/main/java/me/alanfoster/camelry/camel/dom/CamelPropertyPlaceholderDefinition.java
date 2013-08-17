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
//@SubTag("propertyPlaceholder")
public interface CamelPropertyPlaceholderDefinition extends IdentifiedType, DomElement  {
            
        @Required
                @NotNull
        @Attribute("location")
        GenericAttributeValue<String> getLocation();
                    @NotNull
        @Attribute("cache")
        GenericAttributeValue<Boolean> getCache();
                    @NotNull
        @Attribute("ignoreMissingLocation")
        GenericAttributeValue<Boolean> getIgnoreMissingLocation();
                    @NotNull
        @Attribute("propertiesResolverRef")
        GenericAttributeValue<String> getPropertiesResolverRef();
                    @NotNull
        @Attribute("propertiesParserRef")
        GenericAttributeValue<String> getPropertiesParserRef();
                    @NotNull
        @Attribute("propertyPrefix")
        GenericAttributeValue<String> getPropertyPrefix();
                    @NotNull
        @Attribute("propertySuffix")
        GenericAttributeValue<String> getPropertySuffix();
                    @NotNull
        @Attribute("fallbackToUnaugmentedProperty")
        GenericAttributeValue<Boolean> getFallbackToUnaugmentedProperty();
                    @NotNull
        @Attribute("prefixToken")
        GenericAttributeValue<String> getPrefixToken();
                    @NotNull
        @Attribute("suffixToken")
        GenericAttributeValue<String> getSuffixToken();
    
    
    }