package me.alanfoster.camelry.codegen.model

import scala.collection.mutable

import com.sun.xml.bind.v2.model.core.{ElementPropertyInfo, AttributePropertyInfo}
import com.sun.xml.bind.v2.model.runtime.{RuntimeReferencePropertyInfo, RuntimeClassInfo, RuntimeValuePropertyInfo}
import java.lang.reflect.Type

// TODO Decide on a better name in the future once the full requirements are understood
case class BeanInfo (
                  simpleName: String,
                  xmlName: String,
                  baseClass: Option[RuntimeClassInfo],
                  attributes: mutable.Buffer[AttributePropertyInfo[Type, Class[_]]],
                  elements: mutable.Buffer[ElementPropertyInfo[Type, Class[_]]],
                  elementRefs: List[ElementReference],
                  value: RuntimeValuePropertyInfo
            ) {
}
