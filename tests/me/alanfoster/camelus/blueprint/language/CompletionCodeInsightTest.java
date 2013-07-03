package me.alanfoster.camelus.blueprint.language;

import com.intellij.openapi.application.PathManager;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import me.alanfoster.camelus.blueprint.TestHelper;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelus.blueprint.CamelusProjectDescriptorBuilder.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;


public class CompletionCodeInsightTest extends LightCodeInsightFixtureTestCase {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestDataPath() + "/language";
    }

    public void testCompletionWithNoBlueprintFiles() {
        myFixture.configureByFiles("codeInsight/CompleteTestData.blueprintinjectionlanguage");
        List<String> completionVariants = myFixture.getCompletionVariants("codeInsight/CompleteTestData.blueprintinjectionlanguage");

        assertReflectionEquals(
                Collections.EMPTY_LIST,
                completionVariants);
    }

    public void testCompletionWithHelloWorld() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles("codeInsight/HelloWorldPropertyPlaceholders.xml");

        List<String> completionVariants = myFixture.getCompletionVariants("codeInsight/CompleteTestData.blueprintinjectionlanguage");
        assertReflectionEquals(
                Arrays.asList("Hello", "World"),
                completionVariants);
    }

    public void testCompletionWithFooBarBazQux() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles("codeInsight/FooBarBazQuxPropertyPlaceholders.xml");

        List<String> completionVariants = myFixture.getCompletionVariants("codeInsight/CompleteTestData.blueprintinjectionlanguage");
        assertReflectionEquals(
                Arrays.asList("bar", "baz", "foo", "qux"),
                completionVariants);
    }
}
