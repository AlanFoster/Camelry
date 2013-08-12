//
// Note - This file was automatically generated
// Generation spawned by 'class me.alanfoster.camelry.codegen.ScalateGenerator$'
// Creation Date - 12 August 2013
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
//@SubTag("secureXML")
public interface XMLSecurityDataFormat extends DataFormatDefinition, DomElement  {
                    @NotNull
        GenericAttributeValue<String> getXmlCipherAlgorithm();
                    @NotNull
        GenericAttributeValue<String> getPassPhrase();
                    @NotNull
        GenericAttributeValue<String> getSecureTag();
                    @NotNull
        GenericAttributeValue<Boolean> getSecureTagContents();
                    @NotNull
        GenericAttributeValue<String> getKeyCipherAlgorithm();
                    @NotNull
        GenericAttributeValue<String> getRecipientKeyAlias();
                    @NotNull
        GenericAttributeValue<String> getKeyOrTrustStoreParametersId();
    
    
    }