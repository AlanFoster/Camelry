package me.alanfoster.intellij.blueprint.language.contributors;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.xml.XmlTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
// TODO Once InjectionPsiReferenceContributor works this should be fleshed out
public class InjectionPsiReference extends PsiReferenceBase<XmlTag> {
    private static final boolean IS_SOFT = false;

    public InjectionPsiReference(XmlTag element) {
        super(element, IS_SOFT);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }
}
