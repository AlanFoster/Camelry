package me.alanfoster.camelus.camel.dom;

import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.*;
import me.alanfoster.camelus.blueprint.converters.BlueprintBeanPointerConverter;
import me.alanfoster.camelus.blueprint.dom.BlueprintBeanPointer;
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
    @Attribute("ref")
    @Required(nonEmpty = true, value = true)
    @Convert(BlueprintBeanPointerConverter.class)
    GenericAttributeValue<BlueprintBeanPointer> getRef();

    @Attribute("method")
    @Required(nonEmpty = true, value = true)
    @Convert(CamelBeanMethodConverter.class)
    GenericAttributeValue<PsiMethod> getMethod();

}
