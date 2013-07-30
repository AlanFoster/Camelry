package me.alanfoster.camelus;

import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * The base class used for testing.
 */
public class CamelryTestSupport extends LightCodeInsightFixtureTestCase {

    /**
     * Specify the use of a mock JDK, which is a lightweight version of the Java SDK.
     * If we didn't supply this, then we wouldn't be able to get intellisense for classes
     * like java.lang.String etc.
     */
    public static final LightProjectDescriptor DEFAULT_PROJECT_DESCRIPTOR = new DefaultLightProjectDescriptor() {
        @Override
        public Sdk getSdk() {
            String jdkPath = new File(TestHelper.getSourceRoot(), "mockJDK-1.7").getPath();
            return JavaSdk.getInstance().createJdk("1.7", jdkPath, false);
        }
    };

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return DEFAULT_PROJECT_DESCRIPTOR;
    }

    /**
     * @return a path which points directly to the common folder with the given relative path
     */
    public static String commonFile(String relativePath) {
        File commonDirectory = new File(TestHelper.getTestRoot(), "/common");
        String commonFilePath = new File(commonDirectory, relativePath).getPath();
        return commonFilePath;
    }

}
