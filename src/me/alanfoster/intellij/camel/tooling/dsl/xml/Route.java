package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.DomElement;

import java.util.List;

/**
 * Represents the basic DOM structure of a Camel Route
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see Blueprint
 * @see CamelContext
 */
public interface Route extends DomElement {
    From getFrom();
    List<To> getTos();
    List<Method> getMethods();
}
