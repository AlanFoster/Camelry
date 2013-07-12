package me.alanfoster.camelus.blueprint.dom;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.*;
import me.alanfoster.camelus.blueprint.converters.BlueprintBeanMethodProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface BlueprintBean extends DomElement {
    @Attribute("id")
    @NotNull
    @Required(nonEmpty = true, value = true)
            @Scope(ScopeProvider.class)
    GenericAttributeValue<String> getName();

    @Attribute("init-method")
    @NotNull
    @Convert(BlueprintBeanMethodProvider.class)
    GenericAttributeValue<PsiMethod> getInitMethod();

    @Attribute("destroy-method")
    @NotNull
    @Convert(BlueprintBeanMethodProvider.class)
    GenericAttributeValue<PsiMethod> getDestroyMethod();

    @Attribute("factory-method")
    @NotNull
    @Convert(BlueprintBeanMethodProvider.class)
    GenericAttributeValue<PsiMethod> getFactoryMethod();

    @Attribute("class")
    @NotNull
    @Required(nonEmpty = true, value = true)
    GenericAttributeValue<PsiClass> getClassAttribute();

    @SubTagList("property")
    @NotNull
    List<BeanProperty> getBeanProperties();

    @SubTagList("argument")
    @NotNull
    List<BeanArgument> getBeanArguments();
}
