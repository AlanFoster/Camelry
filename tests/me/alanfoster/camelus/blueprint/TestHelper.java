package me.alanfoster.camelus.blueprint;

import com.intellij.openapi.application.PathManager;
import me.alanfoster.camelus.blueprint.language.InjectionParsingTest;

import java.io.File;

/**
 * A small helper for dealing with JUnit tests.
 */
public class TestHelper {

    public static String getTestDataPath() {
        final String jarPathForClass = PathManager.getJarPathForClass(InjectionParsingTest.class);
        File testDirectory = new File(jarPathForClass, "../../../testData/" + TestHelper.class.getPackage().getName().replace('.', '/'));
        return testDirectory.getPath();
    }

}
