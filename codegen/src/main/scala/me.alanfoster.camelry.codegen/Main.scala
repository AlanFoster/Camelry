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

    val metadata: Metadata = new Metadata(author = "Alan", packageName = "me.alanfoster.camelry.camel.dom")
    val folderOutputLocation = "c:/genTest"

    // TODO Investigate why AggregateDefinition doesn't create the optimisticLockRetryPolicyDefinition element
    val generatedFiles: List[(String, String)] =
      generateFiles(metadata)
        .groupBy(_._1).map(_._2.head).toList
    saveFiles(folderOutputLocation, generatedFiles)

    logger.info("Completed code generation with {} files", generatedFiles.size)
  }

  def saveFiles(folderOutputLocation: String, generatedFiles: List[(String, String)]) {
    val deleteFolder:File = new File(folderOutputLocation)
    if(!deleteFolder.exists) deleteFolder.mkdirs()
    generatedFiles
      .foreach({case (className, classContents) =>  {
      val newFile: File = new File(deleteFolder, s"${className}.java")
      val writer: PrintWriter = new PrintWriter(newFile)
      writer.write(classContents)
      writer.close()
    }})
  }

  def generateFiles(metadata: Metadata): List[(String, String)] = {
    ScalateGenerator
      .generateFiles(
      metadata = metadata,
      // TODO Look into generating jaxb index paths
      jaxbPaths = List(
        "org.apache.camel.blueprint",
        "org.apache.camel.util.blueprint",
        "org.apache.camel.core.xml.util.jsse",
        "org.apache.camel.core.xml",
        "org.apache.camel.blueprint",

        "org.apache.camel",
        "org.apache.camel.model",
        "org.apache.camel.model.language",
        "org.apache.camel.model.config",
        "org.apache.camel.model.dataformat",
        "org.apache.camel.model.loadbalancer",
        //"org.apache.camel.model.util",

        "org.apache.camel.core.xml"
        //"org.apache.camel.core.xml.util.jsse",
      ),

      classLoader = getClass.getClassLoader
    )
  }

}

