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
public enum ManagementStatisticsLevel implements NamedEnum {
    
    ALL("All"),
	ROUTESONLY("RoutesOnly"),
	OFF("Off");

    private final String value;
    private ManagementStatisticsLevel(String value) { this.value = value; }
    public String getValue() { return value; }
}