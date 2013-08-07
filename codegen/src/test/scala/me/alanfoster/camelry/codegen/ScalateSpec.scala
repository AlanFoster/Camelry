package me.alanfoster.camelry.codegen

import org.specs2.mutable._
import io.Source._
import java.io.{FileWriter, BufferedWriter, File}
import java.util.{Locale, Date}
import java.text.DateFormat
import me.alanfoster.camelry.codegen.model.Metadata
;

/**
 * ScalateCode generation tests
 */
class ScalateSpec extends Specification {

  "The Code generator " should {
    // Force sequential tests, as we need to read/write from the jaxb.index file for each scenario
    sequential

    "handle basic POJO generation" in {
      withJAXBIndex("Address")
      val ans = generate
      ans === expectedFiles("Address")
    }

     "handle complex XmlElement types" in {
        withJAXBIndex("Person")
        val ans = generate
        ans === expectedFiles("Person", "Address")
      }

        "handle inheritance" in {
          withJAXBIndex("Manager")
          val ans = generate
          ans === expectedFiles("Manager", "Address", "Person")
        }

    "handle simple xml references" in {
      withJAXBIndex("PersonDatabase", "Manager")
      val ans = generate
      ans === expectedFiles("PersonDatabase", "Person", "Address", "Manager")
    }
  }

  def generate =
    ScalateGenerator.generateFiles(
      metadata = new Metadata(author = "Alan", packageName = "foo.bar"),
      jaxbPaths = "foo.bar")

  def withJAXBIndex(simpleNames: String*) {
    val jaxbPath : String = getClass.getResource("/foo/bar/jaxb.index").getFile
    val file: File = new File(jaxbPath).getAbsoluteFile
    file.delete()
    file.createNewFile()
    val bufferedWriter = new BufferedWriter(new FileWriter(file))
    bufferedWriter.write(simpleNames.mkString("\n"))
    bufferedWriter.close()
  }

  def expectedFiles(simpleNames: String*): List[String] = {
    simpleNames
      .map(name => "/expected/" + name + ".txt")
      .map(path => fromInputStream(getClass.getResourceAsStream(path), "UTF-8").mkString)
      // Replace the string ${injectedDate} ourselves, rather than spawn another template engine call for testing
      .map(fileContent => fileContent.replace("${injectedDate}", DateFormat.getDateInstance(DateFormat.LONG, Locale.UK).format(new Date)))
    .toList
  }
}
