package me.alanfoster.camelry.codegen

import javax.xml.bind.JAXBContext
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

// TODO Consider the scenario of isAbstract() being true
// XmlRootElement
// XmlType (?)
// XmlAttribute
// XmlValue
// XmlTransient
// XmlID

/**
 * The trait Generator for creating DomElement interfaces from JAXB annotated classes.
 * The generator itself should perform any data transforms required before passing
 * to a concrete implementation of #generate
 */
trait Generator {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  /**
   *
   * @param jaxbPaths The packages containing the jaxb.index file
   *                  eg "foo.bar.baz" implies there exists a file "foo/bar/baz/jaxb.index"
   */
  def generateFiles(author: String, jaxbPaths: String*): List[String] = {
    val delimitedPaths: String = jaxbPaths.mkString(":")

    val context: JAXBContext = JAXBContext.newInstance(delimitedPaths)

    val set: RuntimeTypeInfoSet = context.asInstanceOf[JAXBContextImpl].getTypeInfoSet
    val beans: mutable.Map[Class[_], _ <: RuntimeClassInfo] = set.beans().asScala

    val files = beans
      //.filter({ case (key, value) => value.isElement && "aggregate".equals(value.getElementName.getLocalPart) })
      .map({
      case (clazz, clazzInfo) => generateFile(author, clazz, clazzInfo)
    })
    files.toList
  }


  def inspect(info: AttributePropertyInfo[Type, _]) {
    val name: String = info.getTarget.getType.asInstanceOf[Class[_]].getSimpleName
  }

  def generateFile(author: String, clazz: Class[_], classInfo: RuntimeClassInfo): String = {
    // Data transformation
    val properties: mutable.Buffer[_ <: RuntimePropertyInfo] = classInfo.getProperties.asScala

    properties
      .filter(p => p.isInstanceOf[AttributePropertyInfo[Type, _]])
      .map(p => p.asInstanceOf[AttributePropertyInfo[Type, _]])
      .foreach(v => inspect(v))

    val propertyMap = groupProperties(classInfo)

    if(propertyMap("values").size > 1) {
      throw new IllegalArgumentException("Values list should be zero or one :: " + propertyMap("values").mkString)
    }

    val generatedText = generate(
      new GeneratorObject(
        metadata = new MetaData(author, generator = getClass),
        other = new Other(
          simpleName = clazz.getSimpleName,
          xmlName = classInfo.getElementName.getLocalPart,
          baseClass = Option(classInfo.getBaseClass),
          elements = propertyMap("elements").asInstanceOf[mutable.Buffer[ElementPropertyInfo[Type, Class[_]]]],
          attributes = propertyMap("attributes").asInstanceOf[mutable.Buffer[AttributePropertyInfo[Type, Class[_]]]],
          value =
            if(propertyMap("values").size == 1) propertyMap("values").asInstanceOf[mutable.Buffer[RuntimeValuePropertyInfo]].head
            else null
        )
      )
    )

    val result = generatedText.trim
    logger.info("Generated file :: \n" + result)

    result
  }

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

        inspect(value)

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

  def generate(definition: GeneratorObject): String
}


