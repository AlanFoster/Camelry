package me.alanfoster.camelus.blueprint.dom.inspectors;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.LocalQuickFixBase;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.highlighting.DomElementAnnotationHolder;
import com.intellij.util.xml.highlighting.DomElementsInspection;
import com.intellij.util.xml.highlighting.DomHighlightingHelper;
import me.alanfoster.camelus.blueprint.dom.model.Blueprint;
import org.jetbrains.annotations.NotNull;

/**
 * Provides warning for deprecated DOMAttributes.
 * This checker also provides a quickfix to rename the attribute as required.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
// TODO Check out DomCustomAnnotationChecker, which seems to provide some of the below functionality - for example in ExtendsClassChecker
public class DeprecatedAttribtueChecker extends DomElementsInspection<Blueprint> {

    public DeprecatedAttribtueChecker() {
        super(Blueprint.class);
    }

    @Override
    protected void checkDomElement(final DomElement element,
                                   final DomElementAnnotationHolder holder,
                                   final DomHighlightingHelper helper) {
        final DeprecatedAttribute deprecatedAttribute = element.getAnnotation(DeprecatedAttribute.class);

        if(deprecatedAttribute != null){
            String deprecatedReason = deprecatedAttribute.reason();
            String newAttributeName = deprecatedAttribute.newName();

            final LocalQuickFix localQuickFix = new MyLocalQuickFixBase(element, newAttributeName);

            holder.createProblem(element, deprecatedReason, localQuickFix);
        }
    }

    /**
     * Provides a quick fix option for renaming attributes to the new name.
     */
    private static class MyLocalQuickFixBase extends LocalQuickFixBase {
        private static final String QUICKFIX_TEXT = "Fix deprecated attribute";
        private final DomElement element;
        private final String newAttributeName;

        public MyLocalQuickFixBase(DomElement element, String newAttributeName, String quickfixText) {
            super(quickfixText);
            this.element = element;
            this.newAttributeName = newAttributeName;
        }

        public MyLocalQuickFixBase(DomElement element, String newAttributeName) {
            this(element, newAttributeName, QUICKFIX_TEXT);
        }

        @Override
        public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
            ((GenericAttributeValue) element).getXmlAttribute().setName(newAttributeName);

           /* final PsiElement psiElement = descriptor.getPsiElement();
            if(psiElement.isValid() && CodeInsightUtilBase.preparePsiElementForWrite(psiElement)) {
               // RefactoringFactory.getInstance(psiElement.getProject()).createRename(psiElement, "ref");
            }*/
        }
    }
}

