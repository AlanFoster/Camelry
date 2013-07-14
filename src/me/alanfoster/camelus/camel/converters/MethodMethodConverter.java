package me.alanfoster.camelus.camel.converters;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.converters.AbstractMethodParams;
import com.intellij.util.xml.converters.AbstractMethodResolveConverter;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import me.alanfoster.camelus.blueprint.dom.BlueprintBeanPointer;
import me.alanfoster.camelus.camel.dom.Method;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Handles the Method Resolving for references that link to Bean elements
 * defined with a blueprint XML file.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class MethodMethodConverter extends BeanPointerMethodConverter<Method> {

    public MethodMethodConverter() {
        super(Method.class);
    }

    @Nullable
    @Override
    public BlueprintBeanPointer getBlueprintBeanPointer(Method parent) {
        // TODO Abstract this logic within a DomImpl class
        // The BlueprintBean reference can be in one of two places, either the 'ref' attribute or 'bean' (deprecated) attribute
        GenericAttributeValue<BlueprintBean> blueprintBeanWrapper =
                (parent.getRef().exists() ? parent.getRef(): parent.getBeanReference());

        return blueprintBeanWrapper.getValue();
    }

}
