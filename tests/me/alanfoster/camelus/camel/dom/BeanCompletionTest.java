package me.alanfoster.camelus.camel.dom;

import me.alanfoster.camelus.CamelusTestSupport;
import me.alanfoster.camelus.TestHelper;
import me.alanfoster.camelus.LanguageFiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.CreateCamelusProject;
import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.blueprintFiles;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests camel bean intellisense completion.
 */
public class BeanCompletionTest extends CamelusTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/dom/camelBean";
    }

    /******************************************************************************
     * Basic Blueprint Bean Reference tests
     ******************************************************************************/
    public void testBlueprintBeanRefCompletionWithinSameBlueprintFile() {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanCompletionWithinSameBlueprintFile));

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanCompletionWithinSameBlueprintFile);
        assertReflectionEquals(
                Arrays.asList("one", "three", "two"),
                completionVariants);
    }

    public void testBlueprintBeanRefCompletionWithNoReferences() {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanRefCompletionWithNoReferences));

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Camel.BlueprintBeanRefCompletionWithNoReferences);
        assertReflectionEquals(
                Collections.EMPTY_LIST,
                completionVariants);
    }

    public void testBlueprintBeanMethodCompletionWithinSameBlueprintFile() {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Camel.BlueprintBeanMethodCompletionWithinSameBlueprintFile));

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


    /*******************************************************************************
     * Blueprint Service reference Bean Reference tests
     ******************************************************************************/
    public void testBlueprintServiceReferenceRefCompletionWithinSameBlueprintFile() {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles(getTestName(false) + ".xml"));

        List<String> completionVariants = myFixture.getCompletionVariants(getTestName(false) + ".xml");
        assertReflectionEquals(
                Arrays.asList("dataSourceBar", "dataSourceFoo", "one", "three", "two"),
                completionVariants);
    }

    public void testBlueprintServiceReferenceRefCompletionWithinDifferentFile() {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles("BlueprintServiceReferenceRefCompletionWithinSameBlueprintFile.xml", "../common/BlueprintServiceReferenceExternalFile.xml"));

        List<String> completionVariants = myFixture.getCompletionVariants("BlueprintServiceReferenceRefCompletionWithinSameBlueprintFile.xml");
        assertReflectionEquals(
                Arrays.asList("dataSourceBar", "dataSourceFoo", "externalReferenceOne", "externalReferenceTwo", "one", "three", "two"),
                completionVariants);
    }
}
