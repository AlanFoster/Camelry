package me.alanfoster.camelus.camel.dom;

import me.alanfoster.camelus.CamelusTestSupport;
import me.alanfoster.camelus.LanguageFiles;
import me.alanfoster.camelus.TestHelper;
import me.alanfoster.camelus.blueprint.dom.inspectors.DeprecatedAttribtueChecker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.CreateCamelusProject;
import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.blueprint;
import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.java;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests for camel method DSL completion
 */
public class MethodTest extends CamelusTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/dom/camelMethod";
    }

    public void testBlueprintBeanMethodCompletionSameFileWithMethodDSL() {
        CreateCamelusProject(myFixture)
                .withFiles(blueprint(LanguageFiles.Camel.BlueprintBeanCompletionWithinSameBlueprintFile));

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanCompletionWithinSameBlueprintFile);
        assertReflectionEquals(
                Arrays.asList("one", "three", "two"),
                completionVariants);
    }

    public void testBlueprintBeanRefCompletionWithNoReferences() {
        CreateCamelusProject(myFixture)
                .withFiles(blueprint(LanguageFiles.Camel.BlueprintBeanRefCompletionWithNoReferences));

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanRefCompletionWithNoReferences);
        assertReflectionEquals(
                Collections.EMPTY_LIST,
                completionVariants);
    }

    public void testBlueprintBeanMethodCompletionWithinSameBlueprintFile() {
        CreateCamelusProject(myFixture)
                .withFiles(blueprint(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile))
                .withFiles(java("me.alanfoster.camelus.blueprint.camel.dom.common", commonFile("Person.java")));

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
                .withFiles(blueprint("BlueprintReferenceMethodCompletionExternalFile.xml", "../common/BlueprintServiceReferenceExternalFile.xml"))
                .withFiles(java("me.alanfoster.camelus.blueprint.camel.dom.common", commonFile("Person.java"), commonFile("IPersonService.java")))
                .withFiles(blueprint(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile));

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
                .withFiles(blueprint("MethodAttributeBeanDeprecatedAnnotatorData.xml"))
                .withOpenedFile("MethodAttributeBeanDeprecatedAnnotatorError.xml");

        myFixture.enableInspections(new DeprecatedAttribtueChecker());
        myFixture.checkHighlighting(true, true, true);
    }

    /**
     * Test that we can rename our bean reference id, directly from a camel route :)
     */
    public void testRenameRefValueWithBlueprintPointerReference() {
        CreateCamelusProject(myFixture)
                .withFiles(blueprint("RenameRefValueWithBlueprintPointerReference.xml"))
                .withFiles(java("me.alanfoster.camelus.blueprint.camel.dom.common", commonFile("IPersonService.java")))
                .withOpenedFileFromTempProject("OSGI-INF/blueprint/RenameRefValueWithBlueprintPointerReference.xml");

        myFixture.renameElementAtCaret("newPersonServiceName");
        // TODO Investigate why the target dom element id isn't being renamed successfully specifically within tests
        myFixture.checkResultByFile("OSGI-INF/blueprint/RenameRefValueWithBlueprintPointerReference.xml", "RenameRefValueWithBlueprintPointerReference_after.xml", false);
    }
}
