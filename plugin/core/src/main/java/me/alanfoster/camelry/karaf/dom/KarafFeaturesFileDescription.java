package me.alanfoster.camelry.karaf.dom;

import com.intellij.openapi.util.Iconable;
import com.intellij.util.xml.DomFileDescription;
import me.alanfoster.camelry.icons.PluginIcons;

import javax.swing.*;

/**
 * Represents the interface for a Karaf Features DOM file.
 * Schema location: http://karaf.apache.org/xmlns/features/v1.0.0
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see KarafFeaturesRoot
 */
public class KarafFeaturesFileDescription extends DomFileDescription<KarafFeaturesRoot>  {
    /**
     * The list of all possible namespaces that the root element node can be under.
     */
    private static final String[] ROOT_ELEMENT_POSSIBLE_NAMESPACES = new String[] {
            "http://karaf.apache.org/xmlns/features/v1.0.0"
    };

    public KarafFeaturesFileDescription() {
        super(KarafFeaturesRoot.class, KarafFeaturesRoot.ROOT_ELEMENT_NAME, ROOT_ELEMENT_POSSIBLE_NAMESPACES);
    }

    @Override
    public Icon getFileIcon(@Iconable.IconFlags int flags) {
        return PluginIcons.KARAF;
    }
}
