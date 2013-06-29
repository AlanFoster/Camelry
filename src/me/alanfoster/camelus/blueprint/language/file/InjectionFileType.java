package me.alanfoster.camelus.blueprint.language.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import me.alanfoster.camelus.blueprint.language.BlueprintInjectionLanguage;
import me.alanfoster.camelus.icons.PluginIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class InjectionFileType extends LanguageFileType {
    public static final InjectionFileType INSTANCE = new InjectionFileType();

    protected InjectionFileType() {
        super(BlueprintInjectionLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "BlueprintInjectionLanguageFile";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "BlueprintInjectionLanguageFile";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "blueprint";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return PluginIcons.BLUEPRINT;
    }
}
