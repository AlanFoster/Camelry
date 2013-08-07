package me.alanfoster.camelry.codegen

import javax.xml.bind.JAXBContext
import me.alanfoster.camelry.codegen.model.{ElementReference, GeneratorObject, Metadata, BeanInfo}
import scala.collection.{immutable, mutable, JavaConversions}

import com.sun.xml.bind.v2.runtime.JAXBContextImpl
import com.sun.xml.bind.v2.model.runtime._
import scala.collection.JavaConverters._
import com.sun.xml.bind.v2.model.core.{ElementPropertyInfo, AttributePropertyInfo}
import java.lang.reflect.{ParameterizedType, Type}

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util


// TODO Consider the scenario of isAbstract() being true
// XmlRootElement
// XmlType (?)
// XmlAttribute
// XmlValue
// XmlTransient
// XmlID

/**
 * The trait Generator for creating DomElement interfaces from JAXB annotated classes.
 * The generatorName itself should perform any data transforms required before passing
 * to a concrete implementation of #generate
 */
trait Generator {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  /**
   *
   * @param jaxbPaths The packages containing the jaxb.index file
   *                  eg "foo.bar.baz" implies there exists a file "foo/bar/baz/jaxb.index"
   */
  def generateFiles(metadata: Metadata, jaxbPaths: String*): List[String] = {
    val delimitedPaths: String = jaxbPaths.mkString(":")

    val context: JAXBContext = JAXBContext.newInstance(delimitedPaths)

/*    val resolver: SchemaOutputResolver {def createOutput(namespaceUri: String, suggestedFileName: String): Result} = new SchemaOutputResolver {
      def createOutput(namespaceUri: String, suggestedFileName: String): Result = {
        val result: StreamResult = new StreamResult(System.out)
        result.setSystemId(suggestedFileName)
        result
      }
    }
    context.generateSchema(resolver)*/

    val set: RuntimeTypeInfoSet = context.asInstanceOf[JAXBContextImpl].getTypeInfoSet
    val beans: mutable.Map[Class[_], _ <: RuntimeClassInfo] = set.beans().asScala

    val files = beans
      //.filter({ case (key, value) => key.getSimpleName == "AggregateDefinition"})
      //.filter({ case (key, value) => key.getSimpleName == "PersonDatabase"})
      //.filter({ case (key, value) => key.getSimpleName == "CatchDefinition"})
      //.filter({ case (key, value) => value.isElement && "aggregate".equals(value.getElementName.getLocalPart) })
      .map({
      case (clazz, clazzInfo) => generateFile(metadata, clazz, clazzInfo)
    })
    files.toList
  }


  def inspect(info: AttributePropertyInfo[Type, _]) {
    val name: String = info.getTarget.getType.asInstanceOf[Class[_]].getSimpleName
  }


  def inspect(info: ElementPropertyInfo[Type, _]) {
    val name = info.ref().iterator().next
  }

  def inspect(elementRef: RuntimeReferencePropertyInfo) {
    val name = elementRef
    //logger.info(JavaConversions.asScalaSet(elementRef.getElements).map("\"" + _.getElementName.getLocalPart + "\"").grouped(5).map(_.mkString(", ")).mkString("\n\t\t\t\t"))

  /*  info.getIndividualType
    for(x <- info.getElements.asScala) {
      logger.info("xmlReference :: " + x.getElementName.getLocalPart)
    }*/
  }



  def generateFile(metadata: Metadata, clazz: Class[_], classInfo: RuntimeClassInfo): String = {
    logger.info("Generating file for {}", clazz)

    // Data transformation
    val properties: mutable.Buffer[_ <: RuntimePropertyInfo] = classInfo.getProperties.asScala

    properties
      .filter(p => p.isInstanceOf[RuntimeReferencePropertyInfo])
      .map(p => p.asInstanceOf[RuntimeReferencePropertyInfo])
      .foreach(v => inspect(v))

/*
    properties
      .filter(p => p.isInstanceOf[AttributePropertyInfo[Type, _]])
      .map(p => p.asInstanceOf[AttributePropertyInfo[Type, _]])
      .foreach(v => inspect(v))

    properties
      .filter(p => p.isInstanceOf[ElementPropertyInfo[Type, _]])
      .map(p => p.asInstanceOf[ElementPropertyInfo[Type, _]])
      .foreach(v => inspect(v))
*/


    val propertyMap = PropertyMapper.groupProperties(classInfo)

    if(propertyMap("values").size > 1) {
      throw new IllegalArgumentException("Values list should be zero or one :: " + propertyMap("values").mkString)
    }

    val generatedText = generate(
      new GeneratorObject(
        generatorName = String.valueOf(getClass),
        metadata = metadata,
        beanInfo = new BeanInfo(
          simpleName = clazz.getSimpleName,
          xmlName =
              if(classInfo.getElementName == null) "AbstractClass"
              else classInfo.getElementName.getLocalPart,
          baseClass = Option(classInfo.getBaseClass),
          elements = propertyMap("elements").asInstanceOf[mutable.Buffer[ElementPropertyInfo[Type, Class[_]]]],
          attributes = propertyMap("attributes").asInstanceOf[mutable.Buffer[AttributePropertyInfo[Type, Class[_]]]],
          elementRefs = getElementRefs(propertyMap("elementRefs").asInstanceOf[mutable.Buffer[RuntimeReferencePropertyInfo]]),
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


  def getElementRefs(elementRefs: mutable.Buffer[RuntimeReferencePropertyInfo]): List[ElementReference] = {
    elementRefs
      .map(elementRef => {
        new ElementReference(
          name = elementRef.getName.capitalize,
          references = JavaConversions.asScalaSet(elementRef.getElements).map(elementRef => elementRef.getElementName.getLocalPart).toSet,
          // Grab the IndividualType ourselves, and wrap later if required
          dataType = getDataType(elementRef.getRawType)
        )
      })
      .toList
  }

  def getDataType(parentType: Type): String = parentType match {
    case individualType: ParameterizedType => String.valueOf(individualType)
    case individualType: Class[_] => individualType.getSimpleName
    /*case x =>  logger.info("Unexpected type" + x)*/
  }

  def generate(definition: GeneratorObject): String
}


