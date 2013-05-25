package me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint;

import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.ResolvingConverter;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Returns all methods which are setters and converts it to JavaNaming conventions.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//  TODO Is there an implementation of this already offered out of the box?
public class BeanPropertyResolver extends ResolvingConverter<PsiMethod> {
    private final Class<BeanProperty> parentTypeClass;

    public BeanPropertyResolver() {
        parentTypeClass = BeanProperty.class;
    }

    @NotNull
    @Override
    public Collection<? extends PsiMethod> getVariants(ConvertContext context) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nullable
    @Override
    public PsiMethod fromString(@Nullable @NonNls String s, ConvertContext context) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nullable
    @Override
    public String toString(@Nullable PsiMethod psiMethod, ConvertContext context) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

/*
    @NotNull
    @Override
    protected Collection<PsiClass> getPsiClasses(BeanProperty beanProperty, ConvertContext context) {
        if (beanProperty.getParent() instanceof Bean) {
            Bean parent = (Bean) beanProperty.getParent();
            PsiClass beanClass = parent.getClassAttribute().getValue();
            if (beanClass != null) {
                return Arrays.asList(beanClass);
            }
        }
        return Collections.EMPTY_LIST;
    }

*/

/*
    @Nullable
    @Override
    protected AbstractMethodParams getMethodParams(@NotNull BeanProperty parent) {
        return null;
    }
*/

/*    public Set<String> getAdditionalVariants() {
        return Collections.EMPTY_SET;
    }*/
}
