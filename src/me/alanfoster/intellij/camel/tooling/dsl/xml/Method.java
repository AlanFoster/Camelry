package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.ide.presentation.Presentation;
import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.*;
import me.alanfoster.intellij.camel.icons.CamelIcons;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
//@Presentation(icon = CamelIcons.CAMEL_STRING)
public interface Method extends DomElement {
    //  If you still want to specify explicitly that your reference to DomElement should be resolved "model-wide",
    // use the @Resolve annotation parameterized with the desired class. The resolution scope will be taken from the
    // DomFileDescription.getResolveScope().
    @Resolve
    @NameValue
    @Attribute("bean")
    @Required
    GenericAttributeValue<Bean> getBean();

    @Attribute("method")
    @Required
    @Convert(MethodResolver.class)
    GenericAttributeValue<PsiMethod> getMethod();
}
