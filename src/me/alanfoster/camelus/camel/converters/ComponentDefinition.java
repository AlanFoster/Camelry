package me.alanfoster.camelus.camel.converters;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class ComponentDefinition {
    private String componentName;

    public ComponentDefinition(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentName() {
        return componentName;
    }
}
