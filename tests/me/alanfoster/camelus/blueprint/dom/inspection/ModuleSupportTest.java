package me.alanfoster.camelus.blueprint.dom.inspection;

import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.ide.util.projectWizard.ProjectBuilder;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.impl.ContentFolderBaseImpl;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.testFramework.PsiTestUtil;
import com.intellij.testFramework.builders.JavaModuleFixtureBuilder;
import com.intellij.testFramework.fixtures.*;
import com.intellij.util.indexing.FileBasedIndex;
import junit.framework.TestCase;
import me.alanfoster.camelus.TestHelper;
import org.junit.Assert;

import java.util.Collection;

public class ModuleSupportTest extends TestCase  {
    JavaCodeInsightTestFixture myFixture;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        final TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder = JavaTestFixtureFactory.createFixtureBuilder(getName());
        myFixture = JavaTestFixtureFactory.getFixtureFactory().createCodeInsightFixture(projectBuilder.getFixture());
        myFixture.setTestDataPath(getTestDataPath());

        final ModuleFixture moduleFixtureOne = newModule(projectBuilder);
        final ModuleFixture moduleFixtureTwo = newModule(projectBuilder);

        myFixture.setUp();

        Module moduleOne = moduleFixtureOne.getModule();
        Module moduleTwo = moduleFixtureTwo.getModule();

        myFixture.copyFileToProject("blueprint/dom/inspection/OneTwoThreeFourBeans.xml", moduleOne.getName() + "/src" + "/OneTwoThreeFourBeans.xml");
        myFixture.copyFileToProject("blueprint/dom/inspection/OneTwoThreeFourBeans.xml", moduleTwo.getName() + "/src" + "/OneTwoThreeFourBeans.xml");

        Collection<VirtualFile> virtualFilesOne = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, XmlFileType.INSTANCE, moduleOne.getModuleContentScope());

        Collection<VirtualFile> virtualFilesTwo = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, XmlFileType.INSTANCE, moduleTwo.getModuleContentScope());

        System.out.println("Successfully created test support");
    }

    private ModuleFixture newModule(TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder) {
        final JavaModuleFixtureBuilder firstProjectBuilder = projectBuilder.addModule(JavaModuleFixtureBuilder.class);
        firstProjectBuilder.setLanguageLevel(LanguageLevel.JDK_1_7);
        firstProjectBuilder.addContentRoot(myFixture.getTempDirPath());
        ModuleFixture moduleFixture = firstProjectBuilder
                .addSourceRoot("src")
                .getFixture();

        return moduleFixture;
    }


    @Override
    public void tearDown() throws Exception {
        super.tearDown();

    }

    public String getTestDataPath() {
        return TestHelper.getTestDataPath();
    }

    public void testSetup() {
        Assert.assertTrue(true);
    }
}
