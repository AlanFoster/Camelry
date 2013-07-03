package me.alanfoster.camelus.blueprint;


import com.intellij.testFramework.TestDataFile;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import com.intellij.testFramework.fixtures.JavaCodeInsightTestFixture;
import org.jetbrains.annotations.NonNls;

import java.io.File;

public class CamelusProjectDescriptorBuilder {

    public static CamelusProject CreateCamelusProject(JavaCodeInsightTestFixture fixture) {
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

        /**
         * Copies the given files into the OSGI-INF folder
         * @param testDataPaths The test data paths, relative to the testData folder
         * @return The builder object
         */
        public CamelusProject withBlueprintFiles(@TestDataFile @NonNls String... testDataPaths) {
            for (String testDataPath : testDataPaths) {
                fixture.copyFileToProject(testDataPath, OSGI_FOLDER_PATH + new File(testDataPath).getName());
            }
            return this;
        }
    }

}
