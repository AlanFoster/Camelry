package me.alanfoster.intellij.camel.dom;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.*;
import me.alanfoster.intellij.camel.tooling.dsl.dom.ComponentDefinitionReferenceConverter;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface To extends DomElement {
    @Attribute("uri")
    @Required
    @Convert(ComponentDefinitionReferenceConverter.class)
    public GenericAttributeValue<PsiClass> getUri();
}
