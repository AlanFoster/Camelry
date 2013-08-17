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
public enum ExchangePattern implements NamedEnum {
    
    INONLY("InOnly"),
	ROBUSTINONLY("RobustInOnly"),
	INOUT("InOut"),
	INOPTIONALOUT("InOptionalOut"),
	OUTONLY("OutOnly"),
	ROBUSTOUTONLY("RobustOutOnly"),
	OUTIN("OutIn"),
	OUTOPTIONALIN("OutOptionalIn");

    private final String value;
    private ExchangePattern(String value) { this.value = value; }
    public String getValue() { return value; }
}