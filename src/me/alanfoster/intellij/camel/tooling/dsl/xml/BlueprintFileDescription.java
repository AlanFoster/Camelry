package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.DomFileDescription;

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
public class BlueprintFileDescription extends DomFileDescription<Blueprint> {
    public BlueprintFileDescription() {
        super(Blueprint.class, "blueprint",
                // List all of the possible namespaces that the root blueprint node can be under
                "http://www.osgi.org/xmlns/blueprint/v1.0.0");
    }
}
