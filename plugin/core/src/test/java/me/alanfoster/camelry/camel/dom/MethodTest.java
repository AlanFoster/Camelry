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
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanCompletionWithinSameBlueprintFile))
                .withOpenedFileFromTempProject(blueprintFiles(LanguageFiles.Camel.BlueprintBeanCompletionWithinSameBlueprintFile));

        List<String> completionVariants = getSafeCompletionVariants();
        assertReflectionEquals(
                Arrays.asList("one", "three", "two"),
                completionVariants);
    }

    public void testBlueprintBeanRefCompletionWithNoReferences() {
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanRefCompletionWithNoReferences))
                .withOpenedFileFromTempProject(blueprintFiles(LanguageFiles.Camel.BlueprintBeanRefCompletionWithNoReferences));

        List<String> completionVariants = getSafeCompletionVariants();
        assertReflectionEquals(
                Collections.EMPTY_LIST,
                completionVariants);
    }

    public void testBlueprintBeanMethodCompletionWithinSameBlueprintFile() {
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile))
                .with(javaFiles("me.alanfoster.camelry.common", commonFile("Person.java")))
                .withOpenedFileFromTempProject(blueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile));

        List<String> completionVariants = getSafeCompletionVariants();

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
                .with(javaFiles("me.alanfoster.camelry.common", commonFile("Person.java"), commonFile("IPersonService.java")))
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile))
                .withOpenedFileFromTempProject(blueprintFiles("BlueprintReferenceMethodCompletionExternalFile.xml"));

        List<String> completionVariants = getSafeCompletionVariants();
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
                // Note, we need to copy the Error xml file over too
                // This is because in-memory file is not considered to be a blueprint file, as it does not exist within OSGI-INF/blueprint
                .with(blueprintFiles("MethodAttributeBeanDeprecatedAnnotatorData.xml", "MethodAttributeBeanDeprecatedAnnotatorError.xml"))
                .withOpenedFileFromTempProject(blueprintFiles(("MethodAttributeBeanDeprecatedAnnotatorError.xml")));

        myFixture.enableInspections(new DeprecatedAttribtueChecker());
        myFixture.checkHighlighting(true, true, true);
    }

    /**
     * Test that we can rename our bean reference id, directly from a camel route :)
     */
    public void testRenameRefValueWithBlueprintPointerReference() {
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles("RenameRefValueWithBlueprintPointerReference.xml"))
                .with(javaFiles("me.alanfoster.camelry.common", commonFile("IPersonService.java")))
                .withOpenedFileFromTempProject(blueprintFiles("RenameRefValueWithBlueprintPointerReference.xml"));

        myFixture.renameElementAtCaret("newPersonServiceName");
        myFixture.checkResultByFile("OSGI-INF/blueprint/RenameRefValueWithBlueprintPointerReference.xml", "RenameRefValueWithBlueprintPointerReference_after.xml", false);
    }
}
