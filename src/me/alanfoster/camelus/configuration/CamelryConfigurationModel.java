package me.alanfoster.camelus.configuration;

/**
 * Represents a basic POJO for all of Camelus' settings.
 * This may need to become more complex in the future.
 */
public class CamelryConfigurationModel {

    private boolean isSimpleLanguageInjected;

    public boolean isSimpleLanguageInjected() {
        return isSimpleLanguageInjected;
    }

    public void setSimpleLanguageInjected(boolean simpleLanguageInjected) {
        isSimpleLanguageInjected = simpleLanguageInjected;
    }

    /**
     * Creates a new Configuration object with the correct default values.
     * This will most likely be called by the CamelryConfigurationService
     *
     * @return A new instance of a default configuration object
     */
    protected static CamelryConfigurationModel getDefaults() {
        CamelryConfigurationModel model = new CamelryConfigurationModel();
        model.setSimpleLanguageInjected(false);
        return model;
    }

    @Override
    public String toString() {
        return "CamelryConfigurationModel{" +
                "isSimpleLanguageInjected=" + isSimpleLanguageInjected +
                '}';
    }

}
