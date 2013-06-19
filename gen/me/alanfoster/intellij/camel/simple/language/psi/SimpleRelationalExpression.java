// This is a generated file. Not intended for manual editing.
package me.alanfoster.intellij.camel.simple.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SimpleRelationalExpression extends PsiElement {

  @NotNull
  SimpleRelationalOperator getRelationalOperator();

  @NotNull
  SimpleRightHandSide getRightHandSide();

  @NotNull
  SimpleSimpleExpression getSimpleExpression();

}
