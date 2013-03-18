package me.alanfoster.intellij.camel.simple.language.tooling.autocomplete;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import me.alanfoster.intellij.camel.simple.language.SimpleLanguage;
import me.alanfoster.intellij.camel.simple.language.psi.SimpleTypes;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class SimpleCompletitionContributor extends CompletionContributor {

    private String[] VALID_IDENTIFIERS = new String[]{
            "camelId",
            "exchangeId",
            "id",
            "body",
            "in.body"
    };

    public SimpleCompletitionContributor() {
        completeIdentifiers();
    }

    private void completeIdentifiers() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(SimpleTypes.IDENTIFIER).withLanguage(SimpleLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters completionParameters,
                                                  ProcessingContext processingContext,
                                                  @NotNull CompletionResultSet completionResultSet) {
                        for (String identifier : VALID_IDENTIFIERS) {
                            completionResultSet.addElement(LookupElementBuilder.create(identifier));
                        }
                    }
                }
        );
    }

}
