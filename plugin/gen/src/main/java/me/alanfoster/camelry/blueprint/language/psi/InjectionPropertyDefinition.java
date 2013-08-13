// This is a generated file. Not intended for manual editing.
package me.alanfoster.camelry.blueprint.language.psi;

import me.alanfoster.camelry.blueprint.language.psi.BlueprintInjectionPropertyDefinition;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import me.alanfoster.camelry.blueprint.dom.model.Property;

public interface InjectionPropertyDefinition extends BlueprintInjectionPropertyDefinition {

  String getName();

  PsiElement getNameIdentifier();

  PsiElement setName(String newName);

  String getPropertyName();

  PsiElement getPropertyElement();

  @Nullable
  Property getReferencedProperty();

}
