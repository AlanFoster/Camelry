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
//@SubTag("secureXML")
public interface XMLSecurityDataFormat extends DataFormatDefinition, DomElement  {
                    @NotNull
        @Attribute("xmlCipherAlgorithm")
        GenericAttributeValue<String> getXmlCipherAlgorithm();
                    @NotNull
        @Attribute("passPhrase")
        GenericAttributeValue<String> getPassPhrase();
                    @NotNull
        @Attribute("secureTag")
        GenericAttributeValue<String> getSecureTag();
                    @NotNull
        @Attribute("secureTagContents")
        GenericAttributeValue<Boolean> getSecureTagContents();
                    @NotNull
        @Attribute("keyCipherAlgorithm")
        GenericAttributeValue<String> getKeyCipherAlgorithm();
                    @NotNull
        @Attribute("recipientKeyAlias")
        GenericAttributeValue<String> getRecipientKeyAlias();
                    @NotNull
        @Attribute("keyOrTrustStoreParametersId")
        GenericAttributeValue<String> getKeyOrTrustStoreParametersId();
    
    
    }