package me.alanfoster.camelus.blueprint.model;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlFile;
import me.alanfoster.camelus.blueprint.dom.Blueprint;
import me.alanfoster.camelus.blueprint.dom.BlueprintBeanPointer;
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
public abstract class BlueprintManager {

    public static BlueprintManager getInstance() {
        return ServiceManager.getService(BlueprintManager.class);
    }

    /**
     * @param project The project to search blueprint config files for
     * @return ALL blueprint config files within the project.
     */
    @NotNull
    public abstract Set<XmlFile> getAllProjectBlueprintConfigFiles(@NotNull Project project);

    @NotNull
    public abstract Set<Blueprint> getAllProjectBlueprintRoots(@NotNull Project project);

    /**
     * @param module The module to search for
     * @return The blueprint config files within the given module.
     *         To get all blueprint config files within the project, make use of the getAllProjectBlueprintConfigFiles instead
     */
    @NotNull
    public abstract Set<XmlFile> getModuleBlueprintConfigFiles(@NotNull Module module);

    @NotNull
    public abstract Set<Blueprint> getModuleBlueprintRoots(@NotNull Module module);

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


    /**
     * @param module The scope of search
     * @return The list of BlueprintBeanPointer instances within the given module.
     *         An empty list will be returned of none are found within the scope.
     * @see BlueprintBeanPointer
     */
    @NotNull
    public abstract List<? extends BlueprintBeanPointer> getAllModuleBlueprintBeanPointers(@NotNull Module module);
}

