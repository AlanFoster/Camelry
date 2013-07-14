package me.alanfoster.camelus.camel.dom;

import me.alanfoster.camelus.CamelusTestSupport;
import me.alanfoster.camelus.TestHelper;

import java.util.Arrays;
import java.util.List;

import static me.alanfoster.camelus.blueprint.CamelusProjectDescriptorBuilder.CreateCamelusProject;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests for the apache camel Throws Exception xml DSL.
 */
public class ThrowExceptionTest extends CamelusTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/dom/ThrowException";
    }

    /**
     * Test to ensure that only beans which extend Exception are shown to the user.
     */
    public void testExceptionBeanVariantsOnly() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles("ExceptionBeanVariantsOnly.xml")
                .withOpenedFile("ExceptionBeanVariantsOnly.xml")
                .withJavaFiles("me.alanfoster.camelus.blueprint.camel.dom.completion", "CustomException.java", "../completion/Person.java");

        List<String> completionVariants = myFixture.getCompletionVariants("ExceptionBeanVariantsOnly.xml");
        assertReflectionEquals(
                Arrays.asList(
                        "argumentException", "customException"),
                completionVariants);
    }


}

