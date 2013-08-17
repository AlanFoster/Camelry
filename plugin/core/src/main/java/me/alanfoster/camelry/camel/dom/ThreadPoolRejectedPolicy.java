//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 16 August 2013
// Please do not manually modify this class.
//
package me.alanfoster.camelry.camel.dom;

import com.intellij.util.xml.NamedEnum;


/**
 * @author Alan
 */
public enum ThreadPoolRejectedPolicy implements NamedEnum {
    
    ABORT("Abort"),
	CALLERRUNS("CallerRuns"),
	DISCARDOLDEST("DiscardOldest"),
	DISCARD("Discard");

    private final String value;
    private ThreadPoolRejectedPolicy(String value) { this.value = value; }
    public String getValue() { return value; }
}