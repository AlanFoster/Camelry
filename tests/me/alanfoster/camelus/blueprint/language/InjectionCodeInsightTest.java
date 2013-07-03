package me.alanfoster.camelus.blueprint.language;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.application.PathManager;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;


public class InjectionCodeInsightTest extends LightCodeInsightFixtureTestCase {

    @Override
    public String getTestDataPath() {
        final String jarPathForClass = PathManager.getJarPathForClass(InjectionParsingTest.class);
        File testDirectory = new File(jarPathForClass, "../../../testData/" + InjectionParsingTest.class.getPackage().getName().toString().replace('.', '/'));
        return testDirectory.getPath();
    }

    public void testCompletionWithNoBlueprintFiles() {
        myFixture.configureByFiles("codeInsight/CompleteTestData.blueprintinjectionlanguage");
        List<String> completionVariants = myFixture.getCompletionVariants("codeInsight/CompleteTestData.blueprintinjectionlanguage");

        assertReflectionEquals(
                Collections.EMPTY_LIST,
                completionVariants);
    }



}
