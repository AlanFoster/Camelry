package me.alanfoster.camelry.blueprint.language.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import me.alanfoster.camelry.blueprint.language.BlueprintInjectionLanguage;
import me.alanfoster.camelry.icons.PluginIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import static me.alanfoster.camelry.CamelryBundle.message;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class InjectionFileType extends LanguageFileType {
    public static final InjectionFileType INSTANCE = new InjectionFileType();

    /**
     * A Semicolon deliminated list of file extensions that this is recognised
     */
    @NonNls
    private static final String EXTENSIONS = "blueprint;blueprintinjectionlanguage";

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
        return message("camelus.blueprint.language.description");
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return EXTENSIONS;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return PluginIcons.BLUEPRINT;
    }
}
