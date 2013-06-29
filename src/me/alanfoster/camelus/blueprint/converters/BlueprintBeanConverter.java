package me.alanfoster.camelus.blueprint.converters;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.ResolvingConverter;
import me.alanfoster.camelus.blueprint.dom.Blueprint;
import me.alanfoster.camelus.blueprint.model.IBlueprintDomModel;
import me.alanfoster.camelus.blueprint.model.IBlueprintManager;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * BlueprintBean converter. This class will resolve all BlueprintBean definitions
 * across the same OSGI-INF module.
 *
 * To resolve blueprint beans which extend extend the Throwable class  see {@link ThrowableBlueprintBeanConverter}
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
    @NotNull
    public List<BlueprintBean> getAllBlueprintBeans(@NotNull Module module) {
        List<BlueprintBean> allBeans = new ArrayList<BlueprintBean>();

        final IBlueprintManager blueprintManager = IBlueprintManager.getInstance();
        final IBlueprintDomModel mergedBlueprintModel = blueprintManager.getMergedBlueprintModel(module);

        // There should be a single merged file
        if(mergedBlueprintModel == null) {
            return allBeans;
        }

        for(DomFileElement<Blueprint> domFile : mergedBlueprintModel.getRoots()) {
            Blueprint blueprint = domFile.getRootElement();
            allBeans.addAll(blueprint.getBeans());
        }

        return allBeans;
    }
}
