package me.alanfoster.camelry.blueprint;

/**
 * Represents the common used constants specific to Blueprint.
 */
public class BlueprintConstants {
    public static final String BLUEPRINT_CM = "cm";

    public static String CM_NAMESPACE_V1_0_0 = "http://aries.apache.org/blueprint/xmlns/blueprint-cm/v1.0.0";
    public static String CM_NAMESPACE_V1_1_1 = "http://aries.apache.org/blueprint/xmlns/blueprint-cm/v1.1.0";

    public static final String[] BLUEPRINT_CM_NAMESPACES = {
            CM_NAMESPACE_V1_0_0,
            CM_NAMESPACE_V1_1_1
    };

    public static final String[] BLUEPRINT_ROOT_POSSIBLE_NAMESPACES = {
            // TODO is there a preferred way to deal with different versions within plugins?
            "http://www.osgi.org/xmlns/blueprint/v1.0.0",
            "http://www.osgi.org/xmlns/blueprint/v1.1.0"
    };
}
