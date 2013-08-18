package me.alanfoster.camelry.camel.dom;

import me.alanfoster.camelry.CamelryProjectDescriptorBuilder;
import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.LanguageFiles;
import me.alanfoster.camelry.TestHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests camel bean intellisense completion.
 */
public class BeanCompletionTest extends CamelryTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/dom/camelBean";
    }

    /******************************************************************************
     * Basic Blueprint Bean Reference tests
     ******************************************************************************/
    public void testBlueprintBeanRefCompletionWithinSameBlueprintFile() {
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
                .withOpenedFileFromTempProject(blueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile));

        List<String> completionVariants = getSafeCompletionVariants();
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


    /*******************************************************************************
     * Blueprint Service reference Bean Reference tests
     ******************************************************************************/
    public void testBlueprintServiceReferenceRefCompletionWithinSameBlueprintFile() {
        String fileName = getTestName(false) + ".xml";
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles(fileName))
                .withOpenedFileFromTempProject(blueprintFiles(fileName));

        List<String> completionVariants = getSafeCompletionVariants();
        assertReflectionEquals(
                Arrays.asList("dataSourceBar", "dataSourceFoo", "one", "three", "two"),
                completionVariants);
    }

    public void testBlueprintServiceReferenceRefCompletionWithinDifferentFile() {
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles("BlueprintServiceReferenceRefCompletionWithinSameBlueprintFile.xml", "../common/BlueprintServiceReferenceExternalFile.xml"))
                .withOpenedFileFromTempProject(blueprintFiles("BlueprintServiceReferenceRefCompletionWithinSameBlueprintFile.xml"));

        List<String> completionVariants = getSafeCompletionVariants();
        assertReflectionEquals(
                Arrays.asList("dataSourceBar", "dataSourceFoo", "one", "three", "two", "externalReferenceOne", "externalReferenceTwo"),
                completionVariants);
    }
}
