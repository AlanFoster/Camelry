package me.alanfoster.intellij.blueprint.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceService;
import me.alanfoster.intellij.blueprint.language.psi.BlueprintInjectionPropertyDefinition;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public abstract class BlueprintInjectionPropertyDefinitionImpl extends ASTWrapperPsiElement implements BlueprintInjectionPropertyDefinition {
    public BlueprintInjectionPropertyDefinitionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    @NotNull
    public PsiReference[] getReferences() {
        return PsiReferenceService.getService().getContributedReferences(this);
    }
}
