package me.alanfoster.intellij.blueprint.dom;

import com.intellij.openapi.util.Iconable;
import com.intellij.util.xml.DomFileDescription;
import me.alanfoster.intellij.icons.CamelIcons;

import javax.swing.*;

/**
 * Wires up a DomFileDescription, so that you can use it with the DOMManager.
 * And work with actual objects, rather than deal with raw XML.
 *
 * This has been wired up in the plugin.xml file under the extensions 'dom.fileDescription'
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see http://confluence.jetbrains.com/display/IDEADEV/Accessing+XML+through+IntelliJ+IDEA+DOM
 */
public class BlueprintFileDescription extends DomFileDescription<Blueprint>  {
    /**
     * The list of all possible namespaces that the root element node can be under.
     */
    private static final String[] ROOT_ELEMENT_POSSIBLE_NAMESPACES = new String[] {
        "http://www.osgi.org/xmlns/blueprint/v1.0.0"
    };

    public BlueprintFileDescription() {
        super(Blueprint.class, Blueprint.ROOT_ELEMENT_NAME, ROOT_ELEMENT_POSSIBLE_NAMESPACES);
    }

    @Override
    public Icon getFileIcon(@Iconable.IconFlags int flags) {
        return CamelIcons.CAMEL;
    }
}
