package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.*;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//@Stubbed
@NameStrategy(value = JavaNameStrategy.class)
public interface Blueprint extends DomElement {
    @SubTagList(value = "bean")
    @Required
    List<Bean> getBeans();

    @Required
    CamelContext getCamelContext();
}
