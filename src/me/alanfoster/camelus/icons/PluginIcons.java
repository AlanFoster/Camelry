package me.alanfoster.camelus.icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * Stores all of the Icons used by this plugin
 *
 * public static access to such data seems to be the common way
 * of doing this in IntelliJ plugins...
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class PluginIcons {
    public static final String CAMEL_STRING = "/me/alanfoster/camelus/icons/camel_16x16.png";
    public static final Icon CAMEL = IconLoader.getIcon(CAMEL_STRING);

    public static final String KARAF_STRING = "/me/alanfoster/camelus/icons/karaf_16x16.png";
    public static final Icon KARAF = IconLoader.getIcon(KARAF_STRING);

    public static final String BLUEPRINT_STRING = "/me/alanfoster/camelus/icons/blueprint_16x16.png";
    public static final Icon BLUEPRINT = IconLoader.getIcon(BLUEPRINT_STRING);
}
