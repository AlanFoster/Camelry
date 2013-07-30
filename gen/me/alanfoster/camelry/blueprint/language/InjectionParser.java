// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelry.blueprint.language;

import org.jetbrains.annotations.*;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static me.alanfoster.camelry.blueprint.language.InjectionTypes.*;
import static me.alanfoster.camelry.camel.simple.language.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class InjectionParser implements PsiParser {

  public static Logger LOG_ = Logger.getInstance("me.alanfoster.camelry.blueprint.language.InjectionParser");

  @NotNull
  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    int level_ = 0;
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this);
    if (root_ == PROPERTY_DEFINITION) {
      result_ = propertyDefinition(builder_, level_ + 1);
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
    return injectionLanguage(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // item_*
  static boolean injectionLanguage(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "injectionLanguage")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!item_(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "injectionLanguage");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // TEXT | propertyDefinition | CRLF
  static boolean item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, TEXT);
    if (!result_) result_ = propertyDefinition(builder_, level_ + 1);
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
  // FUNCTION_START PROPERTY_NAME FUNCTION_END
  public static boolean propertyDefinition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "propertyDefinition")) return false;
    if (!nextTokenIs(builder_, FUNCTION_START)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeTokens(builder_, 0, FUNCTION_START, PROPERTY_NAME, FUNCTION_END);
    if (result_) {
      marker_.done(PROPERTY_DEFINITION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

}
