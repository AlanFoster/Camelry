package me.alanfoster.camelus.blueprint.language.support;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import me.alanfoster.camelus.blueprint.dom.model.Property;
import me.alanfoster.camelus.blueprint.language.BlueprintInjectionLanguage;
import me.alanfoster.camelus.blueprint.language.psi.InjectionPropertyDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * FoldingBuilder for the blueprint injection language.
 * This class should inline the value of a property reference.
 */
public class InjectionFoldingBuilder extends FoldingBuilderEx {
    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quickFix) {
        // We need to create a new folding group per call
        FoldingGroup foldingGroup = FoldingGroup.newGroup(BlueprintInjectionLanguage.BLUEPRINT_INJECTION_LANGUAGE);

        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        Collection<InjectionPropertyDefinition> propertyDefinitions = PsiTreeUtil.findChildrenOfType(root, InjectionPropertyDefinition.class);
        for (InjectionPropertyDefinition definition : propertyDefinitions) {
            Property property = definition.getReferencedProperty();
            if (property == null) continue;
            descriptors.add(new InjectionFoldingDescriptor(definition, property, foldingGroup));
        }
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode astNode) {
        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        return true;
    }

    /**
     * Represents the basic folding descriptor used for a property place holder which
     * simply returns the referenced property placeholder's value.
     */
    private static final class InjectionFoldingDescriptor extends FoldingDescriptor {
        private final String propertyValue;

        public InjectionFoldingDescriptor(@NotNull InjectionPropertyDefinition definition,
                                          @NotNull Property reference,
                                          @NotNull FoldingGroup foldingGroup) {
            super(definition.getNode(), definition.getTextRange(), foldingGroup);
            propertyValue = reference.getValue().getStringValue();
        }

        @Nullable
        @Override
        public String getPlaceholderText() {
            return propertyValue;
        }
    }
}
