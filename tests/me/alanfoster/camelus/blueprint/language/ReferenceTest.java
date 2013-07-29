package me.alanfoster.camelus.blueprint.language;

import com.intellij.psi.PsiElement;
import me.alanfoster.camelus.CamelusTestSupport;
import me.alanfoster.camelus.LanguageFiles;
import me.alanfoster.camelus.TestHelper;
import me.alanfoster.camelus.blueprint.dom.model.Property;
import me.alanfoster.camelus.blueprint.language.psi.InjectionPropertyDefinition;

import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.CreateCamelusProject;
import static me.alanfoster.camelus.CamelusProjectDescriptorBuilder.blueprintFiles;

/**
 * Tests for ensuring references are correct as expected.
 */
public class ReferenceTest extends CamelusTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/blueprint/language";
    }

    public void testSameBundleNoPropertyPlaceholdersValidReference() {
        CreateCamelusProject(myFixture)
                .withOpenedFile(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_FOLDING_TEST_DATA);

        PsiElement parent = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent();
        Property reference = ((InjectionPropertyDefinition) parent).getReferencedProperty();

        assertNull("No reference should exist as no property element exists", reference);
    }

    public void testSameBundleWIthValidReference() {
        CreateCamelusProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_PROPERTIES))
                .withOpenedFile(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_FOLDING_TEST_DATA);

        PsiElement parent = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent();
        String reference = ((InjectionPropertyDefinition) parent).getReferencedProperty().getValue().getRawText();
        assertEquals(
                "The property should select the correct value from the same bundle's value field",
                "foo value 1", reference);
    }

}
