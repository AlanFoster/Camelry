package me.alanfoster.intellij.blueprint.language.file;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class InjectionFileTypeFactory extends FileTypeFactory {

    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(InjectionFileType.INSTANCE, "blueprintinjectionlanguage");
    }
}
