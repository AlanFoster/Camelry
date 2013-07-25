package me.alanfoster.camelus.blueprint.dom.actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Trinity;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import me.alanfoster.camelus.blueprint.dom.model.Property;
import me.alanfoster.camelus.blueprint.language.InjectionTypes;
import me.alanfoster.camelus.blueprint.language.file.InjectionFile;
import me.alanfoster.camelus.blueprint.language.file.InjectionFileType;
import me.alanfoster.camelus.blueprint.language.validators.ExistingPropertyReferenceAnnotator;
import org.jetbrains.annotations.NotNull;

import static me.alanfoster.camelus.CamelusBundle.message;

/**
 * Introduces a property placeholder variable when a non-property value has been selected.
 * For instance "${existingProperty} <selection>new property</selection>" will create a new
 * user defined property with the value of 'new property'
 */
public class IntroducePropertyPlaceholderRefactoring implements RefactoringActionHandler {
    @Override
    public void invoke(final @NotNull Project project, final Editor editor, final PsiFile psiFile, final DataContext dataContext) {
        final SelectionModel selectionModel = editor.getSelectionModel();
        final Module module = ModuleUtil.findModuleForPsiElement(psiFile);

        assert module != null : "The module must not be null for invoking a refactoring - potentially we are using an in memory editor?";

        if (!selectionModel.hasSelection()) {
            CommonRefactoringUtil
                    .showErrorHint(project, editor,
                            message("camelus.blueprint.language.refactoring.introduce.variable.errors.no.selection.message"),
                            message("camelus.blueprint.language.refactoring.introduce.variable.errors.no.selection.title"),
                            null);
            return;
        }

        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
                CommandProcessor.getInstance().executeCommand(project, new Runnable() {
                    @Override
                    public void run() {
                        writeActionInvoke(project, module, editor, psiFile);
                    }
                }, message("camelus.blueprint.language.quickfix.missing.property.undo.message"), project);
            }
        });
    }

    /**
     * This method should only be called when write action has been permitted.
     */
    private void writeActionInvoke(final Project project, final Module module, final Editor editor, final PsiFile psiFile) {
        SelectionModel selectionModel = editor.getSelectionModel();

        PsiElement start = psiFile.findElementAt(selectionModel.getSelectionStart());
        PsiElement stop = psiFile.findElementAt(selectionModel.getSelectionEnd() - 1);

        if (start == null || stop == null) return;
        if (start != stop) return;
        if(!(start.getNode().getElementType() == InjectionTypes.TEXT)) return;

        Trinity<String, String, String> splitTrinity = getSplitTrinity(selectionModel, start.getTextOffset(), start.getText());

        String propertyName = "MyNewVar";
        createNewProperty(module, psiFile, propertyName, splitTrinity.getSecond());
        updateExistingText(project, psiFile, start, splitTrinity.getFirst(), propertyName, splitTrinity.getThird());
    }

    private Property createNewProperty(Module module, PsiFile psiFile, String propertyName, String propertyValue) {
        return ExistingPropertyReferenceAnnotator.createNewProperty(module, psiFile, propertyName, propertyValue);
    }

    /**
     * Replaces the existing text with the new property value reference.
     */
    private void updateExistingText(Project project, PsiFile psiFile,
                                    PsiElement oldElement,
                                    String precedingText,  String propertyName, String succeedingText) {
        // Create the new value to replace the old element; IE "... ${newPropertyName} ..."
        String newValue =
                new StringBuilder()
                        .append(precedingText)
                            .append("${").append(propertyName).append("}")
                        .append(succeedingText)
                        .toString();

        PsiElement replacementElements = getReplacementPsiElement(project, psiFile, newValue);
        oldElement.replace(replacementElements);
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
