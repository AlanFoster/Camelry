package me.alanfoster.camelus.camel.dom;

import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.*;
import me.alanfoster.camelus.blueprint.dom.converters.BlueprintBeanPointerConverter;
import me.alanfoster.camelus.blueprint.dom.model.BlueprintBean;
import me.alanfoster.camelus.camel.converters.MethodMethodConverter;
import me.alanfoster.camelus.blueprint.dom.inspectors.DeprecatedAttribute;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//@Presentation(icon = PluginIcons.CAMEL_STRING)
public interface Method extends DomElement {
    @Attribute("bean")
    @DeprecatedAttribute(
            reason = "The 'bean' attribute is deprecated. You should make use of the 'ref' attribute instead",
            newName = "ref"
    )
    @Convert(BlueprintBeanPointerConverter.class)
    GenericAttributeValue<BlueprintBean> getBeanReference();

    @Attribute("ref")
    @Convert(BlueprintBeanPointerConverter.class)
    GenericAttributeValue<BlueprintBean> getRef();

    @Attribute("method")
    @Required(nonEmpty = true, value = true)
    @Convert(MethodMethodConverter.class)
    GenericAttributeValue<PsiMethod> getMethod();
}
