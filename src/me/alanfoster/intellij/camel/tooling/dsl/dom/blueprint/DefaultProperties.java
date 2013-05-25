package me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint;

import com.intellij.util.xml.*;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@NameStrategy(value = HyphenNameStrategy.class)
@Namespace("http://aries.apache.org/blueprint/xmlns/blueprint-cm/v1.0.0")
public interface DefaultProperties extends DomElement {
    @SubTagList("property")
    List<Property> getProperties();
}
