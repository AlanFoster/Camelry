package me.alanfoster.camelus.camel.dom;

import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.*;
import me.alanfoster.camelus.blueprint.converters.BlueprintBeanConverter;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import me.alanfoster.camelus.camel.converters.CamelBeanMethodConverter;

/**
 * Represents the basic Bean element within a camel route.
 *
 * Example xml :
 * <pre>
 *     {@code
 *       <bean ref="foo" method="bar"/>
 *      }
 * </pre>
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface CamelBean extends DomElement {
    @NameValue
    @Attribute("ref")
    @Required(nonEmpty = true, value = true)
    @Convert(BlueprintBeanConverter.class)
    GenericAttributeValue<BlueprintBean> getRef();

    @Attribute("method")
    @Required(nonEmpty = true, value = true)
    @Convert(CamelBeanMethodConverter.class)
    GenericAttributeValue<PsiMethod> getMethod();

}
