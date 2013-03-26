package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//@Namespace(value = "http://camel.apache.org/schema/blueprint")
public interface Context extends DomElement {
    @Attribute("id")
    GenericAttributeValue<String> getId();
    List<Route> getRoutes();
}
