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
      ans must containAllOf(expectedFiles("Address"))
    }

    "handle complex XmlElement types" in {
      withJAXBIndex("Person")
      val ans = generate
      ans must containAllOf(expectedFiles("Person", "Address"))
    }


    "handle inheritance" in {
      withJAXBIndex("Manager")
      val ans = generate
      ans must containAllOf(expectedFiles("Manager", "Address", "Person"))
    }

    "handle simple xml references" in {
      withJAXBIndex("PersonDatabase", "Manager")
      val ans = generate
      ans must containAllOf(expectedFiles("PersonDatabase", "Person", "Address", "Manager"))
    }
  }

  def generate =
    ScalateGenerator.generateFiles(
      metadata = new Metadata(author = "Alan", packageName = "foo.bar"),
      jaxbPaths = List("foo.bar"),
      classLoader = getClass.getClassLoader
    )

  def withJAXBIndex(simpleNames: String*) {
    val jaxbPath : String = getClass.getResource("/foo/bar/jaxb.index").getFile
    val file: File = new File(jaxbPath).getAbsoluteFile
    file.delete()
    file.createNewFile()
    val bufferedWriter = new BufferedWriter(new FileWriter(file))
    bufferedWriter.write(simpleNames.mkString("\n"))
    bufferedWriter.close()
  }

  def expectedFiles(simpleNames: String*): List[(String, String)] = {
    simpleNames
      .map(name => (name, s"/expected/${name}.txt"))
      .map({ case(name, path) => (name, fromInputStream(getClass.getResourceAsStream(path), "UTF-8").mkString) })
      // Replace the string ${injectedDate} ourselves, rather than spawn another template engine call for testing
      .map({ case(name, fileContent) => (name, fileContent.replace("${injectedDate}", DateFormat.getDateInstance(DateFormat.LONG, Locale.UK).format(new Date)))})
    .toList
  }
}
