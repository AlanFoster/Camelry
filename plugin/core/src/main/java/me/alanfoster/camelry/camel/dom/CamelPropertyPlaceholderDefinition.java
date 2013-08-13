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
//@SubTag("propertyPlaceholder")
public interface CamelPropertyPlaceholderDefinition extends IdentifiedType, DomElement  {
            
        @Required
                @NotNull
        GenericAttributeValue<String> getLocation();
                    @NotNull
        GenericAttributeValue<Boolean> getCache();
                    @NotNull
        GenericAttributeValue<Boolean> getIgnoreMissingLocation();
                    @NotNull
        GenericAttributeValue<String> getPropertiesResolverRef();
                    @NotNull
        GenericAttributeValue<String> getPropertiesParserRef();
                    @NotNull
        GenericAttributeValue<String> getPropertyPrefix();
                    @NotNull
        GenericAttributeValue<String> getPropertySuffix();
                    @NotNull
        GenericAttributeValue<Boolean> getFallbackToUnaugmentedProperty();
                    @NotNull
        GenericAttributeValue<String> getPrefixToken();
                    @NotNull
        GenericAttributeValue<String> getSuffixToken();
    
    
    }