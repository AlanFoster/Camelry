package me.alanfoster.intellij.camel.tooling.dsl.dom;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class RefactorTest  extends RefactoringSupportProvider {
    @Override
    public boolean isMemberInplaceRenameAvailable(PsiElement psiElement, PsiElement context) {
        return true;
    }
}
