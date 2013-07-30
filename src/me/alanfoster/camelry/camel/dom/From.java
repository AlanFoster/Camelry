package me.alanfoster.camelry.camel.dom;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.*;
import me.alanfoster.camelry.camel.converters.ComponentDefinitionReferenceConverter;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface From extends DomElement {
    @Attribute("uri")
    @Required
    @Convert(value = ComponentDefinitionReferenceConverter.class, soft = true)
    public GenericAttributeValue<PsiClass> getUri();
}
