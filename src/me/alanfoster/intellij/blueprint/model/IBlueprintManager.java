package me.alanfoster.intellij.blueprint.model;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlFile;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * Blueprint manager service. Registered via Plugins.xml.
 * This provides access to the singleton instance
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public abstract class IBlueprintManager {

    public static IBlueprintManager getInstance() {
        return ServiceManager.getService(IBlueprintManager.class);
    }

    @NotNull
    public abstract Set<XmlFile> getAllBlueprintConfigFiles(@NotNull Project project);

    @NotNull
    public abstract List<IBlueprintDomModel> getAllBlueprintModels(@NotNull Module module);
}

