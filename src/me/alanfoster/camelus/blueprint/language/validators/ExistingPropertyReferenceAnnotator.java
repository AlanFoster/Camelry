package me.alanfoster.camelus.blueprint.language.validators;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import me.alanfoster.camelus.blueprint.language.contributors.InjectionPsiReference;
import me.alanfoster.camelus.blueprint.language.psi.InjectionPropertyDefinition;
import org.jetbrains.annotations.NotNull;

/**
 * Provides an error annotation when a referenced Blueprint property value does not exist.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class ExistingPropertyReferenceAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if(element instanceof InjectionPropertyDefinition) {
            InjectionPropertyDefinition injectionPropertyDefinition = (InjectionPropertyDefinition) element;
            final PsiElement propertyName = injectionPropertyDefinition.getPropertyElement();

            if(!hasReference(element)) {
                holder.createErrorAnnotation(propertyName, getMessageErrorMessage(injectionPropertyDefinition.getPropertyName()));
            }
        }
    }

    private String getMessageErrorMessage(String referencedElement) {
        return "Unresolved Blueprint Property " + referencedElement;
    }

    private boolean hasReference(PsiElement psiElement) {
        // TODO Perhaps we should add a hasReference method to the InjectionPropertyDefinition instead?
        return new InjectionPsiReference(psiElement).resolve() != null;
    }
}
