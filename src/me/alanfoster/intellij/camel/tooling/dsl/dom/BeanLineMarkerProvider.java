package me.alanfoster.intellij.camel.tooling.dsl.dom;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

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

    // TODO I believe we can register a Presentation handler to do this for us now?
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            Collection<? super RelatedItemLineMarkerInfo> result) {
        /*final XmlNamedElementPattern beanRefPattern = xmlAttribute().withLocalName("ref")
                .withSuperParent(1, xmlTag().withLocalName("bean"));

        if (beanRefPattern.accepts(element)) {
            XmlAttribute xmlRefAttribute = (XmlAttribute) element;
            String xmlRefValueString = xmlRefAttribute.getValueElement().getValue();

            Project project = element.getProject();
            final List<BlueprintBean> matchingBeans = BeanHelper.findBeans(project, xmlRefValueString);

            if (matchingBeans.size() > 0) {
                XmlAttributeValue matchingBeanIdValue = matchingBeans.get(0).getId().getXmlAttributeValue();

                NavigationGutterIconBuilder<PsiElement> builder =
                        NavigationGutterIconBuilder.create(CamelIcons.CAMEL)
                                .setTargets(matchingBeanIdValue)
                                .setTooltipText("Navigate to Bean Declaration");
                result.add(builder.createLineMarkerInfo(element));
            }

        }*/
    }
}
