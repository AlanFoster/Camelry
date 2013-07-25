package me.alanfoster.camelus.blueprint.dom.model;

import com.intellij.openapi.util.Iconable;
import com.intellij.util.xml.DomFileDescription;
import me.alanfoster.camelus.blueprint.BlueprintConstants;
import me.alanfoster.camelus.icons.PluginIcons;

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
    public BlueprintFileDescription() {
        super(Blueprint.class, Blueprint.ROOT_ELEMENT_NAME, BlueprintConstants.BLUEPRINT_ROOT_POSSIBLE_NAMESPACES);
    }

    @Override
    protected void initializeFileDescription() {
        registerNamespacePolicy(BlueprintConstants.BLUEPRINT_CM, BlueprintConstants.BLUEPRINT_CM_NAMESPACES);
    }

    @Override
    public Icon getFileIcon(@Iconable.IconFlags int flags) {
        return PluginIcons.CAMEL;
    }
}
