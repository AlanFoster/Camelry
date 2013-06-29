package me.alanfoster.camelus.camel.dom;

import com.intellij.util.xml.DomElement;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface SetHeader extends DomElement {
    // TODO These definitions will be extremely similiar to setBody, is there a way to deal with this without duplication?
    Expression getExpression();
    Method getMethod();
}
