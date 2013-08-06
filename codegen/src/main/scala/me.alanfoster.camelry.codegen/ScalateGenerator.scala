package me.alanfoster.camelry.codegen

import me.alanfoster.camelry.codegen.model.GeneratorObject

import org.fusesource.scalate.TemplateEngine

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Scalate Generator
 */
object ScalateGenerator extends  Generator {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  val uri = "DomElementTemplate.ssp"
  val engine = new TemplateEngine()
  engine.escapeMarkup = false

  // Forces validation on the file - I believe it also caches it for later use, so there shouldn't be any performance hit in doing this
  engine.load(uri)

  def generate(definition: GeneratorObject): String = {
    val answer = engine.layout(uri, Map(
      "metadata" -> definition.metadata,
      "other" -> definition.other
    ))
    answer
  }
}
