package me.alanfoster.camelry.camel.converters;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.converters.AbstractMethodParams;
import com.intellij.util.xml.converters.AbstractMethodResolveConverter;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Allows for intelliense + method resolving of the given BlueprintBeanPointer reference.
 * All inheriting classes should implement the getBlueprintBeanPointer method to
 * automatically resolve both of these requirements.
 *
 * Note The getVariants() method, which returns the list of variants
 * (for autosuggest etc) is handled by the base class, dependant on the
 * PsiClass returned on the overriden class (IE, this class)
 *
 * To use this class, extend this class, then attach a reference to your new class.
 * <pre>
 *     {@code
 *     @Convert(YourExtendingClass.class)
 *      }
 * </pre>
 *
 */
public abstract class BeanPointerMethodConverter<T extends DomElement> extends AbstractMethodResolveConverter<T> {

    protected BeanPointerMethodConverter(Class<T> domMethodClass) {
        super(domMethodClass);
    }

    /**
     * {@inheritDoc}
     **/
    @NotNull
    @Override
    protected Collection<PsiClass> getPsiClasses(T parent, ConvertContext context) {
        final BlueprintBeanPointer beanReferencePointer = getBlueprintBeanPointer(parent);
        if (beanReferencePointer == null) return Collections.emptyList();

        PsiClass beanClass = beanReferencePointer.getReferencedClass();
        if (beanClass == null) return Collections.emptyList();

        return Arrays.asList(beanClass);
    }

    /**
     * @return The blueprint bean pointer that this DomElement references
     */
    @Nullable
    public abstract BlueprintBeanPointer getBlueprintBeanPointer(T parent);

    /**
     * This method returns any additional methods that should be on the
     * getVariants() list.
     * <p/>
     * Camel doesn't offer any additional methods to call on methods, so
     * this should return an empty set.
     *
     * @return An empty set.
     */
    @NotNull
    @Override
    public Set<String> getAdditionalVariants() {
        return Collections.emptySet();
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
    public AbstractMethodParams getMethodParams(@NotNull T parent) {
        return null;
    }
}
