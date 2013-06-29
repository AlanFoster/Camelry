// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelus.camel.simple.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SimpleStringExpression extends PsiElement {

  @NotNull
  SimpleRightHandSide getRightHandSide();

  @NotNull
  SimpleSimpleExpression getSimpleExpression();

  @NotNull
  SimpleStringOperator getStringOperator();

}
