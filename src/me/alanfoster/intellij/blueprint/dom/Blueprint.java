package me.alanfoster.intellij.blueprint.dom;

import com.intellij.util.xml.*;
import me.alanfoster.intellij.camel.dom.CamelContext;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//@Stubbed
@NameStrategy(value = JavaNameStrategy.class)
public interface Blueprint extends DomElement {
    public static final String ROOT_ELEMENT_NAME = "blueprint";

    @SubTagList(value = "bean")
    List<BlueprintBean> getBeans();

    // Represents the osgi <cm:property-placeholder /> element
    @SubTag("property-placeholder")
    PropertyPlaceholder getPropertyPlaceHolder();

    CamelContext getCamelContext();
}
