// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelry.camel.simple.language.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.camelry.camel.simple.language.psi.*;

public class SimpleRightHandSideImpl extends ASTWrapperPsiElement implements SimpleRightHandSide {

  public SimpleRightHandSideImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public SimpleLiteral getLiteral() {
    return findChildByClass(SimpleLiteral.class);
  }

  @Override
  @Nullable
  public SimpleSimpleExpression getSimpleExpression() {
    return findChildByClass(SimpleSimpleExpression.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitRightHandSide(this);
    else super.accept(visitor);
  }

}
