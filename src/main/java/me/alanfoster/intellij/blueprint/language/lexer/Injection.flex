/**
* This file represents the Lexer for the blueprint injection language.
 * The lexer scans through a document and returns a stream of tokens which match
 * the production rules.
 *
 * This file uses the JFlex notation.
 */
package me.alanfoster.intellij.blueprint.language.lexer;


import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import me.alanfoster.intellij.blueprint.language.InjectionTypes;


/**
 * Below are the options and declarations which the lexer will use
 */
%%
// Defines the lexer class which will be generated
// IE, the name of the lexer class will be called 'InjectionLexer'
%public
%class InjectionLexer
%implements FlexLexer
%unicode
// The name of the scanning method
// this overrides the default method name which is normally 'yylex'
%function advance
// The return type of the scanner method, IE, the IntelliJ PSI Type
%type IElementType
%eof{  return;
%eof}
// %debug -- Generates a lot of noise

// Define any macros
CRLF= \n|\r|\r\n
WHITE_SPACE=[\ \t\f]
FUNCTION_START="${"
FUNCTION_END="}"
PROPERTY_NAME=[^:=}]+
TEXT=[^:=${]+

// define any custom states to use
%state WAITING_PROPERTY_DEFINITION

%%


<YYINITIAL>{FUNCTION_START}                                     { yybegin(WAITING_PROPERTY_DEFINITION); return InjectionTypes.FUNCTION_START; }

<WAITING_PROPERTY_DEFINITION>{PROPERTY_NAME}                    { return InjectionTypes.PROPERTY_NAME; }

<WAITING_PROPERTY_DEFINITION>{FUNCTION_END}                     { yybegin(YYINITIAL); return InjectionTypes.FUNCTION_END; }

<YYINITIAL>{TEXT}                                               { yybegin(YYINITIAL); return InjectionTypes.TEXT; }

{CRLF}                                                          { yybegin(YYINITIAL); return InjectionTypes.CRLF; }

{WHITE_SPACE}+                                                   { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

.                                                   { return TokenType.BAD_CHARACTER; }