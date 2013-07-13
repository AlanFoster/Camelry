package me.alanfoster.camelus.blueprint.converters;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.CommonClassNames;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.ConvertContext;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Converter for Blueprint beans which only extend Throwable.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see BlueprintBean
 */
public class ThrowableBlueprintBeanConverter extends BlueprintBeanConverter {
    @NotNull
    @Override
    public Collection<? extends BlueprintBean> getVariants(final ConvertContext context) {
        final Project project = context.getProject();
        final PsiClass throwablePsiClass = getPsiClass(CommonClassNames.JAVA_LANG_THROWABLE, project);
        return ContainerUtil.filter(super.getVariants(context), new Condition<BlueprintBean>() {
            @Override
            public boolean value(BlueprintBean blueprintBean) {
                final PsiClass psiClass = blueprintBean.getClassAttribute().getValue();

                return InheritanceUtil.isInheritorOrSelf(psiClass, throwablePsiClass, true);
            }
        });
    }

    public static PsiClass getPsiClass(@NotNull Class<?> clazz, Project project) {
        return getPsiClass(clazz.getName(), project);
    }

    public static PsiClass getPsiClass(String qualifiedName, Project project) {
        final PsiClass psiClass = JavaPsiFacade.getInstance(project)
                .findClass(qualifiedName, GlobalSearchScope.allScope(project));

        return psiClass;
    }

}
