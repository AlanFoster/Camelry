// This is a generated file. Not intended for manual editing.
package me.alanfoster.intellij.camel.simple.language.psi;

import java.util.List;
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
