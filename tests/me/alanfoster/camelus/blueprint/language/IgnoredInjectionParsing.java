package me.alanfoster.camelus.blueprint.language;

import com.intellij.openapi.application.PathManager;
import com.intellij.testFramework.ParsingTestCase;

import java.io.File;

// TODO This test is ignored as it causes test runner exceptions
public class IgnoredInjectionParsing extends ParsingTestCase {
    public IgnoredInjectionParsing() {
        super("", "blueprintinjectionlanguage", new InjectionParserDefinition());
    }

    @Override
    public String getTestDataPath() {
        final String jarPathForClass = PathManager.getJarPathForClass(IgnoredInjectionParsing.class);
        File testDirectory = new File(jarPathForClass, "../../../testData/" + IgnoredInjectionParsing.class.getPackage().getName().toString().replace('.', '/'));
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
