package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.ResolvingConverter;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class ComponentDefinitionReferenceConverter extends ResolvingConverter<PsiClass> {
    @Nullable
    @Override
    public PsiClass fromString(@Nullable @NonNls String string, ConvertContext context) {
        ComponentDefinition componentDefinition = getComponentDefinition(string);

        if (componentDefinition == null) {
            return null;
        }

        final String componentName = componentDefinition.getComponentName();
        final Project project = context.getProject();

        // Find the matching Camel Component
        // Note - We can deduce this by the fact camel requires the convention
        // for discovering components to follow the following file format
        // "META-INF/services/org/apache/camel/component/FOO where FOO is the URI scheme for your component"
        // See - http://camel.apache.org/writing-components.html
        final PsiFile[] matchingFile = FilenameIndex.getFilesByName(project, componentName, GlobalSearchScope.allScope(project));
        for (PsiFile psiFile : matchingFile) {

            try {
                // Load the file as properties to make life easier, so we don't have to
                // write any sort of parser, and we can just grab the value for the class
                // location
                Properties properties = new Properties();
                properties.load(new StringReader(psiFile.getText()));

                String componentClassName = properties.getProperty("class");

                final PsiClass componentClass = JavaPsiFacade.getInstance(project).findClass(componentClassName, GlobalSearchScope.allScope(project));

                return componentClass;
            } catch (IOException e) {
                // TODO Wireup a logger...
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    @Nullable
    @Override
    public String toString(@Nullable PsiClass psiClass, ConvertContext context) {
        return psiClass == null ? "" : psiClass.toString();
    }

    @NotNull
    @Override
    public Collection<? extends PsiClass> getVariants(ConvertContext context) {
        return Collections.EMPTY_LIST;
    }

    @Nullable
    private ComponentDefinition getComponentDefinition(String componentString) {
        @SuppressWarnings("*")
        final Pattern compile = Pattern.compile("^(?<componentName>\\w+):(.+)$");
        final Matcher matcher = compile.matcher(componentString);
        if (matcher.matches()) {
            String componentName = matcher.group("componentName");
            ComponentDefinition componentDefinition = new ComponentDefinition(componentName);
            return componentDefinition;
        }
        return null;
    }
}
