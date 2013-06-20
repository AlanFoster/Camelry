package me.alanfoster.intellij.karaf.dom;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.highlighting.BasicDomElementsInspection;
import com.intellij.util.xml.highlighting.DomElementAnnotationHolder;
import com.intellij.util.xml.highlighting.DomHighlightingHelper;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class KarafDomValidation extends BasicDomElementsInspection<KarafFeaturesRoot> {

    public KarafDomValidation() {
        super(KarafFeaturesRoot.class);
    }

    @Override
    protected void checkDomElement(DomElement element, DomElementAnnotationHolder holder, DomHighlightingHelper helper) {
        super.checkDomElement(element, holder, helper);
    }


}
