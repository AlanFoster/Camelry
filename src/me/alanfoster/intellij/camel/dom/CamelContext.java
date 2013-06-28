package me.alanfoster.intellij.camel.dom;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//@Namespace(value = "http://camel.apache.org/schema/blueprint")
//@Presentation(typeName = "CamelContext", icon = PluginIcons.CAMEL_STRING)
public interface CamelContext extends DomElement {
    @Attribute("id")
    GenericAttributeValue<String> getId();

    /**
     * @return Gets the list of CamelRoutes registered with this camel context.
     *         This list should not be modified, instead call undefine() or interact with addRoute()
     *         which can be freely modified.
     */
    List<Route> getRoutes();

    /**
     * @param index The child index that this new route will be created at.
     * @return A new route within the camel context
     */
    Route addEntity(int index);

    /**
     * @return A new route within the camel context
     */
    Route addRoute();
}
