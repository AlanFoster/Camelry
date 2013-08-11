package me.alanfoster.camelry.codegen

import me.alanfoster.camelry.codegen.model._

import me.alanfoster.camelry.codegen.model.BeanInfo
import me.alanfoster.camelry.codegen.model.Metadata
import org.fusesource.scalate.TemplateEngine

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Scalate Generator
 */
object ScalateGenerator extends Generator {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  val generatorName = String.valueOf(getClass)

  val domElementTemplate = "DomElementTemplate.ssp"
  val enumTemplate = "enumTemplate.ssp"
  val engine = new TemplateEngine()
  engine.escapeMarkup = false

  // Forces validation on the file - I believe it also caches it for later use, so there shouldn't be any performance hit in doing this
  engine.load(domElementTemplate)
  engine.load(enumTemplate)

  def generateBeanFile(metadata: Metadata, beanInfo: BeanInfo): String = {
    val answer = engine.layout(domElementTemplate, Map(
      "generatorName" -> generatorName,
      "metadata" -> metadata,
      "beanInfo" -> beanInfo
    ))
    answer
  }

  def generateEnumFile(metadata: Metadata, enumInfo: EnumInfo): String = {
    val answer = engine.layout(enumTemplate, Map(
      "generatorName" -> generatorName,
      "metadata" -> metadata,
      "enumInfo" -> enumInfo
    ))
    answer
  }
}
