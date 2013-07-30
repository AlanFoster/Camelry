package me.alanfoster.camelry.blueprint.language.contributors;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.GenericAttributeValue;
import me.alanfoster.camelry.blueprint.language.file.InjectionFile;
import me.alanfoster.camelry.blueprint.language.file.InjectionFileType;
import me.alanfoster.camelry.blueprint.model.BlueprintManager;
import me.alanfoster.camelry.blueprint.dom.model.DefaultProperties;
import me.alanfoster.camelry.blueprint.dom.model.Property;
import me.alanfoster.camelry.blueprint.dom.model.PropertyPlaceholder;
import me.alanfoster.camelry.icons.PluginIcons;
import me.alanfoster.camelus.blueprint.language.psi.InjectionPropertyDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PsiReference for the Blueprint Injection Language. Allows users to ctrl+click a property,
 * rename elements, and get variants - i.e. intellisense with ctrl+space :)
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class InjectionPsiReference extends PsiReferenceBase<PsiElement> {
    private static final boolean IS_SOFT = false;
    private static final int TEXT_RANGE_START = 2;

    private String propertyName;


    public InjectionPsiReference(PsiElement element) {
        super(element, IS_SOFT);
        setPropertyName(element);
        setRangeInElement(new TextRange(TEXT_RANGE_START, TEXT_RANGE_START + propertyName.length()));
    }

    private String getPropertyName(PsiElement element) {
        return propertyName;
    }

    private void setPropertyName(PsiElement element) {
        propertyName = ((InjectionPropertyDefinition) element).getPropertyName();
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        PropertyPlaceholder propertyPlaceHolder = getModulePropertyPlaceHolder(myElement);
        if(propertyPlaceHolder == null) return null;

        final List<Property> defaultProperties = propertyPlaceHolder.getDefaultProperties().getProperties();
        final Property property = ContainerUtil.find(defaultProperties, new Condition<Property>() {
            @Override
            public boolean value(Property property) {
                final XmlAttributeValue xmlAttributeValue = property.getName().getXmlAttributeValue();
                return xmlAttributeValue != null && propertyName.equals(xmlAttributeValue.getValue());
            }
        });

        return property == null ?  null : property.getName().getXmlAttributeValue();
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        PropertyPlaceholder propertyPlaceHolder = getModulePropertyPlaceHolder(myElement);
        if(propertyPlaceHolder == null) return Collections.EMPTY_LIST.toArray();

        final DefaultProperties defaultProperties = propertyPlaceHolder.getDefaultProperties();
        List<LookupElement> resultSet = new ArrayList<LookupElement>();

        // Loop through all property elements from the default property placeholder and add
        // To the resultSet. There should only be cm:property-placeholder one per bundle.
        for(Property property : defaultProperties.getProperties()) {
            final GenericAttributeValue<String> name = property.getName();
            if(name.exists()) {
                XmlAttributeValue xmlAttributeValue = name.getXmlAttributeValue();
                if(xmlAttributeValue == null) continue;

                String attributeValueString = xmlAttributeValue.getValue();
                resultSet.add(
                        LookupElementBuilder.create(property, attributeValueString)
                                .withIcon(PluginIcons.BLUEPRINT)
                                .withPresentableText(attributeValueString)
                );
            }
        }

        return resultSet.toArray();
    }

    /**
     * Attempts to get the property placeholder for the given element within the element's module.
     *
     * @param psiElement
     * @return The property placeholder if found.
     */
    @Nullable
    private PropertyPlaceholder getModulePropertyPlaceHolder(PsiElement psiElement){
        final Module module = ModuleUtil.findModuleForPsiElement(psiElement);
        if(module == null) return null;
        final PropertyPlaceholder propertyPlaceHolder = BlueprintManager.getInstance().getModulePropertyPlaceHolder(module);
        return propertyPlaceHolder;
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        String newText = getRangeInElement().replace(myElement.getText(), newElementName);
        PsiElement replacementElement = getReplacementElement(myElement.getProject(), myElement.getContainingFile(), newText);
        return myElement.replace(replacementElement);
    }

    private PsiElement getReplacementElement(Project project, PsiFile psiFile, String newText)  {
        String tempFileName = "__" + psiFile.getName();
        InjectionFile fileFromText = (InjectionFile) PsiFileFactory.getInstance(project)
                .createFileFromText(tempFileName, InjectionFileType.INSTANCE, newText);
        return fileFromText.getFirstChild();
    }

}
