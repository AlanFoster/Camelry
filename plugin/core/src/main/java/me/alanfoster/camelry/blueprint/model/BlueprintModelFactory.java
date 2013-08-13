package me.alanfoster.camelry.blueprint.model;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.model.impl.DomModelFactory;
import me.alanfoster.camelry.blueprint.dom.model.Blueprint;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BlueprintModelFactory extends DomModelFactory<Blueprint, IBlueprintDomModel, PsiElement> {

    protected BlueprintModelFactory(final Project project) {
        super(Blueprint.class, project, Blueprint.ROOT_ELEMENT_NAME);
    }

    @NotNull
    @Override
    protected List<IBlueprintDomModel> computeAllModels(@NotNull Module scope) {
        BlueprintManager blueprintManager = BlueprintManager.getInstance();
        final Project project = scope.getProject();
        Set<XmlFile> xmlFiles = blueprintManager.getModuleBlueprintConfigFiles(scope);

        List<IBlueprintDomModel> models = new ArrayList<IBlueprintDomModel>();

        final DomFileElement<Blueprint> mergedModelRoot = createMergedModelRoot(xmlFiles);
        if(mergedModelRoot != null) {
            models.add(new BlueprintDomModel(mergedModelRoot, xmlFiles));
        }

        return models;
    }

    @Override
    protected IBlueprintDomModel createCombinedModel(@NotNull Set<XmlFile> xmlFiles,
                                                     @NotNull DomFileElement<Blueprint> mergedModel,
                                                     IBlueprintDomModel blueprintModel,
                                                     Module scope) {
        return new BlueprintDomModel(mergedModel, xmlFiles);
    }
}
