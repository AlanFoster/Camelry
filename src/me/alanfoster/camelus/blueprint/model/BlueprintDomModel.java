package me.alanfoster.camelus.blueprint.model;

import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.model.impl.DomModelImpl;
import me.alanfoster.camelus.blueprint.dom.model.Blueprint;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BlueprintDomModel extends DomModelImpl<Blueprint> implements IBlueprintDomModel {

    public BlueprintDomModel(@NotNull DomFileElement<Blueprint> mergedModel,
                             @NotNull Set<XmlFile> configFiles) {
        super(mergedModel, configFiles);
    }


}
