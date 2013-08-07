package me.alanfoster.camelry.codegen

import me.alanfoster.camelry.codegen.model.Metadata
import org.slf4j.{LoggerFactory, Logger}
import java.io.{File, PrintWriter}

/**
 * The main class for external triggering of code generation.
 */
object Main {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]) {
    logger.info("Starting Code Generation")

    // TODO Investigate why AggregateDefinition doesn't create the optimisticLockRetryPolicyDefinition element
    val generatedFiles: List[(String, String)] = ScalateGenerator
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


    // Save files to temp place on the HDD for now
    val folderOutputLocation = "c:/genTest"

    val deleteFolder:File = new File(folderOutputLocation)
    if(!deleteFolder.exists) deleteFolder.mkdirs()

    generatedFiles
      .foreach({case (className, classContents) =>  {
        val newFile: File = new File(deleteFolder, s"${className}.java")
      val writer: PrintWriter = new PrintWriter(newFile)
      writer.write(classContents)
      writer.close()
      }})

    logger.info("Completed code generation with {} files", generatedFiles.size)
  }
}
