package me.alanfoster.camelry.camel.dom;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.compiled.ClsClassImpl;
import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.TestHelper;
import org.junit.Assert;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.CreateCamelryProject;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.javaFiles;

/**
 * Tests to ensure onException DOM intellisense.
 */
public class OnException extends CamelryTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/dom/onException";
    }

    public void testMultipleOnExceptionResolvesCorrectly() {
        String fileName = "MultipleOnExceptionResolvesCorrectly.xml";
        String expectedClassName = "java.lang.ClassCastException";
        CreateCamelryProject(myFixture)
                .with(blueprintFiles(fileName))
                .withOpenedFileFromTempProject(blueprintFiles(fileName))
                .with(javaFiles("me.alanfoster.camelry.common", commonFile("CustomException.java"), commonFile("Person.java")));

        PsiElement elementAtCaret = myFixture.getElementAtCaret();

        Assert.assertTrue("Referenced element should be an instance of PsiClass", elementAtCaret instanceof PsiClass);
        String className = ((ClsClassImpl) elementAtCaret).getQualifiedName();
        Assert.assertEquals("Resolved class name should be equal to that of the expected", expectedClassName, className);
    }
}
