package me.alanfoster.camelus.blueprint.language;

import me.alanfoster.camelus.CamelryTestSupport;
import me.alanfoster.camelus.LanguageFiles;
import me.alanfoster.camelus.TestHelper;

import static me.alanfoster.camelus.CamelryProjectDescriptorBuilder.CreateCamelusProject;
import static me.alanfoster.camelus.CamelryProjectDescriptorBuilder.blueprintFiles;

/**
 * Tests to ensure that the blueprint injection language propertly folds.
 * @see me.alanfoster.camelus.blueprint.language.support.InjectionFoldingBuilder
 */
public class FoldingTest extends CamelryTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/blueprint/language";
    }

    public void testFolding() {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_PROPERTIES))
                        .withOpenedFile(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_FOLDING_TEST_DATA);

        myFixture.testFolding(getTestDataPath() + "/" + LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_BLUEPRINT_INJECTION);
    }


}
