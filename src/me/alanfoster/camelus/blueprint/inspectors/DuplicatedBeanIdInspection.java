package me.alanfoster.camelus.blueprint.inspectors;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericAttributeValue;
import com.intellij.util.xml.highlighting.DomElementAnnotationHolder;
import com.intellij.util.xml.highlighting.DomElementsInspection;
import com.intellij.util.xml.highlighting.DomHighlightingHelper;
import me.alanfoster.camelus.CamelusBundle;
import me.alanfoster.camelus.blueprint.dom.Blueprint;
import me.alanfoster.camelus.blueprint.dom.BlueprintBean;
import me.alanfoster.camelus.blueprint.model.BlueprintManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Inspection for ensuring one bundle does not have duplicate BlueprintBean ids
 */
public class DuplicatedBeanIdInspection extends DomElementsInspection<Blueprint> {

    public static Logger logger = Logger.getInstance("me.alanfoster.camelus.blueprint.inspectors.DuplicatedBeanIdInspection");

    public DuplicatedBeanIdInspection() {
        super(Blueprint.class);
    }

    @Override
    protected void checkDomElement(final DomElement element,
                                   final DomElementAnnotationHolder holder,
                                   final DomHighlightingHelper helper) {

        if(element instanceof BlueprintBean) {
            BlueprintBean currentBlueprintBean = (BlueprintBean) element;
            GenericAttributeValue<String> idAttribute = currentBlueprintBean.getName();
            String id = idAttribute.getStringValue();
            if(id == null) return;

            Module module = ModuleUtil.findModuleForPsiElement(element.getXmlTag());

            if(module == null) {
                logger.error("Module value was null for searchId '" + id + "' - Possibly due to an in memory editor?");
                return;
            }

            List<BlueprintBean> duplicatedBeans = getDuplicatedBeans(id, module);
            if(duplicatedBeans.isEmpty()) return;

            // TODO Should we add a quick fix option, so the user can easily check the conflicting ids ?
            holder.createProblem(idAttribute, HighlightSeverity.ERROR, CamelusBundle.message("camelus.blueprint.dom.duplicate.bean.id"));
        }
    }

    /**
     * Gets the duplicated beans within the given module.
     *
     * @param searchId the bean ID which should match
     * @param module The scope to search within
     * @return An empty list if the searchId unique, otherwise the list of duplicates.
     */
    @NotNull
    private List<BlueprintBean> getDuplicatedBeans(@NotNull String searchId, @NotNull Module module) {
        Set<Blueprint> blueprintRoots = BlueprintManager.getInstance().getModuleBlueprintRoots(module);

        Map<String, List<BlueprintBean>> duplicates = new HashMap<String, List<BlueprintBean>>();

        // TODO Scala group by...
        for(Blueprint root : blueprintRoots) {
            for(BlueprintBean bean : root.getBeans()) {
                String id = bean.getName().getStringValue();
                List<BlueprintBean> groupedList;
                if(duplicates.containsKey(id)) {
                    groupedList = duplicates.get(id);
                } else {
                    groupedList = new ArrayList<BlueprintBean>();
                    duplicates.put(id, groupedList);
                }
                groupedList.add(bean);
            }
        }

        List<BlueprintBean> matchingIds = duplicates.get(searchId);

        return (matchingIds == null || matchingIds.size() <= 1)
                ? Collections.<BlueprintBean>emptyList()
                : matchingIds;
    }
}
