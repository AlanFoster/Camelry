package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.*;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface From extends DomElement {
    @Attribute("uri")
    @Required
    @Convert(ComponentDefinitionReferenceConverter.class)
    public GenericAttributeValue<PsiClass> getUri();
}
