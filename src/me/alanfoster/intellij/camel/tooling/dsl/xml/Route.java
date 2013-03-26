package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.DomElement;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */

public interface Route extends DomElement {
    List<Method> getMethods();
}
