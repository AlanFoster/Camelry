package me.alanfoster.camelry.blueprint.dom.converters;

import com.intellij.codeInsight.daemon.EmptyResolveMessageProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.CustomReferenceConverter;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericDomValue;
import me.alanfoster.camelry.CamelryBundle;
import me.alanfoster.camelry.blueprint.dom.model.BlueprintBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.beans.Introspector;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used as the Property Resolver for Blueprint Beans.
 * Blueprint currently supports only method level getter/setter access only.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see me.alanfoster.camelry.blueprint.dom.model.BeanProperty
 */
// TODO This seems like it would be a common pattern in IntelliJ, try to find if there is a class for this already!
public class BeanPropertyResolver implements CustomReferenceConverter<String> {

    @NotNull
    @Override
    public PsiReference[] createReferences(final GenericDomValue<String> propertyWrapper, final PsiElement psiElement, final ConvertContext convertContext) {
        final PsiReferenceBase<PsiElement> reference = new PsiElementPsiReferenceBase(psiElement, propertyWrapper, convertContext);

        return new PsiReference[] { reference };
    }

    private static class PsiElementPsiReferenceBase extends PsiReferenceBase<PsiElement> implements EmptyResolveMessageProvider {
        private static final String SETTER_PREFIX = "set";
        private final GenericDomValue<String> propertyWrapper;
        private final ConvertContext convertContext;

        public PsiElementPsiReferenceBase(PsiElement psiElement, GenericDomValue<String> propertyWrapper, ConvertContext convertContext) {
            super(psiElement);
            this.propertyWrapper = propertyWrapper;
            this.convertContext = convertContext;
        }

        @Nullable
        @Override
        public PsiElement resolve() {
            String propertyName = propertyWrapper.getStringValue();
            final PsiClass beanClass = getBeanClass();

            if((propertyName == null || propertyName.trim().isEmpty()) || beanClass == null) {
                return null;
            }

            String expectedMethodName = SETTER_PREFIX + capitaliseFirst(propertyName);

            // Hunt for the matching method name
            for (PsiMethod method : beanClass.getAllMethods()) {
                String actualMethodName = method.getName();
                if(expectedMethodName.equals(actualMethodName)) {
                    return method;
                }
            }

            return null;
        }

        private String capitaliseFirst(@NotNull String propertyName) {
            return StringUtil.capitalizeWithJavaBeanConvention(propertyName);
        }

        @Override
        public boolean isSoft() {
            return true;
        }

        /**
         * Return all methods which can be injected with blueprint.
         *
         * @return
         */
        @NotNull
        @Override
        public Object[] getVariants() {
            PsiClass beanClass = getBeanClass();

            if(beanClass == null) {
                return EMPTY_ARRAY;
            }

            // Note, we use Set as we don't want duplicates
            Set<String> availableInjectionNames = new HashSet<String>();

            for (PsiMethod method : beanClass.getAllMethods()) {
                String methodName = method.getName();
                if(methodName.startsWith(SETTER_PREFIX)) {
                    String injectionName = Introspector.decapitalize(methodName.substring(SETTER_PREFIX.length()));
                    availableInjectionNames.add(injectionName);
                }
            }

            return availableInjectionNames.toArray();
        }

        private PsiClass getBeanClass() {
            final DomElement domElement = convertContext.getInvocationElement();
            final BlueprintBean bean = domElement.getParentOfType(BlueprintBean.class, true);
            if(bean == null) return null;

            PsiClass beanClass = bean.getClassAttribute().getValue();
            return beanClass;
        }

        @NotNull
        @Override
        public String getUnresolvedMessagePattern() {
            return CamelryBundle.message("camelus.camel.beanpropertyresolver.unresolvedmessagepattern");
        }
    }
}
