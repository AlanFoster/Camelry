package me.alanfoster.camelus.camel.simple.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import me.alanfoster.camelus.camel.simple.language.SimpleLanguage;
import me.alanfoster.camelus.icons.PluginIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * This class represents the details of a FileType.
 * Currently this class is set up to support a file extension ending
 * in 'simple', but such a thing doesn't actually exist in camel land.
 *
 * Work will be needed to only add simple language support in the
 * appropriate places - such as camel xml files with `simple` tags and such.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SimpleFileType extends LanguageFileType {
    /**
     * The singleton instance of this class.
     */
    public static final LanguageFileType INSTANCE = new SimpleFileType();

    /**
     * A Semicolon deliminated list of file extensions that this is recognised
     */
    @NonNls
    private static final String EXTENSIONS = "simple";

    private SimpleFileType() {
        super(SimpleLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Simple Syntax";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "A Camel File";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return EXTENSIONS;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return PluginIcons.CAMEL;
    }
}
