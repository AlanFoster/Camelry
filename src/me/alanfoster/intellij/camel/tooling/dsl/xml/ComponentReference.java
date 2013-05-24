package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.util.xml.GenericDomValue;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class ComponentReference{
    private GenericDomValue<ComponentDefinition> value;

    /*public ComponentReference(GenericDomValue<ComponentDefinition> value, PsiElement element, TextRange range, boolean soft) {
        super(element, range, soft);
        this.value = value;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        String componentString = value.getRawText();


        return null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }*/
}
