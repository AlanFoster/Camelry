package me.alanfoster.intellij.camel.dom;

import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.*;
import me.alanfoster.intellij.blueprint.dom.BlueprintBean;

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
    @DeprecatedAttribute(
            reason = "The 'bean' attribute is deprecated. You should make use of the 'ref' attribute instead",
            newName = "ref"
    )
    GenericAttributeValue<BlueprintBean> getBeanReference();

    @Resolve
    @NameValue
    @Attribute("ref")
    GenericAttributeValue<BlueprintBean> getRef();

    @Attribute("method")
    @Required(nonEmpty = true, value = true)
    @Convert(MethodResolver.class)
    GenericAttributeValue<PsiMethod> getMethod();
}
