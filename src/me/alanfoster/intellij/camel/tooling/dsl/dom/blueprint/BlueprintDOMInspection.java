package me.alanfoster.intellij.camel.tooling.dsl.dom.blueprint;

import com.intellij.util.xml.highlighting.BasicDomElementsInspection;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class BlueprintDOMInspection extends BasicDomElementsInspection<Blueprint> {

    public BlueprintDOMInspection() {
        super(Blueprint.class);
    }

}
