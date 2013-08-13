package me.alanfoster.camelry.blueprint.dom.converters;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.CommonClassNames;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.ConvertContext;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBean;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBeanPointer;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

/**
 * Converter for Blueprint beans which only extend Throwable.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see BlueprintBean
 */
public class ThrowableBlueprintBeanConverter extends BlueprintBeanPointerConverter {
    @NotNull
    @Override
    public Collection<? extends BlueprintBeanPointer> getVariants(final ConvertContext context) {
        final Module module = context.getModule();
        if(module == null) return Collections.emptyList();

        final PsiClass throwablePsiClass = getPsiClass(CommonClassNames.JAVA_LANG_THROWABLE, module);
        return ContainerUtil.filter(super.getVariants(context), new Condition<BlueprintBeanPointer>() {
            @Override
            public boolean value(BlueprintBeanPointer blueprintBean) {
                final PsiClass psiClass = blueprintBean.getReferencedClass();

                return InheritanceUtil.isInheritorOrSelf(psiClass, throwablePsiClass, true);
            }
        });
    }

    public static PsiClass getPsiClass(@NotNull Class<?> clazz, @NotNull Module module) {
        return getPsiClass(clazz.getName(), module);
    }

    public static PsiClass getPsiClass(String qualifiedName, @NotNull Module module) {
        final PsiClass psiClass = JavaPsiFacade.getInstance(module.getProject())
                .findClass(qualifiedName, module.getModuleWithDependenciesAndLibrariesScope(true));

        return psiClass;
    }

}
