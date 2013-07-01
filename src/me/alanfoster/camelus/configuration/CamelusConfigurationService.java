package me.alanfoster.camelus.configuration;

import com.intellij.openapi.components.*;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the CamelusConfiguration service which should store the plugin's state within the Application level.
 * IE This configuration should be persisted across all projects.
 */
@State(
        name="CamelusConfiguration",
        storages = {
                @Storage(id = "default", file = StoragePathMacros.APP_CONFIG + "/CamelusConfiguration.xml"),
        }
)
public class CamelusConfigurationService implements PersistentStateComponent<CamelusConfigurationModel> {
    private CamelusConfigurationModel state;

    public CamelusConfigurationService() {
        state = CamelusConfigurationModel.getDefaults();
    }

    @Nullable
    public static CamelusConfigurationModel getConfiguration() {
        CamelusConfigurationService service = ServiceManager.getService(CamelusConfigurationService.class);
        return service == null ? null : service.getState();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public CamelusConfigurationModel getState() {
        return state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadState(CamelusConfigurationModel state) {
       this.state = state;
    }
}
