package me.alanfoster.camelry.blueprint.dom.actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Trinity;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlFile;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import com.intellij.xml.util.XmlUtil;
import me.alanfoster.camelry.blueprint.language.file.InjectionFileType;
import me.alanfoster.camelry.blueprint.dom.model.BeanProperty;
import me.alanfoster.camelry.blueprint.dom.model.Property;
import me.alanfoster.camelry.blueprint.language.InjectionTypes;
import me.alanfoster.camelry.blueprint.language.file.InjectionFile;
import me.alanfoster.camelry.blueprint.language.psi.InjectionPropertyDefinition;
import me.alanfoster.camelry.blueprint.language.validators.ExistingPropertyReferenceAnnotator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.patterns.DomPatterns.domElement;
import static com.intellij.patterns.XmlPatterns.xmlAttributeValue;
import static me.alanfoster.camelry.CamelryBundle.message;

/**
 * Introduces a property placeholder variable when a non-property value has been selected.
 * For instance "${existingProperty} <selection>new property</selection>" will create a new
 * user defined property with the value of 'new property'
 */
public class IntroducePropertyPlaceholderRefactoring implements RefactoringActionHandler {
    public static Logger logger = Logger.getInstance(IntroducePropertyPlaceholderRefactoring.class);

    /**
     *
     * @param project
     * @param editor The editor for the blueprint injection file
     * @param psiFile The BlueprintInjection psi file
     * @param dataContext
     */
    @Override
    public void invoke(final @NotNull Project project, final Editor editor, final PsiFile psiFile, final DataContext dataContext) {
        final Module module = ModuleUtil.findModuleForPsiElement(psiFile);
        if(module == null) {
            logger.info("The module must not be null for invoking a refactoring - potentially we are using an in memory editor?");
            return;
        }

        boolean isValid = performValidation(project, editor, psiFile);
        if (!isValid) return;

        String suggestedPropertyName = getSuggestedPropertyName(editor, psiFile);

        final String propertyName = getPropertyName(project, suggestedPropertyName);
        if (StringUtil.isEmpty(propertyName)) return;

        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
                CommandProcessor.getInstance().executeCommand(project, new Runnable() {
                    @Override
                    public void run() {
                        writeActionInvoke(project, module, editor, psiFile, propertyName);
                    }
                }, message("camelry.blueprint.language.quickfix.missing.property.undo.message"), project);
            }
        });
    }


    /**
     * Attempts to find an appropriate default suggested property name.
     * @param editor The editor for the blueprint injection file
     * @param psiFile The BlueprintInjection psi file
     * @return A property/argument bean name value;
     *         Otherwise the default property name from the default resource bundle
     */
    @NotNull
    protected String getSuggestedPropertyName(Editor editor, PsiFile psiFile) {
        String defaultPropertyName = message("camelry.blueprint.language.refactoring.introduce.variable.get.property.name.default");

        // Extract the Xml file + xml file editor
        XmlFile xmlFile = XmlUtil.getContainingFile(psiFile);
        Editor topLevelEditor = InjectedLanguageUtil.getTopLevelEditor(editor);

        // TODO We could also grab the transitive argument value name too, as a possible suggestion
        String propertyName = getPropertyName(xmlFile, topLevelEditor);
        return propertyName == null ? defaultPropertyName : propertyName;
    }

    /**
     * Attempt to extract the parent Property name value from the injection location
     * @param xmlFile The xml file
     * @param editor The editor
     * @return The property name of the given injected XmlAttribute parent
     */
    @Nullable
    private String getPropertyName(@NotNull XmlFile xmlFile, @NotNull Editor editor) {
        int selectionStart = editor.getSelectionModel().getSelectionStart();
        XmlAttribute injectedXmlAttribute = PsiTreeUtil.getParentOfType(xmlFile.findElementAt(selectionStart), XmlAttribute.class);
        DomElement domElement = DomUtil.getDomElement(injectedXmlAttribute);
        BeanProperty property = DomUtil.getParentOfType(domElement, BeanProperty.class, true);

        if(property == null) return null;

        return property.getName().getStringValue();
    }


    private boolean performValidation(final @NotNull Project project, final Editor editor, final PsiFile psiFile) {
        final SelectionModel selectionModel = editor.getSelectionModel();

        if (!selectionModel.hasSelection()) {
            CommonRefactoringUtil
                    .showErrorHint(project, editor,
                            message("camelry.blueprint.language.refactoring.introduce.variable.errors.no.selection.message"),
                            message("camelry.blueprint.language.refactoring.introduce.variable.errors.no.selection.title"),
                            null);
            return false;
        }

        if (!isTextOnlySelection(psiFile, selectionModel)) {
            CommonRefactoringUtil
                    .showErrorHint(project, editor,
                            message("camelry.blueprint.language.refactoring.introduce.variable.errors.text.only.message"),
                            message("camelry.blueprint.language.refactoring.introduce.variable.errors.text.only.title"),
                            null);
            return false;
        }

        return true;
    }

    /**
     * @param psiFile
     * @param selectionModel
     * @return True if the selection contains a single InjectionTypes.TEXT instance, false otherwise.
     */
    private boolean isTextOnlySelection(@NotNull PsiFile psiFile, @NotNull SelectionModel selectionModel) {
        PsiElement start = psiFile.findElementAt(selectionModel.getSelectionStart());
        PsiElement stop = psiFile.findElementAt(selectionModel.getSelectionEnd() - 1);

        if (start == null || stop == null) return false;
        if (start != stop) return false;
        if (!(start.getNode().getElementType() == InjectionTypes.TEXT)) return false;

        return true;
    }

    /**
     * This method should only be called when write action has been permitted.
     */
    private void writeActionInvoke(final Project project, final Module module, final Editor editor,
                                   final PsiFile psiFile, @NotNull String propertyName) {
        SelectionModel selectionModel = editor.getSelectionModel();
        PsiElement textElement = psiFile.findElementAt(selectionModel.getSelectionStart());
        assert textElement != null : "The blueprint injection textElement instance should not be null";

        int textElementOffset = textElement.getTextOffset();
        Trinity<String, String, String> splitTrinity = getSplitTrinity(selectionModel, textElement.getTextOffset(), textElement.getText());

        createNewProperty(module, psiFile, propertyName, splitTrinity.getSecond());
        PsiElement newInjectionElements = updateExistingText(project, psiFile, textElement, splitTrinity.getFirst(), propertyName, splitTrinity.getThird());

        updateCaret(editor, newInjectionElements, textElementOffset);
    }

      /*
      TODO Maybe look into using template builder for cm property placeholders
      InjectionPropertyDefinition newInjectionProperty = PsiTreeUtil.findChildOfType(replacedElement, InjectionPropertyDefinition.class);
        assert newInjectionProperty != null : "The replaced element should contain the new InjectionPropertyDefinition";
        PsiElement nameIdentifier = newInjectionProperty.getNameIdentifier();

      TemplateBuilderImpl templateBuilder = new TemplateBuilderImpl(newInjectionProperty);
        templateBuilder.replaceElement(nameIdentifier, nameIdentifier.getText());
        //templateBuilder.setEndVariableAfter(newProperty.getXmlElement());
        Template template = templateBuilder.buildTemplate();
        TemplateManager.getInstance(project).startTemplate(editor, template);

            //   VariableInplaceRenamer variableInplaceRenameHandler = new VariableInplaceRenamer(newInjectionProperty, editor);
    //  variableInplaceRenameHandler.performInplaceRename();

        TemplateBuilderImpl templateBuilder = new TemplateBuilderImpl(newProperty.getXmlElement());
        templateBuilder.setEndVariableAfter(newProperty.getXmlElement());
        Template template = templateBuilder.buildTemplate();
        TemplateManager.getInstance(project).startTemplate(editor, template);*/

    /**
     * Clears the selected text and places the caret directly after the created injection property.
     * @param editor
     * @param newInjectionElements The newly created elements which will contain the injection property
     */
    private void updateCaret(Editor editor, PsiElement newInjectionElements, int previousOffset) {
        InjectionPropertyDefinition newInjectionProperty = PsiTreeUtil.findChildOfType(newInjectionElements, InjectionPropertyDefinition.class);
        assert newInjectionProperty != null : "An InjectionPropertyDefinition should have existed within the newInjectionElements '" + newInjectionElements + "'";
        editor.getSelectionModel().removeSelection();
        editor.getCaretModel().moveToOffset(previousOffset + newInjectionProperty.getTextRange().getEndOffset());
    }

    @Nullable
    private String getPropertyName(Project project, String defaultProperty) {
        return Messages.showInputDialog(project,
                message("camelry.blueprint.language.refactoring.introduce.variable.get.property.name.message"),
                message("camelry.blueprint.language.refactoring.introduce.variable.get.property.name.title"),
                Messages.getQuestionIcon(),
                defaultProperty,
                null
        );
    }

    private Property createNewProperty(Module module, PsiFile psiFile, String propertyName, String propertyValue) {
        return ExistingPropertyReferenceAnnotator.createNewProperty(module, psiFile, propertyName, propertyValue);
    }

    /**
     * Replaces the existing text with the new property value reference.
     * @return The newly created parent PsiElement, which will contain the created children
     */
    private PsiElement updateExistingText(Project project, PsiFile psiFile,
                                          PsiElement oldElement,
                                          String precedingText, String propertyName, String succeedingText) {
        // Create the new value to replace the old element; IE "... ${newPropertyName} ..."
        String newValue =
                new StringBuilder()
                        .append(precedingText)
                        .append("${").append(propertyName).append("}")
                        .append(succeedingText)
                        .toString();

        PsiElement replacementElements = getReplacementPsiElement(project, psiFile, newValue);
        oldElement.replace(replacementElements);

        return replacementElements;
    }

    /**
     * Get a split trinity of the selected text.
     *
     * @param selectionModel
     * @param elementAsText
     * @return The first value will be proceeding value of the selection
     *         The second value will be selected text
     *         The third value will be the succeeding value of the selection.
     *         Note, an empty string will be returned if the left or right values there is no preceeding/succeeeding text.
     */
    private Trinity<String, String, String> getSplitTrinity(@NotNull SelectionModel selectionModel, int elementOffset, @NotNull String elementAsText) {
        int beginIndex = selectionModel.getSelectionStart() - elementOffset;
        int endIndex = selectionModel.getSelectionEnd() - elementOffset;

        String leftHandSide = elementAsText.substring(0, beginIndex);
        String propertyValue = elementAsText.substring(beginIndex, endIndex);
        String rightHandSide = elementAsText.substring(endIndex);

        return new Trinity<String, String, String>(leftHandSide, propertyValue, rightHandSide);
    }

    private PsiElement getReplacementPsiElement(Project project, PsiFile psiFile, String newText) {
        String tempFileName = "__" + psiFile.getName();
        InjectionFile fileFromText = (InjectionFile) PsiFileFactory.getInstance(project)
                .createFileFromText(tempFileName, InjectionFileType.INSTANCE, newText);
        return fileFromText;
    }

    @Override
    public void invoke(@NotNull Project project, @NotNull PsiElement[] elements, DataContext dataContext) {
        throw new UnsupportedOperationException();
    }
}
