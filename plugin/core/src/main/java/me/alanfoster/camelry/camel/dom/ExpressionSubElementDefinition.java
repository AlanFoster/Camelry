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
//@SubTag("expression")
public interface ExpressionSubElementDefinition extends DomElement  {
    
        
        
        
        @SubTagsList({"ognl", "spel", "header", "sql", "constant",
						"el", "expressionDefinition", "jxpath", "simple", "php",
						"javaScript", "language", "python", "ruby", "method",
						"xpath", "tokenize", "property", "ref", "xquery",
						"groovy", "mvel"})
                ExpressionDefinition getExpressionType();

        
        
                                            @SubTagList("xquery")
                XQueryExpression getXquery();
                                            @SubTagList("expressionDefinition")
                ExpressionDefinition getExpressionDefinition();
                                            @SubTagList("simple")
                SimpleExpression getSimple();
                                            @SubTagList("constant")
                ConstantExpression getConstant();
                                            @SubTagList("tokenize")
                TokenizerExpression getTokenize();
                                            @SubTagList("javaScript")
                JavaScriptExpression getJavaScript();
                                            @SubTagList("method")
                MethodCallExpression getMethod();
                                            @SubTagList("ruby")
                RubyExpression getRuby();
                                            @SubTagList("groovy")
                GroovyExpression getGroovy();
                                            @SubTagList("xpath")
                XPathExpression getXpath();
                                            @SubTagList("jxpath")
                JXPathExpression getJxpath();
                                            @SubTagList("el")
                ELExpression getEl();
                                            @SubTagList("mvel")
                MvelExpression getMvel();
                                            @SubTagList("sql")
                SqlExpression getSql();
                                            @SubTagList("ref")
                RefExpression getRef();
                                            @SubTagList("python")
                PythonExpression getPython();
                                            @SubTagList("spel")
                SpELExpression getSpel();
                                            @SubTagList("header")
                HeaderExpression getHeader();
                                            @SubTagList("php")
                PhpExpression getPhp();
                                            @SubTagList("property")
                PropertyExpression getProperty();
                                            @SubTagList("ognl")
                OgnlExpression getOgnl();
                                            @SubTagList("language")
                LanguageExpression getLanguage();
                        
    }