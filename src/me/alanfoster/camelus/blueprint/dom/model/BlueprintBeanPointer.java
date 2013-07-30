package me.alanfoster.camelus.blueprint.dom.model;

import com.intellij.ide.presentation.Presentation;
import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;
import com.intellij.util.xml.Required;
import com.intellij.xml.util.PsiElementPointer;
import me.alanfoster.camelus.CamelryPresentationProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The interface of a referencable Blueprint element.
 * Useful in the scenario of BlueprintBean and BlueprintReference having
 * similar referenceability, but having different class/interface attributes.
 *
 * It is therefore preferrable that Converters resolve to this interface directly
 * rather than to a concrete BlueprintBean or BlueprintReference etc.
 */
@Presentation(provider = CamelryPresentationProvider.Blueprint.class)
public interface BlueprintBeanPointer extends PsiElementPointer {

    @Attribute("id")
    @NotNull
    @NameValue(unique = false)
    @Required(nonEmpty = true, value = true)
    GenericAttributeValue<String> getName();

    /**
     * This interface method does not rely on the DOM model.
     * Instead it should be implemented by a class which can have a
     * different annotation available to it.
     *
     * @return The PsiClass that this element references.
     *         This may be an interface, or it may be an concrete class.
     */
    @Nullable
    PsiClass getReferencedClass();
}
