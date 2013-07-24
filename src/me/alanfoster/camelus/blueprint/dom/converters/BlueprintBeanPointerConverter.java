package me.alanfoster.camelus.blueprint.dom.converters;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.ResolvingConverter;
import me.alanfoster.camelus.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelus.blueprint.model.BlueprintManager;
import me.alanfoster.camelus.blueprint.dom.model.BlueprintBean;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
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
public class BlueprintBeanPointerConverter extends ResolvingConverter<BlueprintBeanPointer> {

    @Nullable
    @Override
    public BlueprintBeanPointer fromString(final @Nullable @NonNls String blueprintRefId,
                          final ConvertContext context) {

        // Extra `blueprintRefId == null` added to stop IntelliJ's null warning
        if(StringUtil.isEmpty(blueprintRefId) || blueprintRefId == null) return null;

        final Module module = context.getModule();
        if(module == null) return null;

        // Find a blueprint bean with the same id as the reference id
        List<? extends BlueprintBeanPointer> blueprintBeans = getBlueprintPointers(module);
        BlueprintBeanPointer matchingBlueprintBean =  ContainerUtil.find(blueprintBeans, new Condition<BlueprintBeanPointer>() {
                @Override
                public boolean value(BlueprintBeanPointer blueprintBean) {
                    return blueprintRefId.equals(blueprintBean.getName().getStringValue());
                }
        });

        return matchingBlueprintBean;
    }

    @Nullable
    @Override
    public String toString(final @Nullable BlueprintBeanPointer blueprintBean,
                           final ConvertContext context) {
        return blueprintBean == null ? null : blueprintBean.getName().getStringValue();
    }

    @NotNull
    @Override
    public Collection<? extends BlueprintBeanPointer> getVariants(ConvertContext context) {
        return (context == null || context.getModule() == null)
                ? Collections.<BlueprintBean>emptyList()
                : getBlueprintPointers(context.getModule());
    }

    @NotNull
    public List<? extends BlueprintBeanPointer> getBlueprintPointers(@Nullable Module module) {
        if(module == null) return Collections.emptyList();

        final BlueprintManager blueprintManager = BlueprintManager.getInstance();
        List<? extends BlueprintBeanPointer> pointers = blueprintManager.getAllModuleBlueprintBeanPointers(module);
        return pointers;
    }

    @Override
    public void handleElementRename(final GenericDomValue<BlueprintBeanPointer> genericValue, final ConvertContext context,
                                    final String newElementName) {
        genericValue.setStringValue(newElementName);
    }

}
