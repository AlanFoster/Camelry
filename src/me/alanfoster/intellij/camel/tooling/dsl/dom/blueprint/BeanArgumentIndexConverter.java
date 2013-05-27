package me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint;

import com.intellij.codeInsight.daemon.EmptyResolveMessageProvider;
import com.intellij.psi.*;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.CustomReferenceConverter;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericDomValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provides the support for finding a constructor index with a blueprint bean.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see BlueprintBean
 */
public class BeanArgumentIndexConverter implements CustomReferenceConverter<Integer> {
    @NotNull
    @Override
    public PsiReference[] createReferences(final GenericDomValue<Integer> argumentIndexWrapper, final PsiElement psiElement, final ConvertContext convertContext) {
        final PsiReferenceBase<PsiElement> reference = new BeanArgumentIndexPsiReference(psiElement, argumentIndexWrapper, convertContext);

        return new PsiReference[] { reference };
    }

    private static class BeanArgumentIndexPsiReference extends PsiReferenceBase<PsiElement> implements EmptyResolveMessageProvider {
        private final GenericDomValue<Integer> argumentIndexWrapper;
        private final ConvertContext convertContext;

        public BeanArgumentIndexPsiReference(PsiElement psiElement, GenericDomValue<Integer> argumentIndexWrapper, ConvertContext convertContext) {
            super(psiElement);
            this.argumentIndexWrapper = argumentIndexWrapper;
            this.convertContext = convertContext;
        }

        @Nullable
        @Override
        public PsiElement resolve() {
            //return argumentIndexWrapper.getParent().getXmlTag();

            Integer argumentIndex = argumentIndexWrapper.getValue();
            PsiClass beanClass = getBeanClass();

            if(argumentIndex == null || beanClass == null || argumentIndex < 0) {
                return null;
            }

            // Find the first matching constructor with n arguments.
            // TODO Learn how this should actually be resolved, rather than just picking the first constructor found
            for(PsiMethod constructor : beanClass.getConstructors()) {
                final PsiParameterList parameterList = constructor.getParameterList();
                if(argumentIndex >= 0 && argumentIndex < parameterList.getParametersCount()) {
                    return parameterList.getParameters()[argumentIndex];
                }
            }

            return null;
        }

        @Override
        public boolean isSoft() {
            return true;
        }

        @NotNull
        @Override
        public Object[] getVariants() {
            PsiClass beanClass = getBeanClass();

            Integer maxArgumentSize = 0;
            for(PsiMethod constructor :  beanClass.getConstructors()) {
                maxArgumentSize = Math.max(maxArgumentSize, constructor.getParameterList().getParametersCount());
            }

            String[] values = new String[maxArgumentSize];
            for(int i = 0; i < maxArgumentSize; i++) {
                values[i] = Integer.toString(i);
            }

            return values;
        }

        private PsiClass getBeanClass() {
            final DomElement domElement = convertContext.getInvocationElement();
            final BlueprintBean bean = domElement.getParentOfType(BlueprintBean.class, true);

            PsiClass beanClass = bean.getClassAttribute().getValue();
            return beanClass;
        }

        @NotNull
        @Override
        public String getUnresolvedMessagePattern() {
            return "No public constructor index found for this value";
        }
    }
}
