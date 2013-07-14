package me.alanfoster.camelus.blueprint.converters;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.converters.AbstractMethodParams;
import com.intellij.util.xml.converters.AbstractMethodResolveConverter;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Provides *all* methods of the given blueprint Bean.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BlueprintBeanMethodProvider extends AbstractMethodResolveConverter<BlueprintBean> {

    public BlueprintBeanMethodProvider() {
        super(BlueprintBean.class);
    }

    @NotNull
    @Override
    protected Collection<PsiClass> getPsiClasses(BlueprintBean parent, ConvertContext context) {
        if (parent != null) {
            PsiClass beanClass = parent.getClassAttribute().getValue();
            if (beanClass != null) {
                return Arrays.asList(beanClass);
            }
        }
        return Collections.emptyList();
    }

    /**
     * No method arguments can be specified in blueprint, so this method returns null by default.
     *
     * @param parent
     * @return
     */
    @Nullable
    @Override
    // TODO This will be useful for the bean language, we should make use of this! :)
    protected AbstractMethodParams getMethodParams(@NotNull BlueprintBean parent) {
        return null;
    }

    /**
     * This method returns any additional methods that should be on the
     * getVariants() list.
     *
     * Blueprint doesn't offer any additional methods to call on methods, so
     * this should return an empty set.
     *
     * @return An empty set.
     */
    @NotNull
    @Override
    public Set<String> getAdditionalVariants() {
        return Collections.emptySet();
    }
}
