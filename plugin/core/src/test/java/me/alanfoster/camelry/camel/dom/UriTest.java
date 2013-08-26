package me.alanfoster.camelry.camel.dom;

import com.intellij.openapi.application.PathManager;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.compiled.ClsClassImpl;
import com.intellij.testFramework.PsiTestUtil;
import com.intellij.util.PathUtil;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.impl.GenericDomValueReference;
import me.alanfoster.camelry.CamelryProjectDescriptorBuilder;
import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.LanguageFiles;
import me.alanfoster.camelry.TestHelper;
import org.junit.Assert;

import java.io.File;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;

/**
 * Tests for the 'uri' language. Ie within `to uri="...." `
 */
public class UriTest extends CamelryTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/dom/uri";
    }

    public void testFromDirectVMReference() {
        performTest("DirectVmComponent");
    }

    public void testToDirectVMReference() {
        performTest("DirectVmComponent");
    }

    public void testFromPropertyInterpolation() {
        performTest(null);
    }

    public void testToPropertyInterpolation() {
        performTest(null);
    }

    /**
     *
     * @param expectedClassName The expected class name that we should resolve to.
     *                          Null implies that no reference should be found.
     */
    private void performTest(String expectedClassName) {
        // We require Camel Core on our classpath
        PsiTestUtil.addLibrary(myFixture.getModule(), "camel-core", new File(TestHelper.getSourceRoot(), "/camel/camel-2.10.0").getPath(), "camel-core-2.10.0.jar");

        String testFile = getTestName(false) + ".xml";
        CamelryProjectDescriptorBuilder.CreateCamelryProject(myFixture)
                .with(blueprintFiles(testFile))
                .withOpenedFileFromTempProject(blueprintFiles(testFile));

        PsiElement elementAtCaret = null;
        try {
            elementAtCaret = myFixture.getElementAtCaret();
        } catch (AssertionError e) {
            Assert.assertTrue("An unexpected AssertionError occured. This test requires for the only assert failure to be that of IntelliJ's testing framework",
                    e.getMessage().contains("element not found in file"));
        }

        if(expectedClassName == null){
            assertNull("There should be no resolved PsiElement for this uri", elementAtCaret);
            return;
        }

        Assert.assertTrue("URI Reference should be an instance of PsiClass", elementAtCaret instanceof PsiClass);
        String className = ((ClsClassImpl) elementAtCaret).getName();
        Assert.assertEquals("Resolved class name should be equal to that of the expected", expectedClassName, className);
    }


}
