package me.alanfoster.camelry.blueprint.dom;

import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.TestHelper;

import java.util.Arrays;
import java.util.List;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.CreateCamelusProject;
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
        CreateCamelusProject(myFixture)
                .with(blueprintFiles("ServiceRefVariants.xml"))
                .withOpenedFile("ServiceRefVariants.xml")
                .with(javaFiles("me.alanfoster.camelus.blueprint.camel.dom.common", commonFile("Person.java")));

        List<String> completionVariants = myFixture.getCompletionVariants("ServiceRefVariants.xml");
        assertReflectionEquals(
                Arrays.asList("customString", "personService"),
                completionVariants);
    }

}
