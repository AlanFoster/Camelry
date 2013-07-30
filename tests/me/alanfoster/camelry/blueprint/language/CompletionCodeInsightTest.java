package me.alanfoster.camelry.blueprint.language;

import me.alanfoster.camelry.LanguageFiles;
import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.TestHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests for blueprint injection language autocompletion support.
 * ie ctrl+space support for property names.
 */
public class CompletionCodeInsightTest extends CamelryTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/blueprint/language";
    }

    public void testCompletionWithNoBlueprintFiles() {
        myFixture.configureByFiles(LanguageFiles.Blueprint.BLUEPRINT_SINGLE_CARET);
        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Blueprint.BLUEPRINT_SINGLE_CARET);

        assertReflectionEquals(
                Collections.EMPTY_LIST,
                completionVariants);
    }

    public void testCompletionWithHelloWorld() {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Blueprint.HELLO_WORLD_PROPERTIES));

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Blueprint.BLUEPRINT_SINGLE_CARET);
        assertReflectionEquals(
                Arrays.asList("Hello", "World"),
                completionVariants);
    }

    public void testCompletionWithFooBarBazQux() {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_PROPERTIES));

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.Blueprint.BLUEPRINT_SINGLE_CARET);
        assertReflectionEquals(
                Arrays.asList("bar", "baz", "foo", "qux"),
                completionVariants);
    }
}
