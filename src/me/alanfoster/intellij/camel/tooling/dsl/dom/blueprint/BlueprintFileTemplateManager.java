package me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint;

import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory;
import me.alanfoster.intellij.camel.icons.CamelIcons;

/**
 * Registers the File Templates of blueprint/camel so that they
 * are accessible to be created via the FileTemplateManager.
 *
 * These files are accessible via /fileTemplates and the file templates
 * should end in "*.ft" - IE "Foo.java.ft"
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see me.alanfoster.intellij.camel.tooling.dsl.dom.CreateCamelFile
 * @see <a href="http://devnet.jetbrains.com/message/5316246">http://devnet.jetbrains.com/message/5316246</a>
 */
public class BlueprintFileTemplateManager implements FileTemplateGroupDescriptorFactory {
    public static final String BLUEPRINT_DEFINITION_FILE = "BlueprintDefinition.xml";
    public static final String BLUEPRINT_TEST_FILE = "BlueprintTest.java";

    @Override
    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        final FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor("CamelBlueprint", CamelIcons.CAMEL);

        group.addTemplate(new FileTemplateDescriptor(BLUEPRINT_DEFINITION_FILE, CamelIcons.CAMEL));
        group.addTemplate(new FileTemplateDescriptor(BLUEPRINT_TEST_FILE, CamelIcons.CAMEL));

        return group;
    }
}
