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
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBean;
import me.alanfoster.camelry.camel.converters.MethodMethodConverter;
import me.alanfoster.camelry.blueprint.dom.inspectors.DeprecatedAttribute;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//@Presentation(icon = PluginIcons.CAMEL_STRING)
public interface MethodCallExpression extends ExpressionDefinition, DomElement  {
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

                    @NotNull
        @Attribute("beanTypeName")
        GenericAttributeValue<String> getBeanTypeName();


    }