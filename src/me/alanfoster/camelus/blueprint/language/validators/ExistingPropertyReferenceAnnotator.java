package me.alanfoster.camelus.blueprint.language.validators;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.xml.*;
import com.intellij.xml.util.XmlUtil;
import me.alanfoster.camelus.blueprint.dom.model.Blueprint;
import me.alanfoster.camelus.blueprint.dom.model.Property;
import me.alanfoster.camelus.blueprint.dom.model.PropertyPlaceholder;
import me.alanfoster.camelus.blueprint.language.contributors.InjectionPsiReference;
import me.alanfoster.camelus.blueprint.language.psi.InjectionPropertyDefinition;
import me.alanfoster.camelus.blueprint.model.BlueprintManager;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import static me.alanfoster.camelus.CamelusBundle.message;

/**
 * Provides an error annotation when a referenced Blueprint property value does not exist.
 * This error annotator also provides a basic quickfix which will create the property.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class ExistingPropertyReferenceAnnotator implements Annotator {
    public static Logger logger = Logger.getInstance(ExistingPropertyReferenceAnnotator.class);

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof InjectionPropertyDefinition) {
            InjectionPropertyDefinition injectionPropertyDefinition = (InjectionPropertyDefinition) element;
            final PsiElement propertyName = injectionPropertyDefinition.getPropertyElement();

            if (!hasReference(element)) {
                int propertyEndOffset = injectionPropertyDefinition.getTextRange().getEndOffset();
                holder.createErrorAnnotation(propertyName, getMessageErrorMessage(injectionPropertyDefinition.getPropertyName()))
                        .registerFix(new CreatePropertyQuickFix(propertyEndOffset, injectionPropertyDefinition.getName()));
            }
        }
    }

    private String getMessageErrorMessage(String referencedElement) {
        return message("camelus.blueprint.language.missing.property.error", referencedElement);
    }

    private boolean hasReference(PsiElement psiElement) {
        // TODO Perhaps we should add a hasReference method to the InjectionPropertyDefinition instead?
        return new InjectionPsiReference(psiElement).resolve() != null;
    }

    public static class CreatePropertyQuickFix extends BaseIntentionAction {
        /**
         * The name of the property to create within the module if this intention
         * is invoked.
         */
        private final String propertyName;
        private final int propertyEndOffset;

        public CreatePropertyQuickFix(int propertyEndOffset, String propertyName) {
            this.propertyName = propertyName;
            this.propertyEndOffset = propertyEndOffset;
        }

        public String getPropertyName() {
            return propertyName;
        }

        @NotNull
        @Override
        public String getText() {
            return message("camelus.blueprint.language.quickfix.missing.property.text", propertyName);
        }

        @NotNull
        @Override
        public String getFamilyName() {
            return message("camelus.blueprint.language.quickfix.missing.property.family.name");
        }

        @Override
        public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
            return true;
        }

        @Override
        public void invoke(@NotNull final Project project, final Editor editor, final PsiFile file) throws IncorrectOperationException {
            // Ask the user for the property value. Null/Empty implies the user closed the window.
            final String propertyValue = getPropertyValue(project);
            if (StringUtils.isEmpty(propertyValue)) return;

            // Boilerplate setup for write action
            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                @Override
                public void run() {
                    CommandProcessor.getInstance().executeCommand(project, new Runnable() {
                        @Override
                        public void run() {
                            writeActionInvoke(project, editor, file, propertyName, propertyValue);
                        }
                    }, message("camelus.blueprint.language.quickfix.missing.property.undo.message"), project);
                }
            });

        }

        /**
         * To be called when write access is granted; Creates a new property with the given property name, and a default value.
         * If the property placeholder does not exist, then we should create one within the same file.
         */
        private void writeActionInvoke(@NotNull final Project project, final Editor editor, final PsiFile file,
                                       @NotNull final String propertyName, @NotNull String propertyValue) {
            Module module = ModuleUtil.findModuleForPsiElement(file);
            if (module == null) {
                logger.error("Module value was null for psifile - Possibly due to an in memory editor?");
                return;
            }

            Property property = ExistingPropertyReferenceAnnotator.createNewProperty(module, file, propertyName, propertyValue);

            // Place the caret directly after the property ie "${foo}<caret>"
            editor.getCaretModel().moveToOffset(propertyEndOffset);
        }

        private String getPropertyValue(@NotNull Project project) {
            return Messages.showInputDialog(project,
                    message("camelus.blueprint.language.quickfix.missing.property.input.dialog.message"),
                    message("camelus.blueprint.language.quickfix.missing.property.input.dialog.title"),
                    Messages.getQuestionIcon()
            );
        }
    }

    public static Property createNewProperty(@NotNull Module module, @NotNull PsiFile psiFile, @NotNull String propertyName, @NotNull String propertyValue) {
        BlueprintManager blueprintManager = BlueprintManager.getInstance();
        PropertyPlaceholder placeholder = blueprintManager.getModulePropertyPlaceHolder(module);
        if (placeholder == null) {
            placeholder = createNewPropertyPlaceholder(psiFile.getProject(), module, psiFile);
        }

        Property property = placeholder.getDefaultProperties().addProperty();
        property.getName().setStringValue(propertyName);
        property.getValue().setStringValue(propertyValue);

        // Force a reformat of the property placeholder element, to put the new element on a new line
        CodeStyleManager.getInstance(module.getProject())
                .reformat(placeholder.getXmlTag());

        return property;
    }

    /**
     * Creates a new Propertyplaceholder element within the given file as the very first element.
     */
    private static PropertyPlaceholder createNewPropertyPlaceholder(Project project, Module module, PsiFile file) {
        XmlFile xmlFile = XmlUtil.getContainingFile(file);
        DomManager domManager = DomManager.getDomManager(project);
        DomFileDescription<?> domFileDescription = domManager.getDomFileDescription(xmlFile);
        DomFileElement<Blueprint> blueprint = domManager.getFileElement(xmlFile, Blueprint.class);
        assert blueprint != null : "The annotator only applies to Blueprint XML files, the doElement should not be null";

        String persistentId = module.getName();
        PropertyPlaceholder propertyPlaceholder = blueprint.getRootElement().getPropertyPlaceHolder();
        propertyPlaceholder.getPersistentId().setStringValue(persistentId);

        return propertyPlaceholder;
    }

}
