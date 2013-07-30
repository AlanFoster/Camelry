package me.alanfoster.camelry.configuration;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import static me.alanfoster.camelry.CamelryBundle.message;

/**
 * Camelus Configuration form. Note, I have decided to test out IntelliJ's form builder for this class.
 * So try to edit this form using the IntelliJ form builder, rather than modifying the swing components
 * directly.
 */
// TODO Think about what level of configuration we may need for the plugin, to best decide how much time to invest in creating a reusable solution
// TODO There's a really slow call time for isModified(), maybe we need to manually call UISettings.getInstance().fireUISettingsChanged(); ourselves?
public class CamelryConfigurationForm implements Configurable {
    private JPanel panel;
    private JCheckBox simpleLanguageInjectionCheckBox;
    private CamelryConfigurationModel settings;

    @Nls
    @Override
    public String getDisplayName() {
        return message("camelus.configuration.displayname");
    }

    @Nullable
    @Override
    // TODO Where do i register a help topic?
    public String getHelpTopic() {
        //return "preferences.settings.project.camelus";
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        // Bind our model to the application layer
        settings = CamelryConfigurationService.getConfiguration();
        bindModel(settings);
        return panel;
    }

    /**
     * Bind the given model to the GUI
     * @param model The model to bind to the GUI
     */
    private void bindModel(CamelryConfigurationModel model) {
        simpleLanguageInjectionCheckBox.setSelected(model.isSimpleLanguageInjected());
    }

    @Override
    public boolean isModified() {
        return simpleLanguageInjectionCheckBox.isSelected() != settings.isSimpleLanguageInjected();
    }

    @Override
    public void apply() throws ConfigurationException {
        settings.setSimpleLanguageInjected(simpleLanguageInjectionCheckBox.isSelected());
    }

    @Override
    public void reset() {
        bindModel(settings);
    }

    @Override
    public void disposeUIResources() {
        // noop - no plugin seems to dispose their ui resources!
    }
}
