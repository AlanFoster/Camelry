// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelry.camel.simple.language.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.camelry.camel.simple.language.psi.*;

public class SimpleEqualityOperatorImpl extends ASTWrapperPsiElement implements SimpleEqualityOperator {

  public SimpleEqualityOperatorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitEqualityOperator(this);
    else super.accept(visitor);
  }

}
