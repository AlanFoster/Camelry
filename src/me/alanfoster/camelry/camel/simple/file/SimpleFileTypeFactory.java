package me.alanfoster.camelry.camel.simple.file;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * A file type factory for the SimpleFileType.
 *
 * This will be registered explicitly to the plugin xml file in META-INF
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @See SimpleFileType
 */
public class SimpleFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
       fileTypeConsumer.consume(SimpleFileType.INSTANCE);
    }
}
