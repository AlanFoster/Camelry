package me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface BlueprintBean extends DomElement {
    // TODO deal with id @Scope
    @NameValue(unique = true)
    @Attribute("id")
    @NotNull
    @Required(nonEmpty = true, value = true)
    GenericAttributeValue<String> getId();

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
