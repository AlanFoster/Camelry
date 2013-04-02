package me.alanfoster.intellij;

import com.intellij.testFramework.ParsingTestCase;
import me.alanfoster.intellij.camel.simple.language.parser.SimpleParserDefinition;

/**
 * A test case for the Simple language parsing.
 *
 * Note - The Intellij tests use an old version of JUnit (3?)
 * So when adding new tests, follow the method naming convention
 * of 'test<Name>' and name the required files in the /testData
 * directory as '<Name>.fileExtension'
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see me.alanfoster.intellij.camel.simple.language.SimpleLanguage
 * @see me.alanfoster.intellij.camel.simple.language.parser.SimpleParser
 */
public class SimpleParsingTest extends ParsingTestCase {
    public SimpleParsingTest() {
        super("", "simple", new SimpleParserDefinition());
    }

    public void testBodyTest() {
        doTest(true);
    }

    @Override
    public String getTestDataPath() {
        return ".\\testData\\me\\alanfoster\\intellij\\camel\\simple\\language\\parser";
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }

}
