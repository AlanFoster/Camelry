package me.alanfoster.intellij.camel.tooling.dsl.dom.camel;

import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.*;
import me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint.BlueprintBean;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//@Presentation(icon = CamelIcons.CAMEL_STRING)
public interface Method extends DomElement {
    //  If you still want to specify explicitly that your reference to DomElement should be resolved "model-wide",
    // use the @Resolve annotation parameterized with the desired class. The resolution scope will be taken from the
    // DomFileDescription.getResolveScope().
    @Resolve
    @NameValue
    @Attribute("bean")
    @Required(nonEmpty = true, value = true)
    GenericAttributeValue<BlueprintBean> getBean();

    @Resolve
    @NameValue
    @Attribute("ref")
    @Required(nonEmpty = true, value = true)
    GenericAttributeValue<BlueprintBean> getRef();

    @Attribute("method")
    @Required(nonEmpty = true, value = true)
    @Convert(MethodResolver.class)
    GenericAttributeValue<PsiMethod> getMethod();
}
