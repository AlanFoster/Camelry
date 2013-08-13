//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 12 August 2013
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
//@SubTag("AbstractClass")
public interface SendDefinition extends NoOutputDefinition, DomElement  {
    @Attribute("uri")
    @Required
    @Convert(value = ComponentDefinitionReferenceConverter.class, soft = true)
    public GenericAttributeValue<PsiClass> getUri();

                    @NotNull
        GenericAttributeValue<String> getRef();

    
}