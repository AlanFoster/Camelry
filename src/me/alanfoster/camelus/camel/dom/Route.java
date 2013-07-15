package me.alanfoster.camelus.camel.dom;

import com.intellij.util.xml.*;

import java.util.List;

/**
 * Represents the basic DOM structure of a Camel Route
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see me.alanfoster.camelus.blueprint.dom.model.Blueprint
 * @see CamelContext
 */
@Stubbed
public interface Route extends DomElement {
    GenericAttributeValue<String> getId();

    /**
     * The from element in a camel route. There should be one of these per route.
     */
    @Required
    From getFrom();

    List<To> getTos();

    @SubTagList("setBody")
    List<SetBody> getSetBodys();

    @SubTagList("setHeader")
    List<SetHeader> getSetHeaders();

    @SubTagList("bean")
    List<CamelBean> getCamelBeans();

    @SubTagList("throwException")
    List<ThrowException> getThrowExceptions();
}
