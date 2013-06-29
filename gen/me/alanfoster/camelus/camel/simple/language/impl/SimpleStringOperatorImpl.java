// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelus.camel.simple.language.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.camelus.camel.simple.language.psi.*;

public class SimpleStringOperatorImpl extends ASTWrapperPsiElement implements SimpleStringOperator {

  public SimpleStringOperatorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitStringOperator(this);
    else super.accept(visitor);
  }

}
