package me.alanfoster.camelus.blueprint.dom.action;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TestInputDialog;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import me.alanfoster.camelus.CamelusTestSupport;
import me.alanfoster.camelus.TestHelper;
import me.alanfoster.camelus.blueprint.dom.actions.BlueprintRefactoringSupport;

import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.CreateCamelusProject;
import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.blueprintFiles;
import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.javaFiles;

/**
 * Tests for ensuring the user can extract a hardcoded string into a property placeholder value.
 */
public class IntroducePropertyPlaceholderVariable extends CamelusTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/blueprint/dom/actions/refactoring";
    }

    public void testValueContainsOnlyText() {
        performTest("connectionFactory.url");
    }

    public void testValueContainsPrecedingPropertyText() {
        performTest("connectionFactory.username");
    }

    // TODO
    public void ignoreNoSelectionErrorMessage() {
        performTest();
    }

    // TODO
    public void ignoreNonTextRefactoringErrorMessage() {
        performTest();
    }

    // TODO
    public void ignoreValueContainsSucceedingPropertyTextWithNoexistingPropertyPlaceholder() {
        performTest();
    }

    // TODO
    public void ignoreUserCancelsRefactoring() {
        performTest();
    }


    private void performTest() {
        performTest("MyNewVar");
    }

    private void performTest(final String newPropertyName) {
        String resourceName = getTestName(false);

        CreateCamelusProject(myFixture)
                .with(blueprintFiles(resourceName + ".xml"))
                .withOpenedFileFromTempProject(blueprintFiles(resourceName + ".xml"))
                .with(javaFiles(
                        "me.alanfoster.camelus.blueprint.camel.dom.common",
                        commonFile("IConnectionFactory.java"), commonFile("ConnectionFactory.java"), commonFile("Connection.java")));

        // Override the input dialogue to input the requried name
        Messages.setTestInputDialog(new TestInputDialog() {
            @Override
            public String show(String message) {
                return newPropertyName;
            }
        });

        final PsiFile injectedFile = InjectedLanguageUtil.findInjectedPsiNoCommit(myFixture.getFile(), myFixture.getEditor().getSelectionModel().getSelectionStart());
        final Editor injectedEditor = InjectedLanguageUtil.getInjectedEditorForInjectedFile(myFixture.getEditor(), injectedFile);

        new BlueprintRefactoringSupport()
                .getIntroduceVariableHandler()
                .invoke(myFixture.getProject(), injectedEditor, injectedFile, null);

        myFixture.checkResultByFile(resourceName + "_after.xml");
    }


}
