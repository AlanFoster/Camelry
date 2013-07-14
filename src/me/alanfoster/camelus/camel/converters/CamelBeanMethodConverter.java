package me.alanfoster.camelus.camel.converters;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.converters.AbstractMethodParams;
import com.intellij.util.xml.converters.AbstractMethodResolveConverter;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import me.alanfoster.camelus.blueprint.dom.BlueprintBeanPointer;
import me.alanfoster.camelus.camel.dom.CamelBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class CamelBeanMethodConverter extends BeanPointerMethodConverter<CamelBean> {

    public CamelBeanMethodConverter() {
        super(CamelBean.class);
    }

    @Override
    public BlueprintBeanPointer getBlueprintBeanPointer(CamelBean parent) {
        return parent.getRef().getValue();
    }

}