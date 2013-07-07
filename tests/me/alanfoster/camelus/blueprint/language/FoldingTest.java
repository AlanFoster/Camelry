package me.alanfoster.camelus.blueprint.language;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import me.alanfoster.camelus.LanguageFiles;
import me.alanfoster.camelus.TestHelper;

import static me.alanfoster.camelus.blueprint.CamelusProjectDescriptorBuilder.CreateCamelusProject;

/**
 * Tests to ensure that the blueprint injection language propertly folds.
 * @see me.alanfoster.camelus.blueprint.language.support.InjectionFoldingBuilder
 */
public class FoldingTest extends LightCodeInsightFixtureTestCase {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestDataPath() + "/blueprint/language";
    }

    public void testFolding() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_PROPERTIES)
                .withOpenedFile(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_FOLDING_TEST_DATA);

        myFixture.testFolding(getTestDataPath() + "/" + LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_BLUEPRINT_INJECTION);
    }


}
