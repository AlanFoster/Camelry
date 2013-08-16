package me.alanfoster.camelry.camel.dom;

import me.alanfoster.camelry.CamelryProjectDescriptorBuilder;
import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.LanguageFiles;
import me.alanfoster.camelry.TestHelper;
import me.alanfoster.camelry.blueprint.dom.inspectors.DeprecatedAttribtueChecker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.javaFiles;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests for camel method DSL completion
 */
public class MethodTest extends CamelryTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/dom/camelMethod";
    }

    public void testBlueprintBeanMethodCompletionSameFileWithMethodDSL() {
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanCompletionWithinSameBlueprintFile));

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanCompletionWithinSameBlueprintFile);
        assertReflectionEquals(
                Arrays.asList("one", "three", "two"),
                completionVariants);
    }

    public void testBlueprintBeanRefCompletionWithNoReferences() {
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanRefCompletionWithNoReferences));

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanRefCompletionWithNoReferences);
        assertReflectionEquals(
                Collections.EMPTY_LIST,
                completionVariants);
    }

    public void testBlueprintBeanMethodCompletionWithinSameBlueprintFile() {
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile))
                .with(javaFiles("me.alanfoster.camelry.blueprint.camel.dom.common", commonFile("Person.java")));

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile);

        assertReflectionEquals(
                Arrays.asList(
                        "getAge", "getF", "getFirstName", "getId", "getLastName", "getNumber", "setAge",
                        "setF", "setFirstName", "setId", "setLastName", "setNumber", "clone", "equals",
                        "hashCode", "toString", "finalize"),
                completionVariants);
    }

    public void testBlueprintReferenceMethodCompletionExternalFile() {
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles("BlueprintReferenceMethodCompletionExternalFile.xml", "../common/BlueprintServiceReferenceExternalFile.xml"))
                .with(javaFiles("me.alanfoster.camelry.blueprint.camel.dom.common", commonFile("Person.java"), commonFile("IPersonService.java")))
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile));

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
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles("MethodAttributeBeanDeprecatedAnnotatorData.xml"))
                .withOpenedFile("MethodAttributeBeanDeprecatedAnnotatorError.xml");

        myFixture.enableInspections(new DeprecatedAttribtueChecker());
        myFixture.checkHighlighting(true, true, true);
    }

    /**
     * Test that we can rename our bean reference id, directly from a camel route :)
     */
    public void testRenameRefValueWithBlueprintPointerReference() {
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles("RenameRefValueWithBlueprintPointerReference.xml"))
                .with(javaFiles("me.alanfoster.camelry.blueprint.camel.dom.common", commonFile("IPersonService.java")))
                .withOpenedFileFromTempProject("OSGI-INF/blueprint/RenameRefValueWithBlueprintPointerReference.xml");

        myFixture.renameElementAtCaret("newPersonServiceName");
        myFixture.checkResultByFile("OSGI-INF/blueprint/RenameRefValueWithBlueprintPointerReference.xml", "RenameRefValueWithBlueprintPointerReference_after.xml", false);
    }
}
