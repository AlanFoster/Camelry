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
 * Tests for Blueprint Bean DomElements.
 */
// TODO Tests to ensure that we resolve to the correct constructor type - Not implemented
public class Bean extends CamelryTestSupport {

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
        CreateCamelryProject(myFixture)
                .with(blueprintFiles("BeanArgumentIndexChoosesLargestConstructorSize.xml"))
                .withOpenedFile("BeanArgumentIndexChoosesLargestConstructorSize.xml")
                .with(javaFiles("me.alanfoster.camelry.common", commonFile("Person.java")));

        List<String> completionVariants = myFixture.getCompletionVariants("BeanArgumentIndexChoosesLargestConstructorSize.xml");
        assertReflectionEquals(
                Arrays.asList("0", "1", "2", "3", "4"),
                completionVariants);
    }


    /**
     * Tests to ensure that only methods with a public setter method are shown to the user under
     * intellisense.
     */
    public void testPropertyNamesIntellisense() {
        CreateCamelryProject(myFixture)
                .with(blueprintFiles("PropertyNamesIntellisense.xml"))
                .withOpenedFile("PropertyNamesIntellisense.xml")
                .with(javaFiles("me.alanfoster.camelry.common", commonFile("Person.java")));

        List<String> completionVariants = myFixture.getCompletionVariants("PropertyNamesIntellisense.xml");
        assertReflectionEquals(
                Arrays.asList("age", "f", "firstName", "id", "lastName", "number"),
                completionVariants);
    }

    /**
     * Tests to ensure that only methods with a public setter method are shown to the user under
     * intellisense.
     */
    public void testPropertyRefIntellisense() {
        CreateCamelryProject(myFixture)
                .with(blueprintFiles("PropertyRefIntellisense.xml"))
                .withOpenedFile("PropertyRefIntellisense.xml")
                .with(javaFiles("me.alanfoster.camelry.common", commonFile("Person.java"), commonFile("IPersonService.java")));

        List<String> completionVariants = myFixture.getCompletionVariants("PropertyRefIntellisense.xml");
        assertReflectionEquals(
                Arrays.asList("customString", "person", "personService"),
                completionVariants);
    }

    /**
     * Tests to ensure that we can invoke property language intellisense
     */
    public void testPropertyLanguageVariants() {
        CreateCamelryProject(myFixture)
                .with(blueprintFiles("PropertyLanguageVariants.xml"))
                .withOpenedFile("PropertyLanguageVariants.xml")
                .with(javaFiles("me.alanfoster.camelry.common", commonFile("Person.java")));

        List<String> completionVariants = myFixture.getCompletionVariants("PropertyLanguageVariants.xml");
        assertReflectionEquals(
                Arrays.asList("myConfig.name"),
                completionVariants);
    }

    /**
     * Tests to ensure we can rename a property placeholder from the context of a property value attribute
     */
    // TODO See why we get the exception element not found in file PropertyLanguageRename.xml at caret position, offset 907
    public void ignorePropertyLanguageRename() {
        CreateCamelryProject(myFixture)
                .with(blueprintFiles("PropertyLanguageRename.xml"))
                        .withOpenedFile("PropertyLanguageRename.xml")
                        .with(javaFiles("me.alanfoster.camelry.common", commonFile("Person.java")));


        myFixture.renameElementAtCaret("helloWorldddd");
        myFixture.checkResultByFile("PropertyLanguageRename.xml", "PropertyLanguageRename.xml", false);
    }


}

