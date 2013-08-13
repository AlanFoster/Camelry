package me.alanfoster.camelry.camel.simple.language.parser;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import me.alanfoster.camelry.camel.simple.language.SimpleLanguage;
import me.alanfoster.camelry.camel.simple.file.SimpleFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Represents a file for the 'Program Structure Interface' files
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see {@link http://confluence.jetbrains.com/display/IDEADEV/IntelliJ+IDEA+Architectural+Overview#IntelliJIDEAArchitecturalOverview-PsiFiles}
 */
public class SimplePsiFile extends PsiFileBase {
    public SimplePsiFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SimpleLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SimpleFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "SimplePsiFile{}";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
