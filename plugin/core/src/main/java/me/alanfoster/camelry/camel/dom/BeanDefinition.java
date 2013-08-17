//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 16 August 2013
// Please do not manually modify this class.
//
package me.alanfoster.camelry.camel.dom;

import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.*;
import me.alanfoster.camelry.blueprint.dom.converters.BlueprintBeanPointerConverter;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelry.camel.converters.CamelBeanMethodConverter;
import org.jetbrains.annotations.NotNull;

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
public interface BeanDefinition extends DomElement {
    @Attribute("ref")
    @Required(nonEmpty = true, value = true)
    @Convert(BlueprintBeanPointerConverter.class)
    GenericAttributeValue<BlueprintBeanPointer> getRef();

    @Attribute("method")
    @Required(nonEmpty = true, value = true)
    @Convert(CamelBeanMethodConverter.class)
    GenericAttributeValue<PsiMethod> getMethod();

                    @NotNull
        @Attribute("beanType")
        GenericAttributeValue<String> getBeanType();


    }