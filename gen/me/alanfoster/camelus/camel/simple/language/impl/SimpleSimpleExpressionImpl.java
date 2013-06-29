// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelus.camel.simple.language.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.camelus.camel.simple.language.psi.*;

public class SimpleSimpleExpressionImpl extends ASTWrapperPsiElement implements SimpleSimpleExpression {

  public SimpleSimpleExpressionImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public SimpleSimpleExpressionBody getSimpleExpressionBody() {
    return findNotNullChildByClass(SimpleSimpleExpressionBody.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitSimpleExpression(this);
    else super.accept(visitor);
  }

}
