//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 12 August 2013
// Please do not manually modify this class.
//
package me.alanfoster.camelry.camel.dom;

import com.intellij.util.xml.NamedEnum;


/**
 * @author Alan
 */
public enum ShutdownRoute implements NamedEnum {
    
    DEFAULT("Default"),
	DEFER("Defer");

    private final String value;
    private ShutdownRoute(String value) { this.value = value; }
    public String getValue() { return value; }
}