package me.alanfoster.camelus.blueprint.inspectors;

import com.intellij.util.xml.highlighting.BasicDomElementsInspection;
import me.alanfoster.camelus.blueprint.dom.Blueprint;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BlueprintDOMInspection extends BasicDomElementsInspection<Blueprint> {

    public BlueprintDOMInspection() {
        super(Blueprint.class);
    }

}
