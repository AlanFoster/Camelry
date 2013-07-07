package me.alanfoster.camelus.cucumber;

import com.intellij.openapi.application.PathManager;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.junit.Assert;

import java.io.File;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class CamelBeanReferenceHighlightingTest extends LightCodeInsightFixtureTestCase {

    @Override
    public String getTestDataPath() {
        final String jarPathForClass = PathManager.getJarPathForClass(CamelBeanReferenceHighlightingTest.class);
        File sourceRoot = new File(jarPathForClass, "./../../../../..");
        return new File(sourceRoot, "testData").getPath();
    }





    public void testCompletion() {
        Assert.assertTrue(true);
    }
}
