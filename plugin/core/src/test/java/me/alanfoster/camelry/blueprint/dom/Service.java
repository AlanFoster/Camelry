package me.alanfoster.camelry.blueprint.dom;

import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.TestHelper;

import java.util.Arrays;
import java.util.List;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.CreateCamelryProject;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.javaFiles;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests for the Blueprint service DomElement support.
 */
public class Service extends CamelryTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/blueprint/dom/service";
    }

    public void testServiceRefVariants() {
        CreateCamelryProject(myFixture)
                .with(blueprintFiles("ServiceRefVariants.xml"))
                .withOpenedFileFromTempProject(blueprintFiles("ServiceRefVariants.xml"))
                .with(javaFiles("me.alanfoster.camelry.common", commonFile("Person.java")));

        List<String> completionVariants = getSafeCompletionVariants();
        assertReflectionEquals(
                Arrays.asList("customString", "personService"),
                completionVariants);
    }

}
