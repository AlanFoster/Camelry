package me.alanfoster.intellij.blueprint.converters;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.ResolvingConverter;
import me.alanfoster.intellij.blueprint.dom.Blueprint;
import me.alanfoster.intellij.blueprint.dom.BlueprintBean;
import me.alanfoster.intellij.blueprint.model.IBlueprintDomModel;
import me.alanfoster.intellij.blueprint.model.IBlueprintManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * BlueprintBean converter. This class should resolve any BlueprintBean definitions
 * across the same OSGI-INF module.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
// TODO Look at CustomReferenceConverter
public class BlueprintBeanConverter extends ResolvingConverter<BlueprintBean> {

    @Nullable
    @Override
    public BlueprintBean fromString(final @Nullable @NonNls String blueprintRefId,
                          final ConvertContext context) {

        if(StringUtil.isEmpty(blueprintRefId)) return null;

        final Module module = context.getModule();
        if(module == null) return null;

        // Find a blueprint bean with the same id as the reference id
        List<BlueprintBean> blueprintBeans = getAllBlueprintBeans(module);
        BlueprintBean matchingBlueprintBean =  ContainerUtil.find(blueprintBeans, new Condition<BlueprintBean>() {
                @Override
                public boolean value(BlueprintBean blueprintBean) {
                return blueprintBean.getId().getStringValue().equals(blueprintRefId);
            }
        });

        return matchingBlueprintBean;
    }

    @Nullable
    @Override
    public String toString(final @Nullable BlueprintBean blueprintBean,
                           final ConvertContext context) {
        return blueprintBean == null ? null : blueprintBean.getId().getStringValue();
    }

    @NotNull
    @Override
    public Collection<? extends BlueprintBean> getVariants(ConvertContext context) {
        return getAllBlueprintBeans(context.getModule());
    }

    // TODO Place this into either the blueprint model or BlueprintManager
    public List<BlueprintBean> getAllBlueprintBeans(@NotNull Module module) {
        List<BlueprintBean> allBeans = new ArrayList<BlueprintBean>();

        final IBlueprintManager blueprintManager = IBlueprintManager.getInstance();
        final List<IBlueprintDomModel> blueprintModels = blueprintManager.getAllBlueprintModels(module);

        // There should be a single merged file
        if(blueprintModels.size() == 0) return null;
        IBlueprintDomModel model = blueprintModels.get(0);
        for(DomFileElement<Blueprint> domFile : model.getRoots()) {
            Blueprint blueprint = domFile.getRootElement();
            allBeans.addAll(blueprint.getBeans());
        }

        return allBeans;
    }
}
