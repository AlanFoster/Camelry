package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.Stubbed;
import com.intellij.util.xml.SubTagList;

import java.util.List;

/**
 * Represents the basic DOM structure of a Camel Route
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see Blueprint
 * @see CamelContext
 */
@Stubbed
public interface Route extends DomElement {
    /**
     * The Root from element in a camel route. There should only be one of these per route.
     * @return
     */
    From getFrom();

    List<To> getTos();

    @SubTagList("setBody")
    List<SetBody> getSetBodys();

    @SubTagList("setHeader")
    List<SetHeader> getSetHeaders();
}
