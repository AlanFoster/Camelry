package me.alanfoster.camelus.blueprint.inspectors;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.util.InheritanceUtil;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.highlighting.DomCustomAnnotationChecker;
import com.intellij.util.xml.highlighting.DomElementAnnotationHolder;
import com.intellij.util.xml.highlighting.DomElementProblemDescriptor;
import com.intellij.util.xml.highlighting.DomHighlightingHelper;
import me.alanfoster.camelus.blueprint.converters.ThrowableBlueprintBeanConverter;
import me.alanfoster.camelus.blueprint.dom.BlueprintBeanPointer;
import me.alanfoster.camelus.camel.dom.ThrowException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelus.CamelusBundle.message;

/**
 * Checks that the given element has a reference to a blueprint bean which extends the
 * required class.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BlueprintBeanRefExtendsChecker extends DomCustomAnnotationChecker<BlueprintBeanRefExtends> {

    @NotNull
    @Override
    public Class<BlueprintBeanRefExtends> getAnnotationClass() {
        return BlueprintBeanRefExtends.class;
    }

    @Override
    public List<DomElementProblemDescriptor> checkForProblems(final @NotNull BlueprintBeanRefExtends annotation,
                                                              final @NotNull DomElement throwExceptionElement,
                                                              final @NotNull DomElementAnnotationHolder holder,
                                                              final @NotNull DomHighlightingHelper helper) {
        PsiClass classAttribute = getBeanPsiClass(throwExceptionElement);
        if(classAttribute == null) return Collections.emptyList();

        Project project = throwExceptionElement.getManager().getProject();
        Module module = ModuleUtil.findModuleForPsiElement(throwExceptionElement.getXmlTag());
        if(module == null) return Collections.emptyList();

        final Class<?> requiredClass = annotation.value();
        final PsiClass requiredPsiClass = ThrowableBlueprintBeanConverter.getPsiClass(requiredClass, module);

        List<DomElementProblemDescriptor> problems = new ArrayList<DomElementProblemDescriptor>();
        if(!InheritanceUtil.isInheritorOrSelf(classAttribute, requiredPsiClass, true)) {
            final DomElementProblemDescriptor problem = holder.createProblem(throwExceptionElement, getErrorMessage(requiredClass));
            problems.add(problem);
        }

        return problems;
    }

    /**
     * Extracts the referenced Blueprint bean's PSi class from the throwException DOM Element.
     * @param throwExceptionElement
     * @return The extracted PSI class, which may be null.
     */
    @Nullable
    private PsiClass getBeanPsiClass(@NotNull DomElement throwExceptionElement) {
        ThrowException throwException = throwExceptionElement.getParentOfType(ThrowException.class, true);

        if(throwException == null) return null;
        BlueprintBeanPointer blueprintBean = throwException.getRef().getValue();
        if(blueprintBean == null) return null;

        PsiClass classAttribute = blueprintBean.getReferencedClass();
        return classAttribute;
    }

    private String getErrorMessage(Class requiredClass) {
        return message("camelus.blueprint.dom.extends.error", requiredClass.getName());
    }

}
