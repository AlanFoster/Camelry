package me.alanfoster.camelus.blueprint.dom.model;

import com.intellij.util.xml.*;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@NameStrategy(value = HyphenNameStrategy.class)
public interface DefaultProperties extends DomElement {
    @SubTagList("property")
    List<Property> getProperties();

    Property addProperty();
}
