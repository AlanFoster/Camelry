package me.alanfoster.camelus;


import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.testFramework.TestDataFile;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import com.intellij.testFramework.fixtures.JavaCodeInsightTestFixture;
import com.intellij.util.indexing.FileBasedIndex;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.util.Collection;

public class CamelusProjectDescriptorBuilder {

    public static CamelusProject CreateCamelusProject(JavaCodeInsightTestFixture fixture) {
        return new CamelusProject(fixture, new DefaultLightProjectDescriptor());
    }

    public static class CamelusProject {
        private final DefaultLightProjectDescriptor defaultLightProjectDescriptor;
        private final JavaCodeInsightTestFixture fixture;

        private static final String OSGI_FOLDER_PATH = "OSGI-INF/blueprint/";

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
                String targetPath = OSGI_FOLDER_PATH + new File(testDataPath).getName();
                fixture.copyFileToProject(testDataPath, targetPath);
            }
            return this;
        }

        public CamelusProject withOpenedFileFromTempProject(@TestDataFile @NonNls String testDataPath) {
            fixture.configureFromTempProjectFile(testDataPath);
            return this;
        }

        public CamelusProject withOpenedFile(@TestDataFile @NonNls String testDataPath) {
            fixture.configureByFile(testDataPath);
            return this;
        }

        public CamelusProject withJavaFiles(String targetPackage, @TestDataFile @NonNls String... testDataPaths) {
            for(String testDataPath : testDataPaths) {
                String targetPath = targetPackage.replace('.', '/') + "/" + new File(testDataPath).getName();
                fixture.copyFileToProject(testDataPath, targetPath);
            }
            Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
                    FileTypeIndex.NAME, StdFileTypes.JAVA, fixture.getModule().getModuleContentScope());

            return this;
        }
    }

}
