// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelry.camel.simple.language.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static me.alanfoster.camelry.camel.simple.language.psi.SimpleTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.camelry.camel.simple.language.psi.*;

public class SimpleMemberAccessKeyImpl extends ASTWrapperPsiElement implements SimpleMemberAccessKey {

  public SimpleMemberAccessKeyImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public SimpleIdentifier getIdentifier() {
    return findChildByClass(SimpleIdentifier.class);
  }

  @Override
  @Nullable
  public SimpleNumericLiteral getNumericLiteral() {
    return findChildByClass(SimpleNumericLiteral.class);
  }

  @Override
  @Nullable
  public SimpleStringLiteral getStringLiteral() {
    return findChildByClass(SimpleStringLiteral.class);
  }

  @Override
  @Nullable
  public PsiElement getNumber() {
    return findChildByType(NUMBER);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitMemberAccessKey(this);
    else super.accept(visitor);
  }

}
