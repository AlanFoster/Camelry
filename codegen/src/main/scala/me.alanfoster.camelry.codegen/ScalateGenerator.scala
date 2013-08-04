package me.alanfoster.camelry.codegen

import javax.xml.bind.JAXBContext
import me.alanfoster.camelry.codegen.model.GeneratorObject
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

/**
 * Scalate Generator
 */
object ScalateGenerator extends  Generator {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def generate(definition: GeneratorObject): String = {
    val uri = "DomElementTemplate.ssp"

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
