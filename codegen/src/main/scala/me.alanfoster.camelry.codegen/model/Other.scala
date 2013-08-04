package me.alanfoster.camelry.codegen.model

import scala.collection.mutable

import com.sun.xml.bind.v2.model.core.{ElementPropertyInfo, AttributePropertyInfo}
import com.sun.xml.bind.v2.model.runtime.RuntimeValuePropertyInfo

// TODO Decide on a better name in the future once the full requirements are understood
case class Other(
                  name: String,
                  attributes: mutable.Buffer[AttributePropertyInfo[Any, Any]],
                  elements: mutable.Buffer[ElementPropertyInfo[Any, Any]],
                  value: RuntimeValuePropertyInfo
            ) {
}
