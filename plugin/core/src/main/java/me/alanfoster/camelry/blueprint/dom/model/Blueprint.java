package me.alanfoster.camelry.blueprint.dom.model;

import com.intellij.util.xml.*;
import me.alanfoster.camelry.camel.dom.CamelContextFactoryBean;
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

    @SubTagList(value = "service")
    List<Service> getServices();

    // Represents the osgi <cm:property-placeholder /> element
    @SubTag(PropertyPlaceholder.TAG_NAME)
    PropertyPlaceholder getPropertyPlaceHolder();

    CamelContextFactoryBean getCamelContext();
    //CamelContextFactoryBean getCamelContext();

    /**
     * Not DOM related; Should be provided by the DOM implementation
     */
    @NotNull
    List<? extends BlueprintBeanPointer> getBlueprintBeanPointers();
}
