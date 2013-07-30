package me.alanfoster.camelry;

import com.intellij.openapi.application.PathManager;
import me.alanfoster.camelry.blueprint.language.IgnoredInjectionParsing;

import java.io.File;

/**
 * A small helper for dealing with JUnit tests.
 */
public class TestHelper {

    public static String getTestRoot() {
        File testRoot = new File(getSourceRoot(), "testData/" + TestHelper.class.getPackage().getName().replace('.', '/'));
        return testRoot.getPath();
    }

    public static File getSourceRoot() {
        final String jarPathForClass = PathManager.getJarPathForClass(IgnoredInjectionParsing.class);
        return new File(jarPathForClass, "../../../");
    }


}
