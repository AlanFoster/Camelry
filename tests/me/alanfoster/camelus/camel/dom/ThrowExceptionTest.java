package me.alanfoster.camelus.camel.dom;

import me.alanfoster.camelus.CamelusTestSupport;
import me.alanfoster.camelus.TestHelper;

import java.util.Arrays;
import java.util.List;

import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.CreateCamelusProject;
import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.blueprint;
import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.java;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests for the apache camel Throws Exception xml DSL.
 */
public class ThrowExceptionTest extends CamelusTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/dom/throwException";
    }

    /**
     * Test to ensure that only beans which extend Exception are shown to the user.
     */
    public void testExceptionBeanVariantsOnly() {
        CreateCamelusProject(myFixture)
                .withFiles(blueprint("ExceptionBeanVariantsOnly.xml"))
                .withOpenedFile("ExceptionBeanVariantsOnly.xml")
                .withFiles(java("me.alanfoster.camelus.blueprint.camel.dom.common", commonFile("CustomException.java"), commonFile("Person.java")));

        List<String> completionVariants = myFixture.getCompletionVariants("ExceptionBeanVariantsOnly.xml");
        assertReflectionEquals(
                Arrays.asList(
                        "argumentException", "customException"),
                completionVariants);
    }


}

