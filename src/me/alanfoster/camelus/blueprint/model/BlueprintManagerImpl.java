package me.alanfoster.camelus.blueprint.model;

import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.ProjectScope;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.indexing.FileBasedIndex;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomManager;
import me.alanfoster.camelus.blueprint.dom.model.Blueprint;
import me.alanfoster.camelus.blueprint.dom.model.BlueprintBeanPointer;
import me.alanfoster.camelus.blueprint.dom.model.BlueprintFileDescription;
import me.alanfoster.camelus.blueprint.dom.model.PropertyPlaceholder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BlueprintManagerImpl extends BlueprintManager {

    // TODO Possibly singly instantiate this ModelFactory via a component
    private BlueprintModelFactory _blueprintModelFactory;

    public BlueprintManagerImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public Set<XmlFile> getAllProjectBlueprintConfigFiles(@NotNull Project project) {
        Set<XmlFile> blueprintConfigFiles = getBlueprintConfigFiles(project, ProjectScope.getContentScope(project));
        return blueprintConfigFiles;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public Set<Blueprint> getAllProjectBlueprintRoots(@NotNull Project project) {
        Set<XmlFile> allProjectBlueprintConfigFiles = getAllProjectBlueprintConfigFiles(project);
        Set<Blueprint> blueprintRoots = mapXmlFilesToBlueprintRoots(allProjectBlueprintConfigFiles, project);
        return blueprintRoots;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public Set<XmlFile> getModuleBlueprintConfigFiles(@NotNull Module module) {
        GlobalSearchScope scope = module.getModuleScope(); // module.getModuleContentScope();
        Set<XmlFile> blueprintConfigFiles = getBlueprintConfigFiles(module.getProject(), scope);
        return blueprintConfigFiles;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public Set<Blueprint> getModuleBlueprintRoots(@NotNull Module module) {
        Set<XmlFile> blueprintConfigFiles = getModuleBlueprintConfigFiles(module);
        Set<Blueprint> blueprintRoots = mapXmlFilesToBlueprintRoots(blueprintConfigFiles, module.getProject());
        return blueprintRoots;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    private Set<XmlFile> getBlueprintConfigFiles(@NotNull Project project, @NotNull GlobalSearchScope scope) {
        Set<XmlFile> files = new HashSet<XmlFile>();

        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, XmlFileType.INSTANCE, scope);

        for (VirtualFile virtualFile : virtualFiles) {

            // TODO Is there a nicer way to do this??
            // Match only blueprint files
            final VirtualFile parentFolder = virtualFile.getParent();

            boolean isBlueprintXmlFile = parentFolder != null &&
                    // TODO - is there a better way of searching for specific files?
                    (parentFolder.isDirectory() &&
                            (parentFolder.getPresentableUrl().endsWith("OSGI-INF\\blueprint")
                                    || parentFolder.getPresentableUrl().endsWith("OSGI-INF/blueprint")));

            if (isBlueprintXmlFile) {
                final Document document = FileDocumentManager.getInstance().getDocument(virtualFile);
                if (document != null) {
                    final PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);

                    XmlFile xmlFile = (XmlFile) psiFile;

                    // This predicate should always be true, but let's avoid a NPE just incase
                    if (xmlFile != null) {
                        files.add(xmlFile);
                    }
                }
            }
        }

        return files;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public List<IBlueprintDomModel> getAllBlueprintModels(@NotNull Module module) {
        return getBlueprintModelFactory(module.getProject()).computeAllModels(module);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public IBlueprintDomModel getMergedBlueprintModel(@NotNull Module module) {
        final List<IBlueprintDomModel> blueprintModels = getAllBlueprintModels(module);
        if (blueprintModels.size() == 0) return null;
        IBlueprintDomModel model = blueprintModels.get(0);
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public PropertyPlaceholder getModulePropertyPlaceHolder(@NotNull Module module) {
        Set<Blueprint> blueprintRoots = getModuleBlueprintRoots(module);

        for (Blueprint rootElement : blueprintRoots) {
            final PropertyPlaceholder propertyPlaceHolder = rootElement.getPropertyPlaceHolder();
            if (propertyPlaceHolder != null
                    && propertyPlaceHolder.isValid()
                    && propertyPlaceHolder.exists()) {

                return propertyPlaceHolder;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public List<? extends BlueprintBeanPointer> getAllModuleBlueprintBeanPointers(@NotNull Module module) {
        Set<Blueprint> blueprintFiles = getModuleBlueprintRoots(module);
        List<BlueprintBeanPointer> blueprintBeanPointers = new ArrayList<BlueprintBeanPointer>();
        for (Blueprint blueprintFile : blueprintFiles) {
            blueprintBeanPointers.addAll(blueprintFile.getBlueprintBeanPointers());
        }
        return blueprintBeanPointers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBlueprintFile(@Nullable PsiFile psiFile) {
        if(psiFile == null) return false;

        Project project = psiFile.getProject();
        return psiFile instanceof XmlFile
                && DomManager.getDomManager(project).getDomFileDescription((XmlFile) psiFile) instanceof BlueprintFileDescription;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    public BlueprintModelFactory getBlueprintModelFactory(@NotNull Project project) {
        if (_blueprintModelFactory == null) {
            _blueprintModelFactory = new BlueprintModelFactory(project);
        }
        return _blueprintModelFactory;
    }


    /**
     * Helper to map XmlFile -> Blueprint.
     */
    // TODO Scala map...
    @NotNull
    private Set<Blueprint> mapXmlFilesToBlueprintRoots(@NotNull Set<XmlFile> xmlFiles, Project project) {
        // Find the required XmlFile which contains the property placeholder DomElement
        final DomManager domManager = DomManager.getDomManager(project);
        Set<Blueprint> roots = new HashSet<Blueprint>();
        for (XmlFile xmlFile : xmlFiles) {
            DomFileElement<Blueprint> fileElement = domManager.getFileElement(xmlFile, Blueprint.class);
            if (fileElement == null) continue;
            final Blueprint rootElement = fileElement.getRootElement();
            roots.add(rootElement);
        }

        return roots;
    }

}
