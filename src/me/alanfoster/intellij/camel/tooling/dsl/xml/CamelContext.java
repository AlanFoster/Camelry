package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.*;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//@Namespace(value = "http://camel.apache.org/schema/blueprint")
public interface CamelContext extends DomElement {
    @Attribute("id")
    GenericAttributeValue<String> getId();
    List<Route> getRoutes();
}
