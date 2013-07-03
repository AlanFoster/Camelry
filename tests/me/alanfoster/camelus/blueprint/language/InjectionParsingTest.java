package me.alanfoster.camelus.blueprint.language;

import com.intellij.openapi.application.PathManager;
import com.intellij.testFramework.ParsingTestCase;

import java.io.File;

public class InjectionParsingTest extends ParsingTestCase {
    public InjectionParsingTest() {
        super("", "blueprintinjectionlanguage", new InjectionParserDefinition());
    }

    @Override
    public String getTestDataPath() {
        final String jarPathForClass = PathManager.getJarPathForClass(InjectionParsingTest.class);
        File testDirectory = new File(jarPathForClass, "../../../testData/" + InjectionParsingTest.class.getPackage().getName().toString().replace('.', '/'));
        return testDirectory.getPath();
    }
/*
    public void testParsingTestData() {
        doTest(true);
    }*/

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

}
