package me.alanfoster.camelus.blueprint.language.validators;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TestInputDialog;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.intellij.testFramework.fixtures.impl.CodeInsightTestFixtureImpl;
import com.intellij.util.containers.ContainerUtil;
import me.alanfoster.camelus.TestHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static me.alanfoster.camelus.CamelryProjectDescriptorBuilder.CreateCamelusProject;
import static me.alanfoster.camelus.CamelryProjectDescriptorBuilder.blueprintFiles;
import static me.alanfoster.camelus.CamelryProjectDescriptorBuilder.javaFiles;
import static me.alanfoster.camelus.CamelryTestSupport.commonFile;

/**
 * Tests that the user is notified when property definitions don't exist within
 * the blueprint language and provides a quickfix to create a new property
 * placeholder element.
 */
public class ExistingPropertyDefinitionTest extends LightCodeInsightFixtureTestCase {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/blueprint/language/validators/propertyDefinition";
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testMissingPropertiesHighlightedAsErrors() {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles("MissingPropertiesHighlightedAsErrors.xml"))
                .withOpenedFileFromTempProject(blueprintFiles("MissingPropertiesHighlightedAsErrors.xml"))
                .with(javaFiles("me.alanfoster.camelus.blueprint.camel.dom.common", commonFile("Person.java")));

        myFixture.checkHighlighting(false, false, true);
    }

    public void testQuickfixUserCancels() {
        performQuickfix("username", null);
    }

    public void testQuickfixMissingPropertyWithNoPreviousPlaceholder() {
        performQuickfix("timeout", "10");
    }

    public void testQuickfixMissingProperty() {
        performQuickfix("username", "alan");
    }

    private void performQuickfix(final String propertyToFix, final String newPropertyValue) {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles(getTestName(false) + ".xml"))
                .withOpenedFileFromTempProject(blueprintFiles(getTestName(false) + ".xml"))
                .with(javaFiles("me.alanfoster.camelus.blueprint.camel.dom.common", commonFile("Person.java")));

        List<IntentionAction> allQuickFixes = getAllQuickFixes();

        IntentionAction intentionAction = ContainerUtil.find(allQuickFixes, new Condition<IntentionAction>() {
            @Override
            public boolean value(IntentionAction intentionAction) {
                return "Create Blueprint Property".equals(intentionAction.getText())
                        && propertyToFix.equals(((ExistingPropertyReferenceAnnotator.CreatePropertyQuickFix) intentionAction).getPropertyName());
            }
        });

        Messages.setTestInputDialog(new TestInputDialog() {
            @Override
            public String show(String message) {
                return newPropertyValue;
            }
        });

        final PsiFile injectedFile = InjectedLanguageUtil.findInjectedPsiNoCommit(myFixture.getFile(), myFixture.getEditor().getSelectionModel().getSelectionStart());
        final Editor injectedEditor = InjectedLanguageUtil.getInjectedEditorForInjectedFile(myFixture.getEditor(), injectedFile);

        assertNotNull(intentionAction);
        intentionAction.invoke(myFixture.getProject(), injectedEditor, injectedFile);

        myFixture.checkResultByFile(getTestName(false) + "_after.xml");
    }

    // TODO Added a custom getAllQuickFixes to stop NPE in intellij - Is this a bug within the intellij code, or are the quick fixes not registered properly?
    @NotNull
    private List<IntentionAction> getAllQuickFixes() {
        List<HighlightInfo> infos = CodeInsightTestFixtureImpl.instantiateAndRun(myFixture.getFile(), myFixture.getEditor(), new int[0], false);
        ArrayList<IntentionAction> actions = new ArrayList<IntentionAction>();
        for (HighlightInfo info : infos) {
            if(info.quickFixActionRanges == null) {
                continue;
            }
            for (Pair<HighlightInfo.IntentionActionDescriptor, TextRange> pair : info.quickFixActionRanges) {
                actions.add(pair.getFirst().getAction());
            }
        }
        return actions;
    }




}
