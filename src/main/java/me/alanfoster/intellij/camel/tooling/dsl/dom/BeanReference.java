package me.alanfoster.intellij.camel.tooling.dsl.dom;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.ResolveResult;
import me.alanfoster.intellij.icons.PluginIcons;
import me.alanfoster.intellij.blueprint.dom.BlueprintBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BeanReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    public BeanReference(){
        super(null, null);
    }

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

        final List<BlueprintBean> beans = BeanHelper.findBeans(project);

        for (BlueprintBean bean : beans) {

            System.out.println("Matched the following bean : " + bean.getId().getStringValue());
            // DomTarget.getTarget(bean.getId()

            variants.add(
                    LookupElementBuilder.create(bean.getId().getXmlAttribute().getValueElement(), bean.getId().getStringValue())
                            .withIcon(PluginIcons.CAMEL)
                            .withPresentableText(bean.getId().getStringValue())
                            .withTailText("(" + bean.getClassAttribute().getStringValue() + ")")
                            .withTypeText("bean")
            );
        }

        return variants.toArray();
    }
}
