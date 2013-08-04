package me.alanfoster.camelry.codegen

import org.specs2.mutable._
import io.Source._
import io.BufferedSource
import io.Codec
import java.io.{FileWriter, BufferedWriter, File}


/**
 * ScalateCode generation tests
 */
class ScalateSpec extends Specification {

  "The Code generator " should {
    // Force sequential tests, as we need to read/write from the jaxb.index file for each scenario
    sequential

    "handle basic POJO generation" in {
      withJAXBIndex("Address")
      val ans = ScalateGenerator.generateFiles(author = "Alan", jaxbPaths = "foo.bar")
      ans === expectedFiles("Address")
    }

    "handle complex XmlElement types" in {
      withJAXBIndex("Person", "Address")
      val ans = ScalateGenerator.generateFiles(author = "Alan", jaxbPaths = "foo.bar")
      ans === expectedFiles("Person", "Address")
    }
  }

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
    .toList
  }
}
