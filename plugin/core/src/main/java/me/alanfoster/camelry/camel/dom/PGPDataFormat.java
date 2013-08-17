//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 16 August 2013
// Please do not manually modify this class.
//
package me.alanfoster.camelry.camel.dom;

import com.intellij.util.xml.*;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.*;
import java.util.List;


/**
 * @author Alan
 */
//@SubTag("pgp")
public interface PGPDataFormat extends DataFormatDefinition, DomElement  {
                    @NotNull
        @Attribute("keyUserid")
        GenericAttributeValue<String> getKeyUserid();
                    @NotNull
        @Attribute("password")
        GenericAttributeValue<String> getPassword();
                    @NotNull
        @Attribute("keyFileName")
        GenericAttributeValue<String> getKeyFileName();
                    @NotNull
        @Attribute("armored")
        GenericAttributeValue<Boolean> getArmored();
                    @NotNull
        @Attribute("integrity")
        GenericAttributeValue<Boolean> getIntegrity();
    
    
    }