// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelus.camel.simple.language.parser;

import org.jetbrains.annotations.*;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static me.alanfoster.camelus.camel.simple.language.psi.SimpleTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SimpleParser implements PsiParser {

  public static Logger LOG_ = Logger.getInstance("me.alanfoster.camelus.camel.simple.language.parser.SimpleParser");

  @NotNull
  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    int level_ = 0;
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this);
    if (root_ == BOOLEAN_LITERAL) {
      result_ = boolean_literal(builder_, level_ + 1);
    }
    else if (root_ == EQUALITY_EXPRESSION) {
      result_ = equality_expression(builder_, level_ + 1);
    }
    else if (root_ == EQUALITY_OPERATOR) {
      result_ = equality_operator(builder_, level_ + 1);
    }
    else if (root_ == IDENTIFIER) {
      result_ = identifier(builder_, level_ + 1);
    }
    else if (root_ == LITERAL) {
      result_ = literal(builder_, level_ + 1);
    }
    else if (root_ == MATH_OPERATOR) {
      result_ = math_operator(builder_, level_ + 1);
    }
    else if (root_ == MEMBER_ACCESS) {
      result_ = member_access(builder_, level_ + 1);
    }
    else if (root_ == MEMBER_ACCESS_KEY) {
      result_ = member_access_key(builder_, level_ + 1);
    }
    else if (root_ == NON_SIMPLE) {
      result_ = non_simple(builder_, level_ + 1);
    }
    else if (root_ == NUMERIC_LITERAL) {
      result_ = numeric_literal(builder_, level_ + 1);
    }
    else if (root_ == RELATIONAL_EXPRESSION) {
      result_ = relational_expression(builder_, level_ + 1);
    }
    else if (root_ == RELATIONAL_OPERATOR) {
      result_ = relational_operator(builder_, level_ + 1);
    }
    else if (root_ == RIGHT_HAND_SIDE) {
      result_ = right_hand_side(builder_, level_ + 1);
    }
    else if (root_ == SIMPLE) {
      result_ = simple(builder_, level_ + 1);
    }
    else if (root_ == SIMPLE_EXPRESSION) {
      result_ = simple_expression(builder_, level_ + 1);
    }
    else if (root_ == SIMPLE_EXPRESSION_BODY) {
      result_ = simple_expression_body(builder_, level_ + 1);
    }
    else if (root_ == SINGLE_STRING_CHARACTERS) {
      result_ = single_string_characters(builder_, level_ + 1);
    }
    else if (root_ == STRING_EXPRESSION) {
      result_ = string_expression(builder_, level_ + 1);
    }
    else if (root_ == STRING_LITERAL) {
      result_ = string_literal(builder_, level_ + 1);
    }
    else if (root_ == STRING_OPERATOR) {
      result_ = string_operator(builder_, level_ + 1);
    }
    else {
      Marker marker_ = builder_.mark();
      enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, null);
      result_ = parse_root_(root_, builder_, level_);
      exitErrorRecordingSection(builder_, level_, result_, true, _SECTION_RECOVER_, TOKEN_ADVANCER);
      marker_.done(root_);
    }
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return simpleFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // TRUE | FALSE
  public static boolean boolean_literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "boolean_literal")) return false;
    if (!nextTokenIs(builder_, FALSE) && !nextTokenIs(builder_, TRUE)
        && replaceVariants(builder_, 2, "<boolean literal>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<boolean literal>");
    result_ = consumeToken(builder_, TRUE);
    if (!result_) result_ = consumeToken(builder_, FALSE);
    if (result_) {
      marker_.done(BOOLEAN_LITERAL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // simple_expression equality_operator right_hand_side
  public static boolean equality_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "equality_expression")) return false;
    if (!nextTokenIs(builder_, FUNCTION_START)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = simple_expression(builder_, level_ + 1);
    result_ = result_ && equality_operator(builder_, level_ + 1);
    result_ = result_ && right_hand_side(builder_, level_ + 1);
    if (result_) {
      marker_.done(EQUALITY_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // NOT_EQUALS | EQUALS_EQUALS
  public static boolean equality_operator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "equality_operator")) return false;
    if (!nextTokenIs(builder_, EQUALS_EQUALS) && !nextTokenIs(builder_, NOT_EQUALS)
        && replaceVariants(builder_, 2, "<equality operator>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<equality operator>");
    result_ = consumeToken(builder_, NOT_EQUALS);
    if (!result_) result_ = consumeToken(builder_, EQUALS_EQUALS);
    if (result_) {
      marker_.done(EQUALITY_OPERATOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // SINGLE_CHARACTER (SINGLE_CHARACTER | NUMBER)*
  public static boolean identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifier")) return false;
    if (!nextTokenIs(builder_, SINGLE_CHARACTER)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SINGLE_CHARACTER);
    result_ = result_ && identifier_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(IDENTIFIER);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // (SINGLE_CHARACTER | NUMBER)*
  private static boolean identifier_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifier_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!identifier_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "identifier_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // SINGLE_CHARACTER | NUMBER
  private static boolean identifier_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifier_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SINGLE_CHARACTER);
    if (!result_) result_ = consumeToken(builder_, NUMBER);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // simple
  //     | non_simple
  //     | CRLF
  static boolean item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = simple(builder_, level_ + 1);
    if (!result_) result_ = non_simple(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, CRLF);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // numeric_literal | string_literal | boolean_literal
  public static boolean literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literal")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<literal>");
    result_ = numeric_literal(builder_, level_ + 1);
    if (!result_) result_ = string_literal(builder_, level_ + 1);
    if (!result_) result_ = boolean_literal(builder_, level_ + 1);
    if (result_) {
      marker_.done(LITERAL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // PLUS | MINUS | MULTIPLY | DIVIDE
  public static boolean math_operator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "math_operator")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<math operator>");
    result_ = consumeToken(builder_, PLUS);
    if (!result_) result_ = consumeToken(builder_, MINUS);
    if (!result_) result_ = consumeToken(builder_, MULTIPLY);
    if (!result_) result_ = consumeToken(builder_, DIVIDE);
    if (result_) {
      marker_.done(MATH_OPERATOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // LEFTSQUARE member_access_key RIGHTSQUARE
  //         | DOT identifier
  public static boolean member_access(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "member_access")) return false;
    if (!nextTokenIs(builder_, DOT) && !nextTokenIs(builder_, LEFTSQUARE)
        && replaceVariants(builder_, 2, "<member access>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<member access>");
    result_ = member_access_0(builder_, level_ + 1);
    if (!result_) result_ = member_access_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(MEMBER_ACCESS);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // LEFTSQUARE member_access_key RIGHTSQUARE
  private static boolean member_access_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "member_access_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFTSQUARE);
    result_ = result_ && member_access_key(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHTSQUARE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // DOT identifier
  private static boolean member_access_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "member_access_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOT);
    result_ = result_ && identifier(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "last" | "last" MINUS number
  //     identifier | string_literal | numeric_literal
  public static boolean member_access_key(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "member_access_key")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<member access key>");
    result_ = consumeToken(builder_, "last");
    if (!result_) result_ = member_access_key_1(builder_, level_ + 1);
    if (!result_) result_ = string_literal(builder_, level_ + 1);
    if (!result_) result_ = numeric_literal(builder_, level_ + 1);
    if (result_) {
      marker_.done(MEMBER_ACCESS_KEY);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // "last" MINUS number
  //     identifier
  private static boolean member_access_key_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "member_access_key_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, "last");
    result_ = result_ && consumeTokens(builder_, 0, MINUS, NUMBER);
    result_ = result_ && identifier(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // (SINGLE_CHARACTER | NUMBER | APOSTROPHE)+
  public static boolean non_simple(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "non_simple")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<non simple>");
    result_ = non_simple_0(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!non_simple_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "non_simple");
        break;
      }
      offset_ = next_offset_;
    }
    if (result_) {
      marker_.done(NON_SIMPLE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // SINGLE_CHARACTER | NUMBER | APOSTROPHE
  private static boolean non_simple_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "non_simple_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SINGLE_CHARACTER);
    if (!result_) result_ = consumeToken(builder_, NUMBER);
    if (!result_) result_ = consumeToken(builder_, APOSTROPHE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // NUMBER
  public static boolean numeric_literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "numeric_literal")) return false;
    if (!nextTokenIs(builder_, NUMBER)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NUMBER);
    if (result_) {
      marker_.done(NUMERIC_LITERAL);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // simple_expression relational_operator right_hand_side
  public static boolean relational_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "relational_expression")) return false;
    if (!nextTokenIs(builder_, FUNCTION_START)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = simple_expression(builder_, level_ + 1);
    result_ = result_ && relational_operator(builder_, level_ + 1);
    result_ = result_ && right_hand_side(builder_, level_ + 1);
    if (result_) {
      marker_.done(RELATIONAL_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // GREATER_OR_EQUAL | GREATER | LESS_OR_EQUAL | LESS
  public static boolean relational_operator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "relational_operator")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<relational operator>");
    result_ = consumeToken(builder_, GREATER_OR_EQUAL);
    if (!result_) result_ = consumeToken(builder_, GREATER);
    if (!result_) result_ = consumeToken(builder_, LESS_OR_EQUAL);
    if (!result_) result_ = consumeToken(builder_, LESS);
    if (result_) {
      marker_.done(RELATIONAL_OPERATOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // simple_expression | literal
  public static boolean right_hand_side(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "right_hand_side")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<right hand side>");
    result_ = simple_expression(builder_, level_ + 1);
    if (!result_) result_ = literal(builder_, level_ + 1);
    if (result_) {
      marker_.done(RIGHT_HAND_SIDE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // equality_expression
  //         | string_expression
  //         | relational_expression
  //         | simple_expression
  public static boolean simple(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simple")) return false;
    if (!nextTokenIs(builder_, FUNCTION_START)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = equality_expression(builder_, level_ + 1);
    if (!result_) result_ = string_expression(builder_, level_ + 1);
    if (!result_) result_ = relational_expression(builder_, level_ + 1);
    if (!result_) result_ = simple_expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(SIMPLE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // item_*
  static boolean simpleFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simpleFile")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!item_(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "simpleFile");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // FUNCTION_START simple_expression_body FUNCTION_END
  public static boolean simple_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simple_expression")) return false;
    if (!nextTokenIs(builder_, FUNCTION_START)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, FUNCTION_START);
    result_ = result_ && simple_expression_body(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, FUNCTION_END);
    if (result_) {
      marker_.done(SIMPLE_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // identifier (member_access)*
  public static boolean simple_expression_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simple_expression_body")) return false;
    if (!nextTokenIs(builder_, SINGLE_CHARACTER)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = identifier(builder_, level_ + 1);
    result_ = result_ && simple_expression_body_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(SIMPLE_EXPRESSION_BODY);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // (member_access)*
  private static boolean simple_expression_body_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simple_expression_body_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!simple_expression_body_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "simple_expression_body_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // (member_access)
  private static boolean simple_expression_body_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simple_expression_body_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = member_access(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // (SINGLE_CHARACTER | NUMBER)*
  public static boolean single_string_characters(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "single_string_characters")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<single string characters>");
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!single_string_characters_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "single_string_characters");
        break;
      }
      offset_ = next_offset_;
    }
    marker_.done(SINGLE_STRING_CHARACTERS);
    exitErrorRecordingSection(builder_, level_, true, false, _SECTION_GENERAL_, null);
    return true;
  }

  // SINGLE_CHARACTER | NUMBER
  private static boolean single_string_characters_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "single_string_characters_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SINGLE_CHARACTER);
    if (!result_) result_ = consumeToken(builder_, NUMBER);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // simple_expression string_operator right_hand_side
  public static boolean string_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_expression")) return false;
    if (!nextTokenIs(builder_, FUNCTION_START)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = simple_expression(builder_, level_ + 1);
    result_ = result_ && string_operator(builder_, level_ + 1);
    result_ = result_ && right_hand_side(builder_, level_ + 1);
    if (result_) {
      marker_.done(STRING_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // SPEECHMARK single_string_characters SPEECHMARK
  //     | APOSTROPHE single_string_characters APOSTROPHE
  public static boolean string_literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_literal")) return false;
    if (!nextTokenIs(builder_, APOSTROPHE) && !nextTokenIs(builder_, SPEECHMARK)
        && replaceVariants(builder_, 2, "<string literal>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<string literal>");
    result_ = string_literal_0(builder_, level_ + 1);
    if (!result_) result_ = string_literal_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(STRING_LITERAL);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // SPEECHMARK single_string_characters SPEECHMARK
  private static boolean string_literal_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_literal_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SPEECHMARK);
    result_ = result_ && single_string_characters(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SPEECHMARK);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // APOSTROPHE single_string_characters APOSTROPHE
  private static boolean string_literal_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_literal_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, APOSTROPHE);
    result_ = result_ && single_string_characters(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, APOSTROPHE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // CONTAINS_NOT | CONTAINS
  //     | REGEX_NOT | REGEX
  //     | NOT_IN | IN
  //     | NOT_IS | IS
  //     | NOT_RANGE | RANGE
  public static boolean string_operator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_operator")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<string operator>");
    result_ = consumeToken(builder_, CONTAINS_NOT);
    if (!result_) result_ = consumeToken(builder_, CONTAINS);
    if (!result_) result_ = consumeToken(builder_, REGEX_NOT);
    if (!result_) result_ = consumeToken(builder_, REGEX);
    if (!result_) result_ = consumeToken(builder_, NOT_IN);
    if (!result_) result_ = consumeToken(builder_, IN);
    if (!result_) result_ = consumeToken(builder_, NOT_IS);
    if (!result_) result_ = consumeToken(builder_, IS);
    if (!result_) result_ = consumeToken(builder_, NOT_RANGE);
    if (!result_) result_ = consumeToken(builder_, RANGE);
    if (result_) {
      marker_.done(STRING_OPERATOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

}
