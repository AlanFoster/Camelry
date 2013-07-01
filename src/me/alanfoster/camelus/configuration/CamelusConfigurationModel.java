package me.alanfoster.camelus.configuration;

import com.intellij.openapi.components.PersistentStateComponent;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a basic POJO for all of Camelus' settings.
 * This may need to become more complex in the future.
 */
public class CamelusConfigurationModel {

    private boolean isSimpleInjected;

    public boolean isSimpleInjected() {
        return isSimpleInjected;
    }

    public void setSimpleInjected(boolean simpleInjected) {
        isSimpleInjected = simpleInjected;
    }

    /**
     * Creates a new Configuration object with the correct default values.
     * This will most likely be called by the CamelusConfigurationService
     *
     * @return A new instance of a default configuration object
     */
    protected static CamelusConfigurationModel getDefaults() {
        CamelusConfigurationModel model = new CamelusConfigurationModel();
        model.setSimpleInjected(false);
        return model;
    }

    @Override
    public String toString() {
        return "CamelusConfigurationModel{" +
                "isSimpleInjected=" + isSimpleInjected +
                '}';
    }

}
