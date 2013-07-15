package me.alanfoster.camelus.blueprint.dom.model;

import com.intellij.ide.presentation.Presentation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.*;
import me.alanfoster.camelus.CamelusPresentationProvider;
import me.alanfoster.camelus.blueprint.dom.converters.BlueprintBeanMethodProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
@Presentation(provider = CamelusPresentationProvider.Blueprint.class)
public interface BlueprintBean extends DomElement, BlueprintBeanPointer {

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
    /**
     * We don't explicitly the class to be instantiable
     * As this isn't valid logic if using BeanArgument elements to provide constructor injection
     */
    @ExtendClass(instantiatable = false)
    GenericAttributeValue<PsiClass> getClassAttribute();

    @SubTagList("property")
    @NotNull
    List<BeanProperty> getBeanProperties();

    @SubTagList("argument")
    @NotNull
    List<BeanArgument> getBeanArguments();
}
