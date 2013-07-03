package me.alanfoster.camelus.blueprint.language;

import com.intellij.openapi.application.PathManager;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelus.blueprint.language.CamelusProjectDescriptorBuilder.CamelusProject;
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

    public void testCompletionWithHelloWorld() {
        CamelusProject(myFixture)
                .withBlueprintFiles("codeInsight/HelloWorldPropertyPlaceholders.xml");

        List<String> completionVariants = myFixture.getCompletionVariants("codeInsight/CompleteTestData.blueprintinjectionlanguage");
        assertReflectionEquals(
                Arrays.asList("Hello", "World"),
                completionVariants);
    }

    public void testCompletionWithFooBarBazQux() {
        CamelusProject(myFixture)
                .withBlueprintFiles("codeInsight/FooBarBazQuxPropertyPlaceholders.xml");

        List<String> completionVariants = myFixture.getCompletionVariants("codeInsight/CompleteTestData.blueprintinjectionlanguage");
        assertReflectionEquals(
                Arrays.asList("bar", "baz", "foo", "qux"),
                completionVariants);
    }
}
