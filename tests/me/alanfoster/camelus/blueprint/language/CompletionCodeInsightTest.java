package me.alanfoster.camelus.blueprint.language;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import me.alanfoster.camelus.blueprint.TestHelper;

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
