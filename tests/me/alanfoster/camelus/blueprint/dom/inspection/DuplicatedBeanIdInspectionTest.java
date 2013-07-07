package me.alanfoster.camelus.blueprint.dom.inspection;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import me.alanfoster.camelus.TestHelper;

import static me.alanfoster.camelus.blueprint.CamelusProjectDescriptorBuilder.CreateCamelusProject;

/**
 * Ensures the user is alerted when a blueprint bean id is duplicated within
 * the same bundle
 */
public class DuplicatedBeanIdInspectionTest extends LightCodeInsightFixtureTestCase {
    @Override
    public String getTestDataPath() {
        return TestHelper.getTestDataPath() + "/blueprint/dom/inspection";
    }

    /**
     * Test to ensure that the user is told about the deprecated 'bean' attribute on
     * the camel method XML DSL.
     */
    // TODO Depends on more complex test structure setup
    /*public void testDuplicatedBeanIdWithinSameBundle() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles("TwoThreeFourFiveBeans.xml", "TwoThreeFourFiveBeans.xml")
                .withOpenedFile("OSGI-INF/blueprint/TwoThreeFourFiveBeans.xml");

        myFixture.checkHighlighting(true, true, true);
    }
*/
    // TODO Write test for highlighting within test directories

}
