package me.alanfoster.camelus.camel.converters;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.converters.AbstractMethodParams;
import com.intellij.util.xml.converters.AbstractMethodResolveConverter;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import me.alanfoster.camelus.camel.dom.CamelBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
// TODO This is very similiar to MethodMethodConverter
public class CamelBeanMethodConverter extends AbstractMethodResolveConverter<CamelBean> {

    public CamelBeanMethodConverter() {
        super(CamelBean.class);
    }

    /**
     * @param parent
     * @param context
     * @return The class which this method references in the DOM.
     *         For example, a bean defined outside the scope of the camel context
     */
    @NotNull
    @Override
    protected Collection<PsiClass> getPsiClasses(CamelBean parent, ConvertContext context) {
        final BlueprintBean beanReference = getBeanReference(parent);
        if (beanReference != null) {
            PsiClass beanClass = beanReference.getClassAttribute().getValue();
            if (beanClass != null) {
                return Arrays.asList(beanClass);
            }
        }
        return Collections.emptyList();
    }

    private BlueprintBean getBeanReference(CamelBean parent) {
        return parent.getRef().getValue();
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
    protected AbstractMethodParams getMethodParams(@NotNull CamelBean parent) {
        return null;
    }

    /**
     * This method returns any additional methods that should be on the
     * getVariants() list.
     * <p/>
     * Camel doesn't offer any additional methods to call on methods, so
     * this should return an empty set.
     *
     * @return An empty set.
     */
    public Set<String> getAdditionalVariants() {
        return Collections.emptySet();
    }
}