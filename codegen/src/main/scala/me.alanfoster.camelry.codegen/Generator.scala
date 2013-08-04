package me.alanfoster.camelry.codegen

import javax.xml.bind.JAXBContext
import me.alanfoster.camelry.codegen.model.{GeneratorObject, MetaData, Other}
import scala.collection.{mutable, JavaConversions}

import com.sun.xml.bind.v2.runtime.JAXBContextImpl
import com.sun.xml.bind.v2.model.runtime.{RuntimeEnumLeafInfo, RuntimePropertyInfo, RuntimeClassInfo, RuntimeTypeInfoSet}
import org.apache.camel.model.SetHeaderDefinition
import org.fusesource.scalate.TemplateEngine
import scala.collection.JavaConverters._
import com.sun.xml.bind.v2.model.core.{PropertyInfo, NonElement, ElementPropertyInfo, AttributePropertyInfo}
import java.lang.reflect.Type

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  def generateFiles(jaxbPaths: String*): List[String] = {
    val delimitedPaths: String = jaxbPaths.mkString(":")

    val context:JAXBContext = JAXBContext.newInstance(delimitedPaths)

    val set: RuntimeTypeInfoSet = context.asInstanceOf[JAXBContextImpl].getTypeInfoSet
    val beans: mutable.Map[Class[_], _ <: RuntimeClassInfo] = set.beans().asScala

    val files = beans
      .filter({ case (key, value) => value.isElement && "aggregate".equals(value.getElementName.getLocalPart) })
      .map({ case (key, value) => generateFile(key, value)})
    files.toList
  }

  def generateFile(clazz : Class[_], classInfo : RuntimeClassInfo): String = {
    // Data transformation
    val properties: mutable.Buffer[_ <: RuntimePropertyInfo] = classInfo.getProperties.asScala

    // TODO Handle Enum target generation
    val propertyMap = properties.groupBy(value => value match {
      case value: AttributePropertyInfo[_, _] => {
        value match {
          case enum: RuntimeEnumLeafInfo =>
            val baseType: NonElement[Type, Class[_]] = enum.getBaseType
            logger.info("enum base type :: " + baseType)
          case _ =>
        }
        logger.info("attribute :: " + value.getName)
        "attributes"
      }
      case value: ElementPropertyInfo[_, _] => {
        logger.info("element :: " + value.getName)
        "elements"
      }
      case x => logger.info("Unrecognised val : " + x)
    }).withDefaultValue(mutable.ArrayBuffer.empty[Any])

    val generatedText = generate(
      new GeneratorObject(
        metadata = new MetaData(author = "Alan", generator = getClass),
        other = new Other (
          name = classInfo.getElementName.getLocalPart,
          elements = propertyMap("elements").asInstanceOf[mutable.Buffer[ElementPropertyInfo[Any, Any]]],
          attributes = propertyMap("attributes").asInstanceOf[mutable.Buffer[AttributePropertyInfo[Any, Any]]]
        )
      )
    )

    val result = generatedText.trim
    logger.info("Generated file :: \n" + result)

    result
  }

  def generate(definition: GeneratorObject): String
}


