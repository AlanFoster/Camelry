package me.alanfoster.camelry;


import com.intellij.openapi.util.Pair;
import com.intellij.testFramework.TestDataFile;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import com.intellij.testFramework.fixtures.JavaCodeInsightTestFixture;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.util.List;

public class CamelryProjectDescriptorBuilder {

    public static CamelryProject CreateCamelryProject(JavaCodeInsightTestFixture fixture) {
        return new CamelryProject(fixture, new DefaultLightProjectDescriptor());
    }

    public static class CamelryProject {
        private final DefaultLightProjectDescriptor defaultLightProjectDescriptor;
        private final JavaCodeInsightTestFixture fixture;



        public CamelryProject(JavaCodeInsightTestFixture fixture, DefaultLightProjectDescriptor defaultLightProjectDescriptor) {
            this.defaultLightProjectDescriptor = defaultLightProjectDescriptor;
            this.fixture = fixture;
        }

        /**
         * Copies all of the given files within the FileType into the current project
         * @param fileTypes
         * @return The builder object
         */
        public CamelryProject with(FileType... fileTypes) {
            for(FileType fileType : fileTypes) {
                for (Pair<String, String> pair : fileType.getPairs()) {
                    fixture.copyFileToProject(pair.getFirst(), pair.getSecond());
                }
            }
            return this;
        }

        /**
         * Loads the given file into the in-memory editor.
         * @param fileType
         * @return
         */
        public CamelryProject withOpenedFileFromTempProject(FileType fileType) {
            assert fileType.getPairs().size() == 1 : "There should be exactly one file within the FileType";
            fixture.configureFromTempProjectFile(fileType.getPairs().get(0).getSecond());
            return this;
        }

        public CamelryProject withOpenedFile(@TestDataFile @NonNls String testDataPath) {
            fixture.configureByFile(testDataPath);
            return this;
        }
    }

    public static abstract class FileType {
        protected String[] testDataPaths;

        public FileType(String... testDataPaths) {
            this.testDataPaths = testDataPaths;
        }
        /**
         * Get the pair of test data paths and copy location
         * @return A pair where the first item is the test data path, and the second item is the target copy location.
         */
        public List<Pair<String, String>> getPairs() {
            return ContainerUtil.map(testDataPaths, new Function<String, Pair<String, String>>() {
                @Override
                public Pair<String, String> fun(String testDataPath) {
                    return new Pair<String, String>(testDataPath, getTargetPath(testDataPath));
                }
            });
        }

        public String getFirstTargetPath() {
            return getPairs().get(0).getSecond();
        }

        protected abstract String getTargetPath(String testDataPath);
    }

    /**
     * Copies all required files into the OSGI-INF blueprint folder.
     */
    public static class BlueprintFileType extends FileType {
        private static final String OSGI_FOLDER_PATH = "OSGI-INF/blueprint/";

        public BlueprintFileType(String... testDataPaths) {
            super(testDataPaths);
        }

        @Override
        protected String getTargetPath(String testDataPath) {
            return OSGI_FOLDER_PATH + new File(testDataPath).getName();
        }
    }

    /**
     * Copies the given files into a new java package within the module.
     */
    public static class JavaFileType extends FileType {
        private final String targetPackage;

        public JavaFileType(String targetPackage, String... testDataPaths) {
            super(testDataPaths);
            this.targetPackage = targetPackage;
        }

        @Override
        protected String getTargetPath(String testDataPath) {
            String targetPath = targetPackage.replace('.', '/') + "/" + new File(testDataPath).getName();
            return targetPath;
        }
    }

    /**
     * Copies the given files into the OSGI-INF folder.
     *
     * @param testDataPaths The test data paths, relative to the testData folder
     * @return The builder object
     */
    public static FileType blueprintFiles(String... testDataPaths) {
        return new BlueprintFileType(testDataPaths);
    }

    /**
     * Copies the given files into a new java package.
     *
     * @param targetPackage
     * @param testDataPaths
     * @return
     */
    public static FileType javaFiles(String targetPackage, String... testDataPaths) {
        return new JavaFileType(targetPackage, testDataPaths);
    }

}
