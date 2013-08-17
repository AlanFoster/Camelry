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
//@SubTag("crypto")
public interface CryptoDataFormat extends DataFormatDefinition, DomElement  {
                    @NotNull
        @Attribute("algorithm")
        GenericAttributeValue<String> getAlgorithm();
                    @NotNull
        @Attribute("cryptoProvider")
        GenericAttributeValue<String> getCryptoProvider();
                    @NotNull
        @Attribute("keyRef")
        GenericAttributeValue<String> getKeyRef();
                    @NotNull
        @Attribute("initVectorRef")
        GenericAttributeValue<String> getInitVectorRef();
                    @NotNull
        @Attribute("algorithmParameterRef")
        GenericAttributeValue<String> getAlgorithmParameterRef();
                    @NotNull
        @Attribute("buffersize")
        GenericAttributeValue<Integer> getBuffersize();
                    @NotNull
        @Attribute("macAlgorithm")
        GenericAttributeValue<String> getMacAlgorithm();
                    @NotNull
        @Attribute("shouldAppendHMAC")
        GenericAttributeValue<Boolean> getShouldAppendHMAC();
                    @NotNull
        @Attribute("inline")
        GenericAttributeValue<Boolean> getInline();
    
    
    }