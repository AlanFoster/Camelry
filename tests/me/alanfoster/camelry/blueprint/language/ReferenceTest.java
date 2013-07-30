package me.alanfoster.camelry.blueprint.language;

import com.intellij.psi.PsiElement;
import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.LanguageFiles;
import me.alanfoster.camelry.TestHelper;
import me.alanfoster.camelry.blueprint.dom.model.Property;
import me.alanfoster.camelry.blueprint.language.psi.InjectionPropertyDefinition;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.CreateCamelryProject;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;

/**
 * Tests for ensuring references are correct as expected.
 */
public class ReferenceTest extends CamelryTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/blueprint/language";
    }

    public void testSameBundleNoPropertyPlaceholdersValidReference() {
        CreateCamelryProject(myFixture)
                .withOpenedFile(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_FOLDING_TEST_DATA);

        PsiElement parent = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent();
        Property reference = ((InjectionPropertyDefinition) parent).getReferencedProperty();

        assertNull("No reference should exist as no property element exists", reference);
    }

    public void testSameBundleWIthValidReference() {
        CreateCamelryProject(myFixture)
                .with(blueprintFiles(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_PROPERTIES))
                .withOpenedFile(LanguageFiles.Blueprint.FOO_BAR_BAZ_QUX_FOLDING_TEST_DATA);

        PsiElement parent = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent();
        String reference = ((InjectionPropertyDefinition) parent).getReferencedProperty().getValue().getRawText();
        assertEquals(
                "The property should select the correct value from the same bundle's value field",
                "foo value 1", reference);
    }

}
