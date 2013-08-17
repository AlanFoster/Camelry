package me.alanfoster.camelry.camel.liveTemplates;

import me.alanfoster.camelry.TestHelper;
import me.alanfoster.camelry.camel.livetemplates.CamelContextTemplateContext;

/**
 * Tests to ensure that Camel LiveTemplates are only supported within specific Dom Elements.
 */
public class CamelContextTemplateContextTests extends CamelryTemplateContextTestBase {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/liveTemplates/context/CamelContextTemplateContext";
    }

    public void testCaretInsideCamelContextElement() {
        performTest(false);
    }

    public void testCaretUnderCamelContext() {
        performTest(true);
    }

    public void testCaretUnderRoute() {
        performTest(false);
    }

    /**
     * @param expectedInContext Whether the template context should return true or not
     */
    protected void performTest(boolean expectedInContext) {
        super.performTest(expectedInContext, new CamelContextTemplateContext());
    }


}
