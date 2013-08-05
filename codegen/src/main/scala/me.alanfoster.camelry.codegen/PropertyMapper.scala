package me.alanfoster.camelry.codegen

import javax.xml.bind.{SchemaOutputResolver, JAXBContext}
import me.alanfoster.camelry.codegen.model.{GeneratorObject, MetaData, Other}
import scala.collection.mutable

import com.sun.xml.bind.v2.runtime.JAXBContextImpl
import com.sun.xml.bind.v2.model.runtime._
import scala.collection.JavaConverters._
import com.sun.xml.bind.v2.model.core.{NonElement, ElementPropertyInfo, AttributePropertyInfo}
import java.lang.reflect.Type

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.apache.camel.model.SetHeaderDefinition
import javax.xml.transform.Result
import javax.xml.transform.stream.StreamResult

/**
 * Performs data transformation into a grouped map.
 * Note, This method is in a separate object primarily to stop IntelliJ crashing
 */
object PropertyMapper {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def groupProperties(classInfo:RuntimeClassInfo) = {
      val properties: mutable.Buffer[_ <: RuntimePropertyInfo] = classInfo.getProperties.asScala

      // TODO Handle Enum target generation
      val propertyMap = properties.groupBy(value => value match {
        case value: AttributePropertyInfo[Type, _] => {
          value match {
            case enum: RuntimeEnumLeafInfo =>
              val baseType = enum.getBaseType
              logger.info("enum base type :: " + baseType)
            case _ =>
          }

          //logger.info("attribute :: " + value.getName)
          "attributes"
        }
        case value: ElementPropertyInfo[Type, _] => {
          logger.info("element :: " + value.getName)
          "elements"
        }
        case value:RuntimeValuePropertyInfo => {
          logger.info("values " + value.getName)
          "values"
        }
        case x => logger.info("Unsupported value : " + x)
      }).withDefaultValue(mutable.ArrayBuffer.empty[Any])
      propertyMap
    }
}
