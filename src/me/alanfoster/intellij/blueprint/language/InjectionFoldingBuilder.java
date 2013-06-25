package me.alanfoster.intellij.blueprint.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.xml.DomManager;
import com.intellij.util.xml.GenericAttributeValue;
import me.alanfoster.intellij.blueprint.dom.Property;
import me.alanfoster.intellij.blueprint.dom.PropertyPlaceholder;
import me.alanfoster.intellij.blueprint.language.contributors.InjectionPsiReference;
import me.alanfoster.intellij.blueprint.language.psi.InjectionPropertyDefinition;
import org.apache.xmlbeans.impl.common.XMLNameHelper;
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
        FoldingGroup foldingGroup = FoldingGroup.newGroup(BlueprintInjectionLanguage.BLUEPRINT_INJECTION_LANGUAGE);

        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        Collection<InjectionPropertyDefinition> propertyDefinitions = PsiTreeUtil.findChildrenOfType(root, InjectionPropertyDefinition.class);
        for(InjectionPropertyDefinition definition : propertyDefinitions) {
            if(definition != null) {
                Project project = definition.getProject();
                // TODO is there an easy way to resolve to a DomElement?
                // TODO Should this be placed this into the element call itself?
                PsiElement reference = new InjectionPsiReference(definition).resolve();
                if(!(reference instanceof XmlAttributeValue)) continue;
                XmlAttribute attribute = (XmlAttribute) reference.getParent();
                DomManager domManager = DomManager.getDomManager(project);
                GenericAttributeValue domElement = domManager.getDomElement(attribute);
                if(domElement == null) continue;
                Property property = domElement.getParentOfType(Property.class, true);
                if(property == null) continue;
                final GenericAttributeValue<String> value = property.getValue();
                if(!value.exists()) continue;
                descriptors.add(new FoldingDescriptor(definition.getNode(), definition.getTextRange(), foldingGroup) {
                    @Nullable
                    @Override
                    public String getPlaceholderText() {
                        return value.getStringValue();
                    }
                });
            }
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
}
