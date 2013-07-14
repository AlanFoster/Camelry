package me.alanfoster.camelus.camel.dom;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import me.alanfoster.camelus.LanguageFiles;
import me.alanfoster.camelus.TestHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelus.blueprint.CamelusProjectDescriptorBuilder.CreateCamelusProject;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests for camel method DSL completion
 */
public class MethodExpressionCompletionTest extends LightCodeInsightFixtureTestCase {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/dom/completion/CamelMethod";
    }

    public void testBlueprintBeanMethodCompletionSameFileWithMethodDSL() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles(LanguageFiles.Camel.BlueprintBeanCompletionWithinSameBlueprintFile);

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanCompletionWithinSameBlueprintFile);
        assertReflectionEquals(
                Arrays.asList("one", "three", "two"),
                completionVariants);
    }

    public void testBlueprintBeanRefCompletionWithNoReferences() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles(LanguageFiles.Camel.BlueprintBeanRefCompletionWithNoReferences);

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanRefCompletionWithNoReferences);
        assertReflectionEquals(
                Collections.EMPTY_LIST,
                completionVariants);
    }

    // Currently ignored, as the completion doesn't seem to work from the test suite... The SDK should already be set though
    public void ignoreBlueprintBeanMethodCompletionWithinSameBlueprintFile() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile);

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile);
        assertReflectionEquals(
                Arrays.asList("TODO", "all", "string", "methods", "here"),
                completionVariants);
    }

    /**
     * Test to ensure that the user is told about the deprecated 'bean' attribute on
     * the camel method XML DSL.
     */
    // TODO Depends on more complex test structure setup
    public void ignoreMethodAttributeBeanDeprecatedAnnotator() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles("MethodAttributeBeanDeprecatedAnnotatorData.xml")
                .withOpenedFile("MethodAttributeBeanDeprecatedAnnotatorErrorAnnotation.xml");

        myFixture.checkHighlighting(true, true, true);
    }


}
