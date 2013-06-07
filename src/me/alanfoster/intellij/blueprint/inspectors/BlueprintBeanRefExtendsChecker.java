package me.alanfoster.intellij.blueprint.inspectors;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.highlighting.DomCustomAnnotationChecker;
import com.intellij.util.xml.highlighting.DomElementAnnotationHolder;
import com.intellij.util.xml.highlighting.DomElementProblemDescriptor;
import com.intellij.util.xml.highlighting.DomHighlightingHelper;
import me.alanfoster.intellij.blueprint.converters.ThrowableBlueprintBeanConverter;
import me.alanfoster.intellij.blueprint.dom.BlueprintBean;
import me.alanfoster.intellij.camel.dom.ThrowException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Checks that the given element
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
                                                              final @NotNull DomElement element,
                                                              final @NotNull DomElementAnnotationHolder holder,
                                                              final @NotNull DomHighlightingHelper helper) {
        ThrowException throwException = element.getParentOfType(ThrowException.class, true);
        if(throwException == null) {
            return Collections.EMPTY_LIST;
        }

        BlueprintBean blueprintBean = throwException.getRef().getValue();
        if(blueprintBean == null) {
            return Collections.EMPTY_LIST;
        }

        PsiClass classAttribute = blueprintBean.getClassAttribute().getValue();
        if(classAttribute == null) {
            return Collections.EMPTY_LIST;
        }

        Project project = element.getManager().getProject();
        final Class<?> requiredClass = annotation.value();
        final PsiClass requiredPsiClass = ThrowableBlueprintBeanConverter.getPsiClass(requiredClass, project);

        List<DomElementProblemDescriptor> problems = new ArrayList<DomElementProblemDescriptor>();
        if(!ThrowableBlueprintBeanConverter.hasSuper(classAttribute, requiredPsiClass)) {
            final DomElementProblemDescriptor problem = holder.createProblem(element, getErrorMessage(requiredClass));
            problems.add(problem);
        }

        return problems;
    }

    private String getErrorMessage(Class requiredClass) {
        return "This BlueprintBean does not extend the required class " + requiredClass.getName();
    }

}
