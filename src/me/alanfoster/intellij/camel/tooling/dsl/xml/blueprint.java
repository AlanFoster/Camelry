package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.Required;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface Blueprint extends DomElement {
    @Required
    List<Bean> getBeans();
    // TODO This doesn't seem to handle 'CamelContext' as the name, is this camel case related?
    @Required
    Context getContext();
}
