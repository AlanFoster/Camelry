package me.alanfoster.camelry.camel.dom;

import com.intellij.util.xml.DomElement;

/**
 * This interface deals with the setBody element ie
 *
 * <setBody>
 *  <expression>...</expression>
 * </setBody>
 *
 * TODO - Is there a way to have a 'choice' subTag in camelry?
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public interface SetBody extends DomElement {
    Expression getExpression();
    Method getMethod();
}
