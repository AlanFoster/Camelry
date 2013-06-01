package me.alanfoster.intellij.blueprint.model;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.model.impl.DomModelFactory;
import me.alanfoster.intellij.blueprint.dom.Blueprint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    @Override
    protected List<IBlueprintDomModel> computeAllModels(@NotNull Module scope) {
        IBlueprintManager blueprintManager = IBlueprintManager.getInstance();
        Set<XmlFile> xmlFiles = blueprintManager.getAllBlueprintConfigFiles(scope);

        List<IBlueprintDomModel> models = new ArrayList<IBlueprintDomModel>();

        models.add(new BlueprintDomModel(createMergedModelRoot(xmlFiles), xmlFiles));

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
