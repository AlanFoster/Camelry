package me.alanfoster.intellij.blueprint.model;

import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.ProjectScope;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.indexing.FileBasedIndex;
import com.intellij.util.xml.DomManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BlueprintManager extends IBlueprintManager {

    public BlueprintManager(){

    }

    @NotNull
    @Override
    public Set<XmlFile> getAllBlueprintConfigFiles(@NotNull final Module module) {
        //final ModuleRootManager rootManager = ModuleRootManager.getInstance(module);
       // PsiManager psiManager = PsiManager.getInstance(module.getProject());

      /*  for(VirtualFile virtualFile : rootManager.getContentRoots() )*/

        Set<XmlFile> files = new HashSet<XmlFile>();

        Project project = module.getProject();
        final DomManager domManager = DomManager.getDomManager(project);


        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, XmlFileType.INSTANCE,
                // TODO Research Scope further!
                ProjectScope.getContentScope(module.getProject()));

        for (VirtualFile virtualFile : virtualFiles) {

            // TODO Is there a nicer way to do this??
            // Match only blueprint files
            final VirtualFile parentFile = virtualFile.getParent();
            boolean isBlueprintXmlFile = parentFile != null &&
                    parentFile.getPresentableUrl().endsWith("resources\\OSGI-INF\\blueprint");

            if (isBlueprintXmlFile) {
                final Document document = FileDocumentManager.getInstance().getDocument(virtualFile);
                if(document != null) {
                    final PsiFile cachedPsiFile = PsiDocumentManager.getInstance(project).getCachedPsiFile(document);

                    XmlFile xmlFile = (XmlFile) cachedPsiFile;

                    files.add(xmlFile);
                }
            }
        }

        return files;
    }

    @NotNull
    @Override
    public List<IBlueprintDomModel> getAllBlueprintModels(@NotNull Module module) {
        // TODO This would be a helpful method to add in the future
        List<IBlueprintDomModel> domModels = new ArrayList<IBlueprintDomModel>();
        /*final DomManager domManager = DomManager.getDomManager(module.getProject());
        for(XmlFile xmlFile : getAllBlueprintConfigFiles(module)) {


         //   domModels.add(domManager.getFileElement(xmlFile, Blueprint.class).get)
        }
        final DomManager domManager = DomManager.getDomManager(project);
        Blueprint root = domManager.getFileElement(xmlFile, Blueprint.class).getRootElement();
        List<BlueprintBean> foundBeans = root.getBeans();
        return null;  //To change body of implemented methods use File | Settings | File Templates.*/
        return domModels;
    }
}
