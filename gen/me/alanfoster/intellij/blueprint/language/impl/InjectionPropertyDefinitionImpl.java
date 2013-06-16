// This is a generated file. Not intended for manual editing.
package me.alanfoster.intellij.blueprint.language.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static me.alanfoster.intellij.blueprint.language.InjectionTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.alanfoster.intellij.blueprint.language.psi.*;

public class InjectionPropertyDefinitionImpl extends ASTWrapperPsiElement implements InjectionPropertyDefinition {

  public InjectionPropertyDefinitionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof InjectionVisitor) ((InjectionVisitor)visitor).visitPropertyDefinition(this);
    else super.accept(visitor);
  }

  public String getName() {
    return InjectionPsiImplUtil.getName(this);
  }

  public PsiElement getNameIdentifier() {
    return InjectionPsiImplUtil.getNameIdentifier(this);
  }

  public PsiElement setName(String newName) {
    return InjectionPsiImplUtil.setName(this, newName);
  }

}
