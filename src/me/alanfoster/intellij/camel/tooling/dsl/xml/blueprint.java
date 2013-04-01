package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.JavaNameStrategy;
import com.intellij.util.xml.NameStrategy;
import com.intellij.util.xml.Required;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@NameStrategy(value = JavaNameStrategy.class)
public interface Blueprint extends DomElement {
    @Required
    List<Bean> getBeans();
    @Required
    CamelContext getCamelContext();
}
