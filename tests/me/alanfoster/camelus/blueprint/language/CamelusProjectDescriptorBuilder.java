package me.alanfoster.camelus.blueprint.language;


import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.module.Module;
import com.intellij.testFramework.PsiTestUtil;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import com.intellij.testFramework.fixtures.JavaCodeInsightTestFixture;

import java.io.File;

public class CamelusProjectDescriptorBuilder {

    public static CamelusProject CamelusProject(JavaCodeInsightTestFixture fixture) {
        return new CamelusProject(fixture, new DefaultLightProjectDescriptor());
    }

    public static class CamelusProject {
        private final DefaultLightProjectDescriptor defaultLightProjectDescriptor;
        private final JavaCodeInsightTestFixture fixture;

        // TODO - Will this will break for non-maven projects? We should search for source folders instead (content roots?)
        private static final String OSGI_FOLDER_PATH = "resources/OSGI-INF/blueprint/";

        public CamelusProject(JavaCodeInsightTestFixture fixture, DefaultLightProjectDescriptor defaultLightProjectDescriptor) {
            this.defaultLightProjectDescriptor = defaultLightProjectDescriptor;
            this.fixture = fixture;
        }

        public CamelusProject withBlueprintFiles(String... blueprintFiles) {
            for (String blueprintFile : blueprintFiles) {
                fixture.copyFileToProject(blueprintFile, OSGI_FOLDER_PATH + new File(blueprintFile).getName());
            }
            return this;
        }
    }

}
