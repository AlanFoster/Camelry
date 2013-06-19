// This is a generated file. Not intended for manual editing.
package me.alanfoster.intellij.camel.simple.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import me.alanfoster.intellij.camel.simple.language.impl.*;

public interface SimpleTypes {

  IElementType BOOLEAN_LITERAL = new SimpleElementType("BOOLEAN_LITERAL");
  IElementType EQUALITY_EXPRESSION = new SimpleElementType("EQUALITY_EXPRESSION");
  IElementType EQUALITY_OPERATOR = new SimpleElementType("EQUALITY_OPERATOR");
  IElementType IDENTIFIER = new SimpleElementType("IDENTIFIER");
  IElementType LITERAL = new SimpleElementType("LITERAL");
  IElementType MATH_OPERATOR = new SimpleElementType("MATH_OPERATOR");
  IElementType MEMBER_ACCESS = new SimpleElementType("MEMBER_ACCESS");
  IElementType MEMBER_ACCESS_KEY = new SimpleElementType("MEMBER_ACCESS_KEY");
  IElementType NON_SIMPLE = new SimpleElementType("NON_SIMPLE");
  IElementType NUMERIC_LITERAL = new SimpleElementType("NUMERIC_LITERAL");
  IElementType RELATIONAL_EXPRESSION = new SimpleElementType("RELATIONAL_EXPRESSION");
  IElementType RELATIONAL_OPERATOR = new SimpleElementType("RELATIONAL_OPERATOR");
  IElementType RIGHT_HAND_SIDE = new SimpleElementType("RIGHT_HAND_SIDE");
  IElementType SIMPLE = new SimpleElementType("SIMPLE");
  IElementType SIMPLE_EXPRESSION = new SimpleElementType("SIMPLE_EXPRESSION");
  IElementType SIMPLE_EXPRESSION_BODY = new SimpleElementType("SIMPLE_EXPRESSION_BODY");
  IElementType SINGLE_STRING_CHARACTERS = new SimpleElementType("SINGLE_STRING_CHARACTERS");
  IElementType STRING_EXPRESSION = new SimpleElementType("STRING_EXPRESSION");
  IElementType STRING_LITERAL = new SimpleElementType("STRING_LITERAL");
  IElementType STRING_OPERATOR = new SimpleElementType("STRING_OPERATOR");

  IElementType APOSTROPHE = new SimpleTokenType("APOSTROPHE");
  IElementType CONTAINS = new SimpleTokenType("CONTAINS");
  IElementType CONTAINS_NOT = new SimpleTokenType("CONTAINS_NOT");
  IElementType CRLF = new SimpleTokenType("CRLF");
  IElementType DIVIDE = new SimpleTokenType("DIVIDE");
  IElementType DOT = new SimpleTokenType("DOT");
  IElementType EQUALS_EQUALS = new SimpleTokenType("EQUALS_EQUALS");
  IElementType FALSE = new SimpleTokenType("FALSE");
  IElementType FUNCTION_END = new SimpleTokenType("FUNCTION_END");
  IElementType FUNCTION_START = new SimpleTokenType("FUNCTION_START");
  IElementType GREATER = new SimpleTokenType("GREATER");
  IElementType GREATER_OR_EQUAL = new SimpleTokenType("GREATER_OR_EQUAL");
  IElementType IN = new SimpleTokenType("IN");
  IElementType IS = new SimpleTokenType("IS");
  IElementType LEFTSQUARE = new SimpleTokenType("LEFTSQUARE");
  IElementType LESS = new SimpleTokenType("LESS");
  IElementType LESS_OR_EQUAL = new SimpleTokenType("LESS_OR_EQUAL");
  IElementType MINUS = new SimpleTokenType("MINUS");
  IElementType MULTIPLY = new SimpleTokenType("MULTIPLY");
  IElementType NOT_EQUALS = new SimpleTokenType("NOT_EQUALS");
  IElementType NOT_IN = new SimpleTokenType("NOT_IN");
  IElementType NOT_IS = new SimpleTokenType("NOT_IS");
  IElementType NOT_RANGE = new SimpleTokenType("NOT_RANGE");
  IElementType NUMBER = new SimpleTokenType("number");
  IElementType PLUS = new SimpleTokenType("PLUS");
  IElementType RANGE = new SimpleTokenType("RANGE");
  IElementType REGEX = new SimpleTokenType("REGEX");
  IElementType REGEX_NOT = new SimpleTokenType("REGEX_NOT");
  IElementType RIGHTSQUARE = new SimpleTokenType("RIGHTSQUARE");
  IElementType SINGLE_CHARACTER = new SimpleTokenType("SINGLE_CHARACTER");
  IElementType SPEECHMARK = new SimpleTokenType("SPEECHMARK");
  IElementType TRUE = new SimpleTokenType("TRUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == BOOLEAN_LITERAL) {
        return new SimpleBooleanLiteralImpl(node);
      }
      else if (type == EQUALITY_EXPRESSION) {
        return new SimpleEqualityExpressionImpl(node);
      }
      else if (type == EQUALITY_OPERATOR) {
        return new SimpleEqualityOperatorImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new SimpleIdentifierImpl(node);
      }
      else if (type == LITERAL) {
        return new SimpleLiteralImpl(node);
      }
      else if (type == MATH_OPERATOR) {
        return new SimpleMathOperatorImpl(node);
      }
      else if (type == MEMBER_ACCESS) {
        return new SimpleMemberAccessImpl(node);
      }
      else if (type == MEMBER_ACCESS_KEY) {
        return new SimpleMemberAccessKeyImpl(node);
      }
      else if (type == NON_SIMPLE) {
        return new SimpleNonSimpleImpl(node);
      }
      else if (type == NUMERIC_LITERAL) {
        return new SimpleNumericLiteralImpl(node);
      }
      else if (type == RELATIONAL_EXPRESSION) {
        return new SimpleRelationalExpressionImpl(node);
      }
      else if (type == RELATIONAL_OPERATOR) {
        return new SimpleRelationalOperatorImpl(node);
      }
      else if (type == RIGHT_HAND_SIDE) {
        return new SimpleRightHandSideImpl(node);
      }
      else if (type == SIMPLE) {
        return new SimpleSimpleImpl(node);
      }
      else if (type == SIMPLE_EXPRESSION) {
        return new SimpleSimpleExpressionImpl(node);
      }
      else if (type == SIMPLE_EXPRESSION_BODY) {
        return new SimpleSimpleExpressionBodyImpl(node);
      }
      else if (type == SINGLE_STRING_CHARACTERS) {
        return new SimpleSingleStringCharactersImpl(node);
      }
      else if (type == STRING_EXPRESSION) {
        return new SimpleStringExpressionImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new SimpleStringLiteralImpl(node);
      }
      else if (type == STRING_OPERATOR) {
        return new SimpleStringOperatorImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
