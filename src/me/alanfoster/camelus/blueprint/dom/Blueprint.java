package me.alanfoster.camelus.blueprint.dom;

import com.intellij.util.xml.*;
import me.alanfoster.camelus.camel.dom.CamelContext;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

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

    @SubTagList(value = "reference")
    List<BlueprintReference> getReferences();

    // Represents the osgi <cm:property-placeholder /> element
    @SubTag("property-placeholder")
    PropertyPlaceholder getPropertyPlaceHolder();

    CamelContext getCamelContext();

    /**
     * Not DOM related; Should be provided by the DOM implementation
     */
    @NotNull
    List<? extends BlueprintBeanPointer> getBlueprintBeanPointers();
}
