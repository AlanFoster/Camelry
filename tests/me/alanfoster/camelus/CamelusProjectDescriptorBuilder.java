package me.alanfoster.camelus;


import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.testFramework.TestDataFile;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import com.intellij.testFramework.fixtures.JavaCodeInsightTestFixture;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.FileBasedIndex;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class CamelusProjectDescriptorBuilder {

    public static CamelusProject CreateCamelusProject(JavaCodeInsightTestFixture fixture) {
        return new CamelusProject(fixture, new DefaultLightProjectDescriptor());
    }

    public static class CamelusProject {
        private final DefaultLightProjectDescriptor defaultLightProjectDescriptor;
        private final JavaCodeInsightTestFixture fixture;



        public CamelusProject(JavaCodeInsightTestFixture fixture, DefaultLightProjectDescriptor defaultLightProjectDescriptor) {
            this.defaultLightProjectDescriptor = defaultLightProjectDescriptor;
            this.fixture = fixture;
        }

        /**
         * Copies all of the given files within the FileType into the current project
         * @param fileTypes
         * @return The builder object
         */
        public CamelusProject withFiles(FileType... fileTypes) {
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
        public CamelusProject withOpenedFileFromTempProject(FileType fileType) {
            assert fileType.getPairs().size() == 1 : "There should be exactly one file within the FileType";
            fixture.configureFromTempProjectFile(fileType.getPairs().get(0).getSecond());
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
    public static FileType blueprint(String... testDataPaths) {
        return new BlueprintFileType(testDataPaths);
    }

    /**
     * Copies the given files into a new java package.
     *
     * @param targetPackage
     * @param testDataPaths
     * @return
     */
    public static FileType java(String targetPackage, String... testDataPaths) {
        return new JavaFileType(targetPackage, testDataPaths);
    }



}
