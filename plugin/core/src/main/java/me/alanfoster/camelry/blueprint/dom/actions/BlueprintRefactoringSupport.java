package me.alanfoster.camelry.blueprint.dom.actions;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.RefactoringActionHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provides refactoring support for the Blueprint injection language.
 * Currently this only supports the ability to introduce a variable, ie, a
 * property placeholder value.
 */
public class BlueprintRefactoringSupport extends RefactoringSupportProvider {
    @Override
    public boolean isAvailable(@NotNull PsiElement context) {
        return true;
    }

    @Nullable
    @Override
    public RefactoringActionHandler getIntroduceVariableHandler() {
        return new IntroducePropertyPlaceholderRefactoring();
    }
}
