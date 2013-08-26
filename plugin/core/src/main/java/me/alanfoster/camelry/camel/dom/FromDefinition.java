//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 16 August 2013
// Please do not manually modify this class.
//
package me.alanfoster.camelry.camel.dom;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import me.alanfoster.camelry.camel.converters.ComponentDefinitionReferenceConverter;
import org.jetbrains.annotations.*;
import java.util.List;


/**
 * @author Alan
 */
//@SubTag("from")
public interface FromDefinition extends OptionalIdentifiedDefinition, DomElement  {
    @Attribute("uri")
    @Required
    @Convert(ComponentDefinitionReferenceConverter.class)
    public GenericAttributeValue<PsiClass> getUri();
                    @NotNull
        @Attribute("ref")
        GenericAttributeValue<String> getRef();
    
    
    }