package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.psi.PsiClass;
import com.intellij.util.xml.Attribute;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.NameValue;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface Bean extends DomElement {
    /**
     * {@link http://devnet.jetbrains.com/message/5270329#5270329}
     */
   // @NameValue
    @Attribute("id")
    GenericAttributeValue<PsiClass> getId();

    @Attribute("class")
    @NotNull
    GenericAttributeValue<PsiClass> getClassAttribute();


}
