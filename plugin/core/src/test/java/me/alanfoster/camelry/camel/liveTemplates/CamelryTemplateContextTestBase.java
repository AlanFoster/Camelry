package me.alanfoster.camelry.camel.liveTemplates;

import me.alanfoster.camelry.CamelryProjectDescriptorBuilder;
import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.LanguageFiles;
import me.alanfoster.camelry.TestHelper;
import me.alanfoster.camelry.camel.livetemplates.CamelContextTemplateContext;
import me.alanfoster.camelry.camel.livetemplates.CamelryTemplateContext;
import org.junit.Assert;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.CreateCamelryProject;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.javaFiles;

/**
 * Tests to ensure that Camel LiveTemplates are only supported within specific Dom Elements.
 */
public abstract class CamelryTemplateContextTestBase extends CamelryTestSupport {

    /**
     * @param expectedInContext Whether the template context should return true or not
     */
    protected void performTest(boolean expectedInContext, CamelryTemplateContext templateContext) {
        String fileName = getTestName(false) + ".xml";

        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles(fileName))
                .withOpenedFileFromTempProject(blueprintFiles(fileName));

        boolean inContext = templateContext.isInContext(myFixture.getFile(), myFixture.getCaretOffset());

        Assert.assertEquals(
                "The expected incontext and actual incontext should be equal for live template contexts",
                expectedInContext, inContext);
    }


}
