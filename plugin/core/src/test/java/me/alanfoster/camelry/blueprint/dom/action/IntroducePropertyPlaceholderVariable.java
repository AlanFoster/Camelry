package me.alanfoster.camelry.blueprint.dom.action;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TestInputDialog;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import junit.framework.Assert;
import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.TestHelper;
import me.alanfoster.camelry.blueprint.dom.actions.BlueprintRefactoringSupport;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.CreateCamelryProject;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.javaFiles;

/**
 * Tests for ensuring the user can extract a hardcoded string into a property placeholder value.
 */
public class IntroducePropertyPlaceholderVariable extends CamelryTestSupport {

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

    public void testNoSelectionErrorMessage() {
        performTestWithError("Cannot perform refactoring.\nPlease select the elements you wish to extract.");
    }

    public void testNonTextRefactoringErrorMessage() {
        performTestWithError("Cannot perform refactoring.\nPlease select a text value only.");
    }

    public void testValueContainsSucceedingPropertyTextWithNoExistingPropertyPlaceholder() {
        performTest();
    }

    public void testUserCancelsRefactoring() {
        performTest(null);
    }

    public void testUserEntersEmptyString() {
        performTest("");
    }

    private void performTest() {
        performTest("MyNewVar");
    }


    private void performTestWithError(String expectedErrorMessage) {
        try {
            performTest();
            fail("The test should fail with the error message :: " + expectedErrorMessage);
        } catch(CommonRefactoringUtil.RefactoringErrorHintException e) {
            Assert.assertEquals("The refactoring error message should be as expected",
                    expectedErrorMessage, e.getMessage());
        }
    }

    private void performTest(final String newPropertyName) {
        String resourceName = getTestName(false);

        CreateCamelryProject(myFixture)
                .with(blueprintFiles(resourceName + ".xml"))
                .withOpenedFileFromTempProject(blueprintFiles(resourceName + ".xml"))
                .with(javaFiles(
                        "me.alanfoster.camelry.common",
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
