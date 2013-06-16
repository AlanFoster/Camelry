package me.alanfoster.intellij.blueprint.language.contributors;

import com.intellij.codeInsight.completion.*;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import me.alanfoster.intellij.blueprint.language.BlueprintInjectionLanguage;
import me.alanfoster.intellij.blueprint.language.InjectionTypes;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class InjectionReferenceContributor extends CompletionContributor {
    public InjectionReferenceContributor() {
        // Basic Contribution test, property name correctly triggers within a CompletionContributor, but not within a PsiReferenceContributor!
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(InjectionTypes.PROPERTY_NAME).withLanguage(BlueprintInjectionLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  ProcessingContext context,
                                                  @NotNull CompletionResultSet resultSet) {

                        // TODO See if blueprint has any types which are always available within the language
                        /*final Project project = parameters.getPosition().getProject();
                        final Set<XmlFile> blueprintModels = IBlueprintManager.getInstance().getAllBlueprintConfigFiles(project);

                        for (XmlFile blueprintModel : blueprintModels) {
                            resultSet.addElement(LookupElementBuilder.create(blueprintModel));
                        }*/
                    }
                });
    }
}
