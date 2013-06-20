package me.alanfoster.intellij.camel.converters;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.converters.AbstractMethodParams;
import com.intellij.util.xml.converters.AbstractMethodResolveConverter;
import me.alanfoster.intellij.blueprint.dom.BlueprintBean;
import me.alanfoster.intellij.camel.dom.Method;
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
 * Note The getVariants() method, which returns the list of variants
 * (for autosuggest etc) is handled by the base class, dependant on the
 * PsiClass returned on the overriden class (IE, this class)
 *
 * To use this, on a DomElement you will need to add it using the annotation
 * <pre>
 *     {@code
 *     @Convert(MethodResolver.class)
 *      }
 * </pre>
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
// TODO This is very similar to PropertyResolver
public class MethodMethodConverter extends AbstractMethodResolveConverter<Method> {

    public MethodMethodConverter() {
        super(Method.class);
    }

    /**
     *
     * @param parent
     * @param context
     * @return The class which this method references in the DOM.
     *          For example, a bean defined outside the scope of the camel context
     */
    @NotNull
    @Override
    protected Collection<PsiClass> getPsiClasses(Method parent, ConvertContext context) {
        final BlueprintBean beanReference = getBeanReference(parent);
        if (beanReference != null) {
            PsiClass beanClass = beanReference.getClassAttribute().getValue();
            if (beanClass != null) {
                return Arrays.asList(beanClass);
            }
        }
        return Collections.EMPTY_LIST;
    }

    // TODO I believe we can provide an abstract class which implements the Method interface and provide a concrete implementation for this method. This would be nice to do! <-- I believe you need to add 'dom.implementation' to the meta-inf file with both interfaceClass + implementationClass attributes
    private BlueprintBean getBeanReference(Method parent) {
        // The BlueprintBean reference can be in one of two places, either the 'ref' attribute or 'bean' (deprecated) attribute
        GenericAttributeValue<BlueprintBean> blueprintBeanWrapper =
                (parent.getRef().exists() ? parent.getRef(): parent.getBeanReference());

        return blueprintBeanWrapper.getValue();
    }


    /**
     * No method arguments can be specified in camel, so this
     * method returns null by default.
     *
     * @param parent
     * @return
     */
    @Nullable
    @Override
    protected AbstractMethodParams getMethodParams(@NotNull Method parent) {
        return null;
    }

    /**
     * This method returns any additional methods that should be on the
     * getVariants() list.
     *
     * Camel doesn't offer any additional methods to call on methods, so
     * this should return an empty set.
     *
     * @return An empty set.
     */
    public Set<String> getAdditionalVariants() {
        return Collections.EMPTY_SET;
    }
}
