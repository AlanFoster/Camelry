package me.alanfoster.camelus;

/**
 * Stores the constants for test files. I think IntelliJ is supposed to do clever things with the
 * @TestDataFile annotation, similiar to the property bundle support, but it didn't appear to do anything...
 */
public class LanguageFiles {

    public class Blueprint {
        public static final String BLUEPRINT_SINGLE_CARET = "codeInsight/CompleteTestData.blueprintinjectionlanguage";
        public static final String FOO_BAR_BAZ_QUX_PROPERTIES = "codeInsight/FooBarBazQuxPropertyPlaceholders.xml";
        public static final String HELLO_WORLD_PROPERTIES = "codeInsight/HelloWorldPropertyPlaceholders.xml";
        public static final String FOO_BAR_BAZ_QUX_BLUEPRINT_INJECTION = "codeInsight/BlueprintFooBarBazQux.blueprintinjectionlanguage";
        public static final String FOO_BAR_BAZ_QUX_FOLDING_TEST_DATA = "codeInsight/FooBarBazQuxTestData.blueprintinjectionlanguage";

    }


    public class Camel {
        public static final String BlueprintBeanCompletionWithinSameBlueprintFile = "BlueprintBeanRefCompletionWithinSameBlueprintFile.xml";
        public static final String BlueprintBeanMethodCompletionWithinSameBlueprintFile = "BlueprintBeanMethodCompletionWithinSameBlueprintFile.xml";
        public static final String BlueprintBeanRefCompletionWithNoReferences = "BlueprintBeanRefCompletionWithNoReferences.xml";
    }
}
