/**
 * The Lexing definition for the Apache Camel Simple Language
 * The lexer scans through a document and returns a stream of tokens which match
 * the production rules.
 *
 * This file uses the JFlex notation.
*/
package me.alanfoster.intellij.camel.simple.language.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

import me.alanfoster.intellij.camel.simple.language.psi.SimpleTypes;

/**
 * Below are the options and declarations which the lexer will use
 */
%%
// Defines the lexer class which will be generated
// IE, the name of the lexer class will be called 'SimpleLexer'
%public
%class SimpleLexer
%implements FlexLexer
%unicode
// The name of the scanning method
// this overrides the default method name which is normally 'yylex'
%function advance
// The return type of the scanner method, IE, the IntelliJ PSI Type
%type IElementType
%eof{  return;
%eof}

// Define any Macros
FUNCTION_START="${"
IDENTIFIER=[:jletter:] [:jletterdigit:]*
FUNCTION_END="}"
NUMBER=[0-9]+

CRLF= \n|\r|\r\n
WHITE_SPACE=[\ \t\f]

// Define any custom states to be used
%state FUNCTION_STATE

%%

//<YYINITIAL> {FUNCTION_START} { yybegin(FUNCTION_STATE); return SimpleTypes.FUNCTION_START; }
//<FUNCTION_STATE> {IDENTIFIER} { return SimpleTypes.IDENTIFIER; }
//<FUNCTION_STATE> {FUNCTION_END} { yybegin(YYINITIAL); return SimpleTypes.FUNCTION_END; }

<YYINITIAL> "true" { return SimpleTypes.TRUE; }
<YYINITIAL> "false" { return SimpleTypes.FALSE; }

<YYINITIAL> {FUNCTION_START} { return SimpleTypes.FUNCTION_START; }
<YYINITIAL> {IDENTIFIER} { return SimpleTypes.IDENTIFIER; }
<YYINITIAL> {FUNCTION_END} {  return SimpleTypes.FUNCTION_END; }

<YYINITIAL> {NUMBER} { return SimpleTypes.NUMBER; }

// Operators
<YYINITIAL> "==" { return SimpleTypes.EQUALS_EQUALS; }

<YYINITIAL> ">=" { return SimpleTypes.GREATER_OR_EQUAL; }
<YYINITIAL> ">" { return SimpleTypes.GREATER; }

<YYINITIAL> "<=" { return SimpleTypes.LESS_OR_EQUAL; }
<YYINITIAL> "<" { return SimpleTypes.LESS; }

// Punctuation
<YYINITIAL> "[" { return SimpleTypes.LEFTSQUARE; }
<YYINITIAL> "]" { return SimpleTypes.RIGHTSQUARE; }
<YYINITIAL> "." { return SimpleTypes.DOT; }
<YYINITIAL> "'" { return SimpleTypes.APOSTROPHE; }

// Strings
<YYINITIAL> [:jletter:] { return SimpleTypes.SINGLE_CHARACTER; }


{CRLF} { return SimpleTypes.CRLF; }

{WHITE_SPACE}+ { return TokenType.WHITE_SPACE; }

// Match all the remaining lexeme, as it is something our production rules do not understand
// An alternative is to throw an exception, but we can provide better feedback to the user
// if we match it and provide a new token type for such lexemes
. { return TokenType.BAD_CHARACTER; }
