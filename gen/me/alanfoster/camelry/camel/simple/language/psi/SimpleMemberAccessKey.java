// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelry.camel.simple.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SimpleMemberAccessKey extends PsiElement {

  @Nullable
  SimpleIdentifier getIdentifier();

  @Nullable
  SimpleNumericLiteral getNumericLiteral();

  @Nullable
  SimpleStringLiteral getStringLiteral();

  @Nullable
  PsiElement getNumber();

}
