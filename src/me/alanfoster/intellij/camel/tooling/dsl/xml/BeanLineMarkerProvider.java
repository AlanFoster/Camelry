package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.XmlNamedElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import me.alanfoster.intellij.camel.icons.CamelIcons;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

import static com.intellij.patterns.XmlPatterns.xmlAttribute;
import static com.intellij.patterns.XmlPatterns.xmlTag;

/**
 * Marks the gutter line with a bean icon when a bean reference is
 * found within the camel dsl.
 *
 * This icon is clickable, and will take you to the bean definition
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BeanLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            Collection<? super RelatedItemLineMarkerInfo> result) {
        final XmlNamedElementPattern beanRefPattern = xmlAttribute().withLocalName("ref")
                .withSuperParent(1, xmlTag().withLocalName("bean"));

        if (beanRefPattern.accepts(element)) {
            XmlAttribute xmlRefAttribute = (XmlAttribute) element;
            String xmlRefValueString = xmlRefAttribute.getValueElement().getValue();

            Project project = element.getProject();
            final List<Bean> matchingBeans = BeanHelper.findBeans(project, xmlRefValueString);

            if (matchingBeans.size() > 0) {
                XmlAttributeValue matchingBeanIdValue = matchingBeans.get(0).getId().getXmlAttributeValue();

                NavigationGutterIconBuilder<PsiElement> builder =
                        NavigationGutterIconBuilder.create(CamelIcons.CAMEL)
                                .setTargets(matchingBeanIdValue)
                                .setTooltipText("Navigate to Bean Declaration");
                result.add(builder.createLineMarkerInfo(element));
            }

        }
    }
}
