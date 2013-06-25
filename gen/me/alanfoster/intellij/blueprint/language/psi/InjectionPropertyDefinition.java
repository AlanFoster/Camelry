// This is a generated file. Not intended for manual editing.
package me.alanfoster.intellij.blueprint.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import me.alanfoster.intellij.blueprint.dom.Property;

public interface InjectionPropertyDefinition extends BlueprintInjectionPropertyDefinition {

  String getName();

  PsiElement getNameIdentifier();

  PsiElement setName(String newName);

  String getPropertyName();

  PsiElement getPropertyElement();

  @Nullable
  Property getReferencedProperty();

}
