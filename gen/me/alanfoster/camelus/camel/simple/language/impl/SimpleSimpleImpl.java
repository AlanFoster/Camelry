// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelus.camel.simple.language.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.camelus.camel.simple.language.psi.*;

public class SimpleSimpleImpl extends ASTWrapperPsiElement implements SimpleSimple {

  public SimpleSimpleImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public SimpleEqualityExpression getEqualityExpression() {
    return findChildByClass(SimpleEqualityExpression.class);
  }

  @Override
  @Nullable
  public SimpleRelationalExpression getRelationalExpression() {
    return findChildByClass(SimpleRelationalExpression.class);
  }

  @Override
  @Nullable
  public SimpleSimpleExpression getSimpleExpression() {
    return findChildByClass(SimpleSimpleExpression.class);
  }

  @Override
  @Nullable
  public SimpleStringExpression getStringExpression() {
    return findChildByClass(SimpleStringExpression.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitSimple(this);
    else super.accept(visitor);
  }

}
