package me.alanfoster.camelry.camel.converters;

import com.intellij.util.xml.GenericAttributeValue;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBean;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelry.camel.dom.MethodCallExpression;
import org.jetbrains.annotations.Nullable;

/**
 * Handles the Method Resolving for references that link to Bean elements
 * defined with a blueprint XML file.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class MethodMethodConverter extends BeanPointerMethodConverter<MethodCallExpression> {

    public MethodMethodConverter() {
        super(MethodCallExpression.class);
    }

    @Nullable
    @Override
    public BlueprintBeanPointer getBlueprintBeanPointer(MethodCallExpression parent) {
        // TODO Abstract this logic within a DomImpl class
        // The BlueprintBean reference can be in one of two places, either the 'ref' attribute or 'bean' (deprecated) attribute
        GenericAttributeValue<BlueprintBean> blueprintBeanWrapper =
                (parent.getRef().exists() ? parent.getRef(): parent.getBeanReference());

        return blueprintBeanWrapper.getValue();
    }

}
