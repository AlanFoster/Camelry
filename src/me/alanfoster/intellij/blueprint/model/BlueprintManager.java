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
import me.alanfoster.intellij.blueprint.dom.Blueprint;
import me.alanfoster.intellij.blueprint.dom.PropertyPlaceholder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BlueprintManager extends IBlueprintManager {

    // TODO Possibly singly instantiate this ModelFactory via a component
    private BlueprintModelFactory _blueprintModelFactory;

    public BlueprintManager() {
    }

    @NotNull
    @Override
    public Set<XmlFile> getAllBlueprintConfigFiles(@NotNull final Project project) {
        Set<XmlFile> files = new HashSet<XmlFile>();

        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, XmlFileType.INSTANCE,
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
                if (document != null) {
                    final PsiFile cachedPsiFile = PsiDocumentManager.getInstance(project).getCachedPsiFile(document);

                    XmlFile xmlFile = (XmlFile) cachedPsiFile;

                    files.add(xmlFile);
                }
            }
        }

        return files;
    }

    @Nullable
    @Override
    public List<IBlueprintDomModel> getAllBlueprintModels(@NotNull Module module) {
        return getBlueprintModelFactory(module.getProject()).computeAllModels(module);
    }

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
    public PropertyPlaceholder getPropertyPlaceHolder(@NotNull Project project) {
        final Set<XmlFile> blueprintModels = IBlueprintManager.getInstance().getAllBlueprintConfigFiles(project);

        // Find the required XmlFile which contains the property placeholder DomElement
        final DomManager domManager = DomManager.getDomManager(project);
        for (XmlFile xmlFile : blueprintModels) {
            final Blueprint rootElement = domManager.getFileElement(xmlFile, Blueprint.class).getRootElement();
            if (rootElement == null) continue;

            final PropertyPlaceholder propertyPlaceHolder = rootElement.getPropertyPlaceHolder();
            if (propertyPlaceHolder != null
                    && propertyPlaceHolder.isValid()
                    && propertyPlaceHolder.exists()) {

                return propertyPlaceHolder;
            }
        }

        return null;
    }

    @NotNull
    public BlueprintModelFactory getBlueprintModelFactory(@NotNull Project project) {
        if (_blueprintModelFactory == null) {
            _blueprintModelFactory = new BlueprintModelFactory(project);
        }
        return _blueprintModelFactory;
    }
}
