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
import com.intellij.util.IncorrectOperationException;
import me.alanfoster.camelus.blueprint.dom.model.Property;
import me.alanfoster.camelus.blueprint.dom.model.PropertyPlaceholder;
import me.alanfoster.camelus.blueprint.language.contributors.InjectionPsiReference;
import me.alanfoster.camelus.blueprint.language.psi.InjectionPropertyDefinition;
import me.alanfoster.camelus.blueprint.model.BlueprintManager;
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

    private static class CreatePropertyQuickFix extends BaseIntentionAction {
        /**
         * The name of the property to create within the module if this intention
         * is invoked.
         */
        private final String propertyName;
        private int propertyEndOffset;

        public CreatePropertyQuickFix(int propertyEndOffset, String propertyName) {
            this.propertyName = propertyName;
            this.propertyEndOffset = propertyEndOffset;
        }

        @NotNull
        @Override
        public String getText() {
            return message("camelus.blueprint.language.quickfix.missing.property.text");
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
            // Ask the user for the property value. Null implies the user closed the window.
            final String propertyValue = getPropertyValue(project);
            if(propertyValue == null) return;

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

            BlueprintManager blueprintManager = BlueprintManager.getInstance();
            PropertyPlaceholder modulePropertyPlaceHolder = blueprintManager.getModulePropertyPlaceHolder(module);
            if (modulePropertyPlaceHolder == null) {
                // TODO Create a new default property placeholder within the same file, or a dedicated file.
                return;
            }

            Property property = modulePropertyPlaceHolder.getDefaultProperties().addProperty();
            property.getName().setStringValue(propertyName);
            property.getValue().setStringValue(propertyValue);

            // Place the caret directly after the property ie "${foo}<caret>"
            editor.getCaretModel().moveToOffset(propertyEndOffset);

            // Force a reformat of the property placeholder element, to put the new element on a new line
            CodeStyleManager.getInstance(project)
                    .adjustLineIndent(file, modulePropertyPlaceHolder.getXmlTag().getTextRange());
        }

        private String getPropertyValue(@NotNull Project project) {
            return Messages.showInputDialog(project,
                    message("camelus.blueprint.language.quickfix.missing.property.input.dialog.message"),
                    message("camelus.blueprint.language.quickfix.missing.property.input.dialog.title"),
                    Messages.getQuestionIcon()
            );
        }
    }
}
