package me.alanfoster.camelry;

import com.intellij.codeInsight.completion.CodeCompletionHandlerBase;
import com.intellij.codeInsight.completion.CompletionProgressIndicator;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.codeInsight.lookup.impl.LookupImpl;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.impl.source.tree.injected.InjectedLanguageUtil;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.UIUtil;
import junit.framework.Assert;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

/**
 * The base class used for testing.
 */
public abstract class CamelryTestSupport extends LightCodeInsightFixtureTestCase {

    /**
     * Specify the use of a mock JDK, which is a lightweight version of the Java SDK.
     * If we didn't supply this, then we wouldn't be able to get intellisense for classes
     * like java.lang.String etc.
     */
    public static final LightProjectDescriptor DEFAULT_PROJECT_DESCRIPTOR = new DefaultLightProjectDescriptor() {
        @Override
        public Sdk getSdk() {
            String jdkPath = new File(TestHelper.getSourceRoot(), "mockJDK-1.7").getPath();
            return JavaSdk.getInstance().createJdk("1.7", jdkPath, false);
        }
    };

    @NotNull
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return DEFAULT_PROJECT_DESCRIPTOR;
    }

    /**
     * @return a path which points directly to the common folder with the given relative path
     */
    public static String commonFile(String relativePath) {
        File commonDirectory = new File(TestHelper.getTestRoot(), "/common");
        String commonFilePath = new File(commonDirectory, relativePath).getPath();
        return commonFilePath;
    }


    /**
     * Differs to myFixture.getCompletionVariants() in that it doesn't configure the project before
     * executing the get variants list.
     *
     * @return A list of String variants.
     */
    public List<String> getSafeCompletionVariants() {
        LookupElement[] items = complete(CompletionType.BASIC, 1);
        Assert.assertNotNull("No lookup was shown, probably there was only one lookup element that was inserted automatically", items);

        return getLookupElementStrings();
    }

    private boolean myEmptyLookup = false;
    private LookupElement[] complete(final CompletionType type, final int invocationCount) {

        UIUtil.invokeAndWaitIfNeeded(new Runnable() {
            @Override
            public void run() {
                CommandProcessor.getInstance().executeCommand(getProject(), new Runnable() {
                    @Override
                    public void run() {
                        final CodeCompletionHandlerBase handler = new CodeCompletionHandlerBase(type) {

                            @Override
                            protected void completionFinished(int offset1,
                                                              int offset2,
                                                              CompletionProgressIndicator indicator,
                                                              LookupElement[] items,
                                                              boolean hasModifiers) {
                                myEmptyLookup = items.length == 0;
                                super.completionFinished(offset1, offset2, indicator, items, hasModifiers);
                            }
                        };
                        Editor editor = InjectedLanguageUtil.getEditorForInjectedLanguageNoCommit(myFixture.getEditor(), myFixture.getFile());
                        handler.invokeCompletion(getProject(), editor, invocationCount);
                        PsiDocumentManager.getInstance(getProject()).commitAllDocuments(); // to compare with file text
                    }
                }, null, null);
            }
        });

        return getLookupElements();
    }

    @Nullable
    private LookupElement[] getLookupElements() {
        LookupImpl lookup = getLookup();
        if (lookup == null) {
            return myEmptyLookup ? LookupElement.EMPTY_ARRAY : null;
        }
        else {
            final List<LookupElement> list = lookup.getItems();
            return list.toArray(new LookupElement[list.size()]);
        }
    }

    private LookupImpl getLookup() {
        return (LookupImpl) LookupManager.getActiveLookup(myFixture.getEditor());
    }

    @Nullable
    private List<String> getLookupElementStrings() {
        final LookupElement[] elements = getLookupElements();
        if (elements == null) return null;

        return ContainerUtil.map(elements, new Function<LookupElement, String>() {
            @Override
            public String fun(final LookupElement lookupItem) {
                return lookupItem.getLookupString();
            }
        });
    }




}
