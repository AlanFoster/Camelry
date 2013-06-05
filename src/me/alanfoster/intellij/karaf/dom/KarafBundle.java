package me.alanfoster.intellij.karaf.dom;

import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.GenericDomValue;
import com.intellij.util.xml.TagValue;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface KarafBundle extends DomElement {
    @TagValue
    GenericDomValue<String> getValue();
}
