// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelry.camel.simple.language.impl;

import java.util.List;

import me.alanfoster.camelry.camel.simple.language.psi.SimpleIdentifier;
import me.alanfoster.camelry.camel.simple.language.psi.SimpleMemberAccess;
import me.alanfoster.camelry.camel.simple.language.psi.SimpleSimpleExpressionBody;
import me.alanfoster.camelry.camel.simple.language.psi.SimpleVisitor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.camelry.camel.simple.language.psi.*;
public class SimpleSimpleExpressionBodyImpl extends ASTWrapperPsiElement implements SimpleSimpleExpressionBody {

  public SimpleSimpleExpressionBodyImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public SimpleIdentifier getIdentifier() {
    return findNotNullChildByClass(SimpleIdentifier.class);
  }

  @Override
  @NotNull
  public List<SimpleMemberAccess> getMemberAccessList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SimpleMemberAccess.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitSimpleExpressionBody(this);
    else super.accept(visitor);
  }

}
