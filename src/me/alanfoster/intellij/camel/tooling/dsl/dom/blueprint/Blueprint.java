package me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint;

import com.intellij.util.xml.*;
import me.alanfoster.intellij.camel.tooling.dsl.dom.camel.CamelContext;

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
    List<BlueprintBean> getBeans();

    // Represents the osgi <cm:property-placeholder /> element
    PropertyPlaceholder getPropertyPlaceHolder();

    @Required
    CamelContext getCamelContext();
}
