package me.alanfoster.camelry.blueprint.language;

import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.LanguageFiles;
import me.alanfoster.camelry.TestHelper;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.CreateCamelusProject;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;

/**
 * Tests to ensure that the blueprint injection language propertly folds.
 * @see me.alanfoster.camelry.blueprint.language.support.InjectionFoldingBuilder
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
