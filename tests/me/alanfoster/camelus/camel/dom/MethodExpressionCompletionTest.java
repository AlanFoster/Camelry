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
                .withBlueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile);

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile);
        assertReflectionEquals(
                Arrays.asList(
                        "charAt", "charAt", "codePointAt", "codePointBefore", "codePointCount", "compareTo",
                        "compareTo", "compareToIgnoreCase", "concat", "contains", "contentEquals", "contentEquals",
                        "endsWith", "equals", "equalsIgnoreCase", "equals", "getBytes", "getBytes", "getBytes",
                        "getChars", "getChars", "hashCode", "indexOf", "indexOf", "indexOf", "indexOf", "intern",
                        "lastIndexOf", "lastIndexOf", "lastIndexOf", "lastIndexOf", "length", "length", "matches",
                        "offsetByCodePoints", "regionMatches", "regionMatches", "replace", "replace", "replaceAll",
                        "replaceFirst", "split", "split", "startsWith", "startsWith", "subSequence", "substring",
                        "substring", "toCharArray", "toLowerCase", "toLowerCase", "toString", "toString",
                        "toUpperCase", "toUpperCase", "trim", "clone", "hashCode", "toString", "finalize",
                        "subSequence"),
                completionVariants);
    }

    /**
     * Test to ensure that the user is told about the deprecated 'bean' attribute on
     * the camel method XML DSL.
     */
    public void testMethodAttributeBeanDeprecatedAnnotator() throws Exception {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles("MethodAttributeBeanDeprecatedAnnotatorData.xml")
                .withOpenedFile("MethodAttributeBeanDeprecatedAnnotatorError.xml");

        myFixture.enableInspections(new DeprecatedAttribtueChecker());
        myFixture.checkHighlighting(true, true, true);
    }

}
