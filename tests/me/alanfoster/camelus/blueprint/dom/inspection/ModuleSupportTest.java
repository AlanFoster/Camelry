package me.alanfoster.camelus.blueprint.dom.inspection;

import me.alanfoster.camelus.TestHelper;

import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.testFramework.builders.JavaModuleFixtureBuilder;
import com.intellij.testFramework.fixtures.*;
import com.intellij.util.indexing.FileBasedIndex;
import junit.framework.TestCase;
import org.junit.Assert;

import java.io.File;
import java.util.Collection;

public class ModuleSupportTest extends TestCase {
    // Project test fixture which points to the root src content folder
    protected JavaCodeInsightTestFixture myFixture;

    // Direct access to the created modules
    protected Module myFirstModule;
    protected Module mySecondModule;

    // Setup creates a new project test fixture + two modules
    @Override
    public void setUp() throws Exception {
        super.setUp();

        final TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder = JavaTestFixtureFactory.createFixtureBuilder(getName());
        myFixture = JavaTestFixtureFactory.getFixtureFactory().createCodeInsightFixture(projectBuilder.getFixture());
        myFixture.setTestDataPath(getTestDataPath());

        final ModuleFixture firstModuleFixture = newModule(projectBuilder, "0");
        final ModuleFixture secondModuleFixture = newModule(projectBuilder, "1");

        // Call setup on our project fixture, which now allows us to access the ModuleFixture's modules without exception
        myFixture.setUp();

        myFirstModule = firstModuleFixture.getModule();
        mySecondModule = secondModuleFixture.getModule();
    }

    // Creates a new java 1.7 module and adds it to the project
    private ModuleFixture newModule(TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder, String contentRoot) throws Exception {
        final JavaModuleFixtureBuilder firstProjectBuilder = projectBuilder.addModule(JavaModuleFixtureBuilder.class);
        firstProjectBuilder.setLanguageLevel(LanguageLevel.JDK_1_7);
        String tempDirPath = myFixture.getTempDirPath();

        // Create a new content root for each module, and create a directory for it manually
        String contentRootPath = tempDirPath + "/" + contentRoot;
        new File(contentRootPath).mkdir();

        // Call the builder
        ModuleFixture moduleFixture = firstProjectBuilder
                .addContentRoot(contentRootPath)
                .addSourceRoot("src")
                .getFixture();

        return moduleFixture;
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        myFixture.tearDown();
    }

    public String getTestDataPath() {
        return TestHelper.getTestDataPath();
    }

    public void testFileCopiesSuccessfullyIntoTwoDifferentModules() {
        // Copy two different files into both modules separately
        myFixture.copyFileToProject("blueprint/dom/inspection/OneTwoThreeFourBeans.xml", myFirstModule.getName() + "/src/OneTwoThreeFourBeans.xml");
        myFixture.copyFileToProject("blueprint/dom/inspection/TwoThreeFourFiveBeans.xml", mySecondModule.getName() + "/src/TwoThreeFourFiveBeans.xml");

        Collection<VirtualFile> virtualFilesTwo = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, XmlFileType.INSTANCE, mySecondModule.getModuleContentScope());
        Assert.assertEquals("The second module should only have one file available within its content scope", 1, virtualFilesTwo.size());
    }
}
