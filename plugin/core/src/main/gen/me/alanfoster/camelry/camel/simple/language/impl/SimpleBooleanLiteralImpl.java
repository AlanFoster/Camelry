// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelry.camel.simple.language.impl;

import me.alanfoster.camelry.camel.simple.language.psi.SimpleBooleanLiteral;
import me.alanfoster.camelry.camel.simple.language.psi.SimpleVisitor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class SimpleBooleanLiteralImpl extends ASTWrapperPsiElement implements SimpleBooleanLiteral {

  public SimpleBooleanLiteralImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitBooleanLiteral(this);
    else super.accept(visitor);
  }

}
