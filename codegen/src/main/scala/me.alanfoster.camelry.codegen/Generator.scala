package me.alanfoster.camelry.codegen

import javax.xml.bind.JAXBContext
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
 * Generator for creating DomElement interfaces from JAXb annotated classes.
 */
object Generator {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]) {

    logger.info("Starting Code Generation")

    //  Create the jaxB context
    val context:JAXBContext = JAXBContext.newInstance(
      "org.apache.camel.model:" +
      "org.apache.camel.core.xml"
    )

    val set: RuntimeTypeInfoSet = context.asInstanceOf[JAXBContextImpl].getTypeInfoSet
    set.getAnyTypeInfo
    val beans: mutable.Map[Class[_], _ <: RuntimeClassInfo] = set.beans().asScala

    beans
      .filter({ case (key, value) => value.isElement && "aggregate".equals(value.getElementName.getLocalPart) })
      .foreach({ case (key, value) => {generateFile(key, value)}})

    logger.info("Completed code generation")
  }

  def generateFile(clazz : Class[_], classInfo : RuntimeClassInfo) = {
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

    val generatedText = new ScalateGenerator().generate(
      new GeneratorObject(
        metadata = new MetaData(author = "Alan", generator = getClass),
        other = new Other (
           name = classInfo.getElementName.getLocalPart,
           elements = propertyMap("elements").asInstanceOf[mutable.Buffer[ElementPropertyInfo[Any, Any]]],
           attributes = propertyMap("attributes").asInstanceOf[mutable.Buffer[AttributePropertyInfo[Any, Any]]]
        )
      )
    )

    logger.info("Generated file :: \n" + generatedText.trim)
  }


  trait Generator {
    def generate(definition: GeneratorObject): String
  }

  class ScalateGenerator extends Generator {

    private val uri: String = "DomElementTemplate.ssp"

    def generate(definition: GeneratorObject): String = {
      val engine = new TemplateEngine()
      engine.escapeMarkup = false

      // Forces validation on the file - I believe it also caches it for later use, so there shouldn't be any performance hit in doing this
      engine.load(uri)

      val answer = engine.layout(uri, Map(
        "metadata" -> definition.metadata,
        "other" -> definition.other
      ))
      answer
    }
  }

  // TODO Decide on a better name in the future once the full requirements are understood
  case class Other(name: String, attributes: mutable.Buffer[AttributePropertyInfo[Any, Any]], elements:  mutable.Buffer[ElementPropertyInfo[Any, Any]]) {
  }

  case class GeneratorObject(metadata: MetaData, other : Other) {
  }

  case class MetaData(author: String, generator: Any) {
  }

}


