package me.alanfoster.camelus.karaf.dom;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.SubTagList;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Represents the interface for a Karaf Features DOM file.
 * Schema location: http://karaf.apache.org/xmlns/features/v1.0.0
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface KarafFeaturesRoot extends DomElement {
    @NonNls
    public static final String ROOT_ELEMENT_NAME = "features";

    @SubTagList("repository")
    List<KarafRepository> getKarafRepositories();

    @SubTagList("feature")
    @NotNull
    List<KarafFeature> getFeatures();
}
