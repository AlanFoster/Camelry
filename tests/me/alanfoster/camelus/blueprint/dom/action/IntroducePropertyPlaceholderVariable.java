package me.alanfoster.camelus.blueprint.dom.action;

import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import me.alanfoster.camelus.CamelusTestSupport;
import me.alanfoster.camelus.TestHelper;
import me.alanfoster.camelus.blueprint.dom.actions.BlueprintRefactoringSupport;

import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.CreateCamelusProject;

/**
 * Tests for ensuring the user can extract a hardcoded string into a property placeholder value.
 */
public class IntroducePropertyPlaceholderVariable extends CamelusTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/blueprint/dom/actions/refactoring";
    }

    public void testValueContainsOnlyText() {
        performTest();
    }

    public void testValueContainsPrecedingPropertyText() {
        performTest();
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
        String resourceName = getTestName(false);

        CreateCamelusProject(myFixture)
                .withBlueprintFiles(resourceName + ".xml")
                .withOpenedFile(resourceName + ".xml")
                .withJavaFiles(
                        "me.alanfoster.camelus.blueprint.camel.dom.common",
                        commonFile("IConnectionFactory.java"), commonFile("ConnectionFactory.java"), commonFile("Connection.java"));

        // TODO Language injection doesn't seem to happen automatically in tests
        Editor editor = myFixture.getEditor();
        PsiFile injectedFile = InjectedLanguageUtil.findInjectedPsiNoCommit(myFixture.getFile(), editor.getSelectionModel().getSelectionStart());
        Editor injectedEditor = InjectedLanguageUtil.getInjectedEditorForInjectedFile(editor, injectedFile);

        new BlueprintRefactoringSupport()
                .getIntroduceVariableHandler()
                .invoke(myFixture.getProject(), injectedEditor, injectedFile, null);

        myFixture.checkResultByFile(resourceName + "_after.xml");
    }
}
