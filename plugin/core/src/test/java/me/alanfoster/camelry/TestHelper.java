package me.alanfoster.camelry;

import com.intellij.openapi.application.PathManager;
import me.alanfoster.camelry.blueprint.language.IgnoredInjectionParsing;

import java.io.File;

/**
 * A small helper for dealing with JUnit tests.
 */
public class TestHelper {

    /**
     * @return The absolute path to the camelry test folder
     */
    public static String getTestRoot() {
        return new File(getSourceRoot().getPath(), "testData/me/alanfoster/camelry").getPath();
    }

    /**
     * @return The absolute path to the root test folder
     */
    public static File getSourceRoot() {
        return new File(TestHelper.class.getResource("/").getPath());
    }
}
