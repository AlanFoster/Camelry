package me.alanfoster.camelus.blueprint.dom;

import me.alanfoster.camelus.CamelusTestSupport;
import me.alanfoster.camelus.TestHelper;

import java.util.Arrays;
import java.util.List;

import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.CreateCamelusProject;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Tests for Blueprint Bean DomElements.
 */
public class Bean extends CamelusTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/blueprint/dom/bean";
    }

    /**
     * Test to ensure that when intellisense is invoked on a blueprint argument index,
     * the largest size is chosen. IE if a class has 4 different constructors, and the
     * largest constructor has 4 arguments, then the intellisense should represent [0, 1, 2, 3]
     */
    public void testBeanArgumentIndexChoosesLargestConstructorSize() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles("BeanArgumentIndexChoosesLargestConstructorSize.xml")
                .withOpenedFile("BeanArgumentIndexChoosesLargestConstructorSize.xml")
                .withJavaFiles("me.alanfoster.camelus.blueprint.camel.dom.common", commonFile("CustomException.java"), commonFile("Person.java"));

        List<String> completionVariants = myFixture.getCompletionVariants("BeanArgumentIndexChoosesLargestConstructorSize.xml");
        assertReflectionEquals(
                Arrays.asList("0", "1", "2", "3", "4"),
                completionVariants);
    }

    // TODO Tests to ensure that we resolve to the correct constructor type

}

