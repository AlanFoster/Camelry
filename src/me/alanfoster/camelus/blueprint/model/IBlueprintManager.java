package me.alanfoster.camelus.blueprint.model;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlFile;
import me.alanfoster.camelus.blueprint.dom.PropertyPlaceholder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    /**
     * @param project The project to search blueprint config files for
     * @return ALL blueprint config files within the project.
     */
    @NotNull
    public abstract Set<XmlFile> getAllBlueprintConfigFiles(@NotNull Project project);

    /**
     * @param module The module to search for
     * @return The blueprint config files within the given module.
     *         To get all blueprint config files within the project, make use of the getAllBlueprintConfigFiles instead
     */
    @NotNull
    public abstract Set<XmlFile> getModuleBlueprintConfigFiles(@NotNull Module module);

    @NotNull
    public abstract List<IBlueprintDomModel> getAllBlueprintModels(@NotNull Module module);

    @Nullable
    public abstract IBlueprintDomModel getMergedBlueprintModel(@NotNull Module module);

    /**
     * Each Blueprint bundle is allowed one PropertyPlaceholder.
     * This property placeholder should have a unique PID.
     *
     * @param module the module to search for a property placeholder in
     * @return
     */
    @Nullable
    public abstract PropertyPlaceholder getModulePropertyPlaceHolder(@NotNull Module module);
}

