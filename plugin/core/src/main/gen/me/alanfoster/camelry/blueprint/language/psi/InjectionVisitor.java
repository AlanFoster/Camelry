// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelry.blueprint.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class InjectionVisitor extends PsiElementVisitor {

  public void visitPropertyDefinition(@NotNull InjectionPropertyDefinition o) {
    visitBlueprintInjectionPropertyDefinition(o);
  }

  public void visitBlueprintInjectionPropertyDefinition(@NotNull BlueprintInjectionPropertyDefinition o) {
    visitElement(o);
  }

  public void visitElement(@NotNull InjectionElement o) {
    visitElement(o);
  }

}
