package me.alanfoster.camelry;

import com.intellij.ide.presentation.PresentationProvider;
import me.alanfoster.camelry.icons.PluginIcons;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides the presentation icons etc for Camelus.
 */
public class CamelryPresentationProvider {
    public static class Blueprint extends PresentationProvider<Object> {
        @Override
        @Nullable
        public String getName(Object t) {
            return null;
        }

        @Override
        @Nullable
        public Icon getIcon(Object t) {
            return PluginIcons.BLUEPRINT;
        }

        @Override
        @Nullable
        public String getTypeName(Object t) {
            return null;
        }
    }
}
