// This is a generated file. Not intended for manual editing.
package me.alanfoster.intellij.camel.simple.language.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static me.alanfoster.intellij.camel.simple.language.psi.SimpleTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.intellij.camel.simple.language.psi.*;

public class SimpleMemberAccessImpl extends ASTWrapperPsiElement implements SimpleMemberAccess {

  public SimpleMemberAccessImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public SimpleIdentifier getIdentifier() {
    return findChildByClass(SimpleIdentifier.class);
  }

  @Override
  @Nullable
  public SimpleMemberAccessKey getMemberAccessKey() {
    return findChildByClass(SimpleMemberAccessKey.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor)visitor).visitMemberAccess(this);
    else super.accept(visitor);
  }

}
