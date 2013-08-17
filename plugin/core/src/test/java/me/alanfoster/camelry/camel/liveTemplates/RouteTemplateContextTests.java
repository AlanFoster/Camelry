package me.alanfoster.camelry.camel.liveTemplates;

import me.alanfoster.camelry.TestHelper;
import me.alanfoster.camelry.camel.livetemplates.RouteTemplateContext;

/**
 * Tests to ensure that Camel LiveTemplates are only supported within specific Dom Elements.
 */
public class RouteTemplateContextTests extends CamelryTemplateContextTestBase {
    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/liveTemplates/context/RouteTemplateContext";
    }

    public void testCaretUnderCamelContext() {
        performTest(false);
    }

    public void testCaretUnderComplexRoute() {
        performTest(true);
    }

    public void testCaretUnderRoute() {
        performTest(true);
    }

    /**
     * @param expectedInContext Whether the template context should return true or not
     */
    protected void performTest(boolean expectedInContext) {
        super.performTest(expectedInContext, new RouteTemplateContext());
    }

}
