package me.alanfoster.camelry.configuration;

import com.intellij.openapi.components.*;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the CamelryConfiguration service which should store the plugin's state within the Application level.
 * IE This configuration should be persisted across all projects.
 */
@State(
        name="CamelryConfiguration",
        storages = {
                @Storage(id = "default", file = StoragePathMacros.APP_CONFIG + "/CamelryConfiguration.xml"),
        }
)
public class CamelryConfigurationService implements PersistentStateComponent<CamelryConfigurationModel> {
    private CamelryConfigurationModel state;

    public CamelryConfigurationService() {
        state = CamelryConfigurationModel.getDefaults();
    }

    @Nullable
    public static CamelryConfigurationModel getConfiguration() {
        CamelryConfigurationService service = ServiceManager.getService(CamelryConfigurationService.class);
        return service.getState();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public CamelryConfigurationModel getState() {
        return state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadState(CamelryConfigurationModel state) {
       this.state = state;
    }
}
