// This is a generated file. Not intended for manual editing.
package me.alanfoster.intellij.camel.simple.language.psi;

import java.util.List;
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
