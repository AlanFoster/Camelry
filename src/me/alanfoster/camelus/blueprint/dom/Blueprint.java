package me.alanfoster.camelus.blueprint.dom;

import com.intellij.util.xml.*;
import me.alanfoster.camelus.camel.dom.CamelContext;
import org.jetbrains.annotations.NonNls;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//@Stubbed
@NameStrategy(value = JavaNameStrategy.class)
public interface Blueprint extends DomElement {
    @NonNls
    public static final String ROOT_ELEMENT_NAME = "blueprint";

    @SubTagList(value = "bean")
    List<BlueprintBean> getBeans();

    // Represents the osgi <cm:property-placeholder /> element
    @SubTag("property-placeholder")
    PropertyPlaceholder getPropertyPlaceHolder();

    CamelContext getCamelContext();
}
