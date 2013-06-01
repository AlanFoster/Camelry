package me.alanfoster.intellij.blueprint.dom;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface BlueprintBean extends DomElement {
    // TODO Are we supposed to implement our own @NameValue resolver too, now that we have changed the Scope to include external models?
    @NameValue(unique = true)
    @Attribute("id")
    @NotNull
    @Required(nonEmpty = true, value = true)
    GenericAttributeValue<String> getId();

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
