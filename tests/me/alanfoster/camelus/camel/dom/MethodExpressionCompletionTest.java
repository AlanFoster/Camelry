package me.alanfoster.camelus.camel.dom;

import me.alanfoster.camelus.CamelusTestSupport;
import me.alanfoster.camelus.LanguageFiles;
import me.alanfoster.camelus.TestHelper;
import me.alanfoster.camelus.blueprint.inspectors.DeprecatedAttribtueChecker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelus.blueprint.CamelusProjectDescriptorBuilder.CreateCamelusProject;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests for camel method DSL completion
 */
public class MethodExpressionCompletionTest extends CamelusTestSupport {

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
    public void testBlueprintBeanMethodCompletionWithinSameBlueprintFile() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile)
                .withJavaFiles("me.alanfoster.camelus.blueprint.camel.dom.completion", "../Person.java");

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile);

        assertReflectionEquals(
                Arrays.asList(
                        "getAge", "getF", "getFirstName", "getId", "getLastName", "getNumber", "setAge",
                        "setF", "setFirstName", "setId", "setLastName", "setNumber", "clone", "equals",
                        "hashCode", "toString", "finalize"),
                completionVariants);
    }

    public void testBlueprintReferenceMethodCompletionExternalFile() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles("BlueprintReferenceMethodCompletionExternalFile.xml", "../BlueprintServiceReferenceExternalFile.xml")
                .withJavaFiles("me.alanfoster.camelus.blueprint.camel.dom.completion", "../Person.java", "../IPersonService.java")
                .withBlueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile);

        List<String> completionVariants = myFixture.getCompletionVariants("BlueprintReferenceMethodCompletionExternalFile.xml");
        assertReflectionEquals(
                Arrays.asList(
                        "create", "delete", "get", "getAll", "update",
                        "clone", "equals", "hashCode", "toString", "finalize"),
                completionVariants);
    }

    /**
     * Test to ensure that the user is told about the deprecated 'bean' attribute on
     * the camel method XML DSL.
     */
    public void testMethodAttributeBeanDeprecatedAnnotator() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles("MethodAttributeBeanDeprecatedAnnotatorData.xml")
                .withOpenedFile("MethodAttributeBeanDeprecatedAnnotatorError.xml");

        myFixture.enableInspections(new DeprecatedAttribtueChecker());
        myFixture.checkHighlighting(true, true, true);
    }



}
