package me.alanfoster.camelry.codegen

import me.alanfoster.camelry.codegen.model.Metadata
import org.slf4j.{LoggerFactory, Logger}

/**
 * The main class for external triggering of code generation.
 */
object Main {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]) {
    logger.info("Starting Code Generation")

    // TODO Investigate why AggregateDefinition doesn't create the optimisticLockRetryPolicyDefinition element
    val generatedFiles: List[String] = ScalateGenerator
      .generateFiles(
        metadata = new Metadata(author = "Alan", packageName = "me.alanfoster.camelry.camel.dom"),
        // TODO Look into generating jaxb index paths
        jaxbPaths =
          "org.apache.camel",
          "org.apache.camel.model",
          "org.apache.camel.model.language",
          "org.apache.camel.model.config",
          "org.apache.camel.model.dataformat",
          "org.apache.camel.model.loadbalancer",
          //"org.apache.camel.model.util",

          "org.apache.camel.core.xml"
          //"org.apache.camel.core.xml.util.jsse"
    )

    logger.info("Completed code generation with {} files", generatedFiles.size)
  }
}
