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
public enum ShutdownRunningTask implements NamedEnum {
    
    COMPLETECURRENTTASKONLY("CompleteCurrentTaskOnly"),
	COMPLETEALLTASKS("CompleteAllTasks");

    private final String value;
    private ShutdownRunningTask(String value) { this.value = value; }
    public String getValue() { return value; }
}