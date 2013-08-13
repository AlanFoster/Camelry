package me.alanfoster.camelry.blueprint.language.file;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import me.alanfoster.camelry.blueprint.language.BlueprintInjectionLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class InjectionFile extends PsiFileBase {
    public InjectionFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, BlueprintInjectionLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return InjectionFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Blueprint File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
