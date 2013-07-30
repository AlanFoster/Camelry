package me.alanfoster.camelry.blueprint.dom.inspection;

import me.alanfoster.camelry.TestHelper;

import com.intellij.openapi.module.Module;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.testFramework.builders.JavaModuleFixtureBuilder;
import com.intellij.testFramework.fixtures.*;
import junit.framework.TestCase;

import java.io.File;

public abstract class ModuleSupportHelper extends TestCase {
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

    @Override
    public void tearDown() throws Exception {
        myFixture.tearDown();
        super.tearDown();
    }

    // Creates a new java 1.7 module and adds it to the project
    private ModuleFixture newModule(TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder, String contentRoot) throws Exception {
        final JavaModuleFixtureBuilder firstProjectBuilder = projectBuilder.addModule(JavaModuleFixtureBuilder.class);
        firstProjectBuilder.setLanguageLevel(LanguageLevel.JDK_1_7);
        String tempDirPath = myFixture.getTempDirPath();

        // Create a new content root for each module, and create a directory for it manually
        String contentRootPath = tempDirPath + "/" + contentRoot;
        new File(contentRootPath).mkdir();

        String sourceRootPath = contentRootPath + "/src";
        new File(sourceRootPath).mkdir();

        // Call the builder
        ModuleFixture moduleFixture = firstProjectBuilder
                .addContentRoot(contentRootPath)
                .addSourceRoot(sourceRootPath)
                .getFixture();

        return moduleFixture;
    }

    public String getTestDataPath() {
        return TestHelper.getTestRoot();
    }
}
