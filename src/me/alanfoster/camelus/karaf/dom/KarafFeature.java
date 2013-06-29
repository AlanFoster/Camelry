package me.alanfoster.camelus.karaf.dom;

import com.intellij.util.xml.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface KarafFeature extends DomElement {
    @NameValue(unique = true)
    @NotNull
    @Required(nonEmpty = true, value = true)
    @Attribute("name")
    GenericAttributeValue<String> getName();

    @SubTagList("feature")
    List<KarafFeatureDependency> getFeatureDependencies();

    @SubTagList("bundle")
    List<KarafBundle> getBundles();
}
