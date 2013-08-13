// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelry.camel.simple.language.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.camelry.camel.simple.language.psi.*;
public class SimpleRelationalExpressionImpl extends ASTWrapperPsiElement implements SimpleRelationalExpression {

  public SimpleRelationalExpressionImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public SimpleRelationalOperator getRelationalOperator() {
    return findNotNullChildByClass(SimpleRelationalOperator.class);
  }

  @Override
  @NotNull
  public SimpleRightHandSide getRightHandSide() {
    return findNotNullChildByClass(SimpleRightHandSide.class);
  }

  @Override
  @NotNull
  public SimpleSimpleExpression getSimpleExpression() {
    return findNotNullChildByClass(SimpleSimpleExpression.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitRelationalExpression(this);
    else super.accept(visitor);
  }

}
