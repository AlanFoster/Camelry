package me.alanfoster.intellij.camel.tooling.dsl.dom;

import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.ProjectScope;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.indexing.FileBasedIndex;
import com.intellij.util.xml.DomManager;
import me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint.BlueprintBean;
import me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint.Blueprint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Helpers for finding blueprint bean definitions (ie, defined in a blueprint.xml file)
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BeanHelper {

    public static List<BlueprintBean> findBeans(Project project) {
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

                final DomManager domManager = DomManager.getDomManager(project);
                Blueprint root = domManager.getFileElement(xmlFile, Blueprint.class).getRootElement();
                List<BlueprintBean> foundBeans = root.getBeans();

                return foundBeans;
            }
        }

        return Collections.EMPTY_LIST;
    }


    public static List<BlueprintBean> findBeans(Project project, String id) {
        List<BlueprintBean> allBeans = findBeans(project);
        List<BlueprintBean> matchingBeans = new ArrayList(allBeans.size());
        for (BlueprintBean bean : allBeans) {
            if (id.equals(bean.getId().getStringValue())) {
                matchingBeans.add(bean);
            }
        }
        return matchingBeans;
    }


    public static PsiElement getMethod(Project project, PsiClass psiClass) {
        return null;
    }

}
