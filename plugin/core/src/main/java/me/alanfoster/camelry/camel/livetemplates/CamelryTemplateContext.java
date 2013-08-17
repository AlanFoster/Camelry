package me.alanfoster.camelry.camel.livetemplates;

import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.impl.source.tree.ElementType;
import com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl;
import com.intellij.psi.util.PsiUtil;
import com.intellij.psi.xml.XmlElementType;
import com.intellij.psi.xml.XmlText;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.DomUtil;
import com.intellij.xml.util.XmlUtil;
import me.alanfoster.camelry.camel.dom.RouteDefinition;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the base class for a Camel specific place of injection. All extending classes
 * will be passed the DomElement which is currently selected within a PSIFile - which implies
 * all CamelryContexts are only applicable within a Blueprint Xml file.
 */
public abstract class CamelryTemplateContext extends TemplateContextType {

    public CamelryTemplateContext(@NotNull @NonNls String id, @NotNull String presentableName) {
        super(id, presentableName);
    }

    /**
     * @param file
     * @param offset
     * @return True if the currently selected element is contained within a blueprint file,
     *         and the implementation class returns true.
     *         Note, the selected element must be of XmlText.
     *         This limitation is added to stop scenarios such as
     *         <pre>
     *         {@code
     *              <foo <caret> />
     *         }
     *         </pre>
     */
    @Override
    public boolean isInContext(@NotNull PsiFile file, int offset) {
        if (file.getFileType() != StdFileTypes.XML) return false;

        PsiElement elementAtOffset = PsiUtil.getElementAtOffset(file, offset);

        // The target element should be whitespace, ie under a dom element
        if(!isXmlText(elementAtOffset)) return false;

        DomElement domElement = DomUtil.getDomElement(elementAtOffset);
        return domElement != null && isInContext(file.getProject(), domElement);
    }

    private boolean isXmlText(PsiElement psiElement) {
        PsiElement parent = psiElement.getParent();
        return parent instanceof XmlText;
    }

    /**
     * All implementing classes should apply their specific logic to the supplied domElement.
     *
     * @param domElement The currently selected domElement
     * @return True if the Context is applicable to this element, false otherwise.
     */
    public abstract boolean isInContext(@NotNull Project project, @NotNull DomElement domElement);
}
