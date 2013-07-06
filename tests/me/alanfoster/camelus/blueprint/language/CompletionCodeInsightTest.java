package me.alanfoster.camelus.blueprint.language;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.StdModuleTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.PsiTestUtil;
import com.intellij.testFramework.builders.WebModuleFixtureBuilder;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.intellij.testFramework.fixtures.TempDirTestFixture;
import me.alanfoster.camelus.blueprint.TestHelper;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.alanfoster.camelus.blueprint.CamelusProjectDescriptorBuilder.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;


public class CompletionCodeInsightTest extends LightCodeInsightFixtureTestCase {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestDataPath() + "/language";
    }

    public void testCompletionWithNoBlueprintFiles() {
        myFixture.configureByFiles(LanguageFiles.BLUEPRINT_SINGLE_CARET);
        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.BLUEPRINT_SINGLE_CARET);

        assertReflectionEquals(
                Collections.EMPTY_LIST,
                completionVariants);
    }

    public void testCompletionWithHelloWorld() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles(LanguageFiles.HELLO_WORLD_PROPERTIES);

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.BLUEPRINT_SINGLE_CARET);
        assertReflectionEquals(
                Arrays.asList("Hello", "World"),
                completionVariants);
    }

/*
    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new DefaultLightProjectDescriptor() {
            @Override
            public void configureModule(Module module, ModifiableRootModel model, ContentEntry contentEntry) {
                Project project = module.getProject();

                PsiTestUtil.addModule(project, StdModuleTypes.JAVA, "projectTwo", model.getProject().getBaseDir());

                Module[] modules = ModuleManager.getInstance(model.getProject()).getModules();
                VirtualFile[] contentSourceRoots = ProjectRootManager.getInstance(project).getContentSourceRoots();
                OrderEntry[] orderEntries = ModuleRootManager.getInstance(modules[0]).getOrderEntries();

                super.configureModule(module, model, contentEntry);
            }
        };
    }
*/

    public void testCompletionWithFooBarBazQux() {
        CreateCamelusProject(myFixture)
                .withBlueprintFiles(LanguageFiles.FOO_BAR_BAZ_QUX_PROPERTIES);

        List<String> completionVariants = myFixture.getCompletionVariants(LanguageFiles.BLUEPRINT_SINGLE_CARET);
        assertReflectionEquals(
                Arrays.asList("bar", "baz", "foo", "qux"),
                completionVariants);
    }
}
