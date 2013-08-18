package me.alanfoster.camelry.camel.dom;

import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.TestHelper;

import java.util.Arrays;
import java.util.List;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.CreateCamelryProject;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.javaFiles;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests for the apache camel Throws Exception xml DSL.
 */
public class ThrowExceptionTest extends CamelryTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/dom/throwException";
    }

    /**
     * Test to ensure that only beans which extend Exception are shown to the user.
     */
    public void testExceptionBeanVariantsOnly() {
        CreateCamelryProject(myFixture)
                .with(blueprintFiles("ExceptionBeanVariantsOnly.xml"))
                .withOpenedFileFromTempProject(blueprintFiles("ExceptionBeanVariantsOnly.xml"))
                .with(javaFiles("me.alanfoster.camelry.common", commonFile("CustomException.java"), commonFile("Person.java")));

        List<String> completionVariants = getSafeCompletionVariants();
        assertReflectionEquals(
                Arrays.asList(
                        "argumentException", "customException"),
                completionVariants);
    }


}

