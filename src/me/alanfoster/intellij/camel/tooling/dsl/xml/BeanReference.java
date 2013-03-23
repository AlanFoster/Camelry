package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.ProjectScope;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.indexing.FileBasedIndex;
import com.intellij.util.xml.DomManager;
import me.alanfoster.intellij.camel.icons.CamelIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BeanReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    public BeanReference(@NotNull PsiElement psiElement, TextRange textRange) {
        super(psiElement, textRange);
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        return new ResolveResult[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public PsiElement resolve() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();

        List<LookupElement> variants = new ArrayList<LookupElement>();

        final List<Bean> beans = findBeans(project);

        for (Bean bean : beans) {

            System.out.println("Matched the following bean : " + bean.getId().getStringValue());

            variants.add(
                    LookupElementBuilder.create(bean.getId().getXmlTag())
                            .withIcon(CamelIcons.CAMEL)
                            .withPresentableText(bean.getId().getStringValue())
                            .withTailText("(" + bean.getClassAttribute().getStringValue() + ")")
                            .withTypeText("bean")
                            //.withLookupString(bean.getId().getStringValue())
            );
        }

        return variants.toArray();
    }

    private List<Bean> findBeans(Project project) {
        //Collection<FileType> foo = FileBasedIndex.getInstance().getAllKeys(FileTypeIndex.NAME, project);

        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME,
                XmlFileType.INSTANCE,
                // TODO Research Scope further!
                ProjectScope.getContentScope(project));

        for (VirtualFile virtualFile : virtualFiles) {

            // TODO Is there a nicer way to do this??
            // Match only blueprint files
            final VirtualFile parentFile = virtualFile.getParent();
            boolean isBlueprintXmlFile = parentFile != null &&
                    parentFile.getPresentableUrl().endsWith("resources\\OSGI-INF\\blueprint");

            if (isBlueprintXmlFile) {
                final Document document = FileDocumentManager.getInstance().getDocument(virtualFile);

                final PsiFile cachedPsiFile = PsiDocumentManager.getInstance(project).getCachedPsiFile(document);

                XmlFile xmlFile = (XmlFile) cachedPsiFile;
                // final XmlTag[] xmlTags = xmlFile.getDocument().getRootTag().findSubTags("bean", null);

                final DomManager domManager = DomManager.getDomManager(project);
                Blueprint root = domManager.getFileElement(xmlFile, Blueprint.class).getRootElement();
                List<Bean> foundBeans = root.getBeans();

                return foundBeans;
            }
        }

        return Collections.EMPTY_LIST;
    }


}
