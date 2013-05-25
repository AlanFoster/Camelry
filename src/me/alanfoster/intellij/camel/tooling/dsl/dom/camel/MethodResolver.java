package me.alanfoster.intellij.camel.tooling.dsl.dom.camel;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.converters.AbstractMethodParams;
import com.intellij.util.xml.converters.AbstractMethodResolveConverter;
import me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint.BlueprintBean;
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
 *     @Convert(MethodResolver.class)
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
// TODO This is very similar to PropertyResolver
public class MethodResolver extends AbstractMethodResolveConverter<Method> {

    public MethodResolver() {
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
        final BlueprintBean beanReference = parent.getBean().getValue();
        if (beanReference != null) {
            PsiClass beanClass = beanReference.getClassAttribute().getValue();
            if (beanClass != null) {
                return Arrays.asList(beanClass);
            }
        }
        return Collections.EMPTY_LIST;
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
