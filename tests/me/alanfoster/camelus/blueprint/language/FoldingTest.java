package me.alanfoster.camelus.blueprint.language;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import me.alanfoster.camelus.blueprint.TestHelper;

import static me.alanfoster.camelus.blueprint.CamelusProjectDescriptorBuilder.CreateCamelusProject;

/**
 * Tests to ensure that the blueprint injection language propertly folds.
 * @see me.alanfoster.camelus.blueprint.language.support.InjectionFoldingBuilder
 */
public class FoldingTest extends LightCodeInsightFixtureTestCase {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestDataPath() + "/language";
    }

    public void testFolding() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles(LanguageFiles.FOO_BAR_BAZ_QUX_PROPERTIES);
               // .withOpenedFile(LanguageFiles.BLUEPRINT_SINGLE_CARET);

        myFixture.configureByFiles(LanguageFiles.FOO_BAR_BAZ_QUX_BLUEPRINT_INJECTION);
        myFixture.testFolding(getTestDataPath() + "/" + LanguageFiles.FOO_BAR_BAZ_QUX_BLUEPRINT_INJECTION);
    }
}
