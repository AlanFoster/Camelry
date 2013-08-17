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
public enum LoggingLevel implements NamedEnum {
    
    DEBUG("DEBUG"),
	ERROR("ERROR"),
	INFO("INFO"),
	TRACE("TRACE"),
	WARN("WARN"),
	OFF("OFF");

    private final String value;
    private LoggingLevel(String value) { this.value = value; }
    public String getValue() { return value; }
}