package me.alanfoster.intellij.camel.tooling.dsl.dom;

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
