// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelus.camel.simple.language.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.camelus.camel.simple.language.psi.*;

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
