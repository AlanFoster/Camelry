package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.*;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface Method extends DomElement {
    //  If you still want to specify explicitly that your reference to DomElement should be resolved "model-wide",
    // use the @Resolve annotation parameterized with the desired class. The resolution scope will be taken from the
    // DomFileDescription.getResolveScope().
    @Resolve
    @NameValue
    @Attribute("bean")
    @Required
    GenericAttributeValue<Bean> getBean();

    @Attribute("method")
    @Required
    GenericAttributeValue<String> getMethod();
}
