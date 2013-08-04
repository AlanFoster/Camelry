package me.alanfoster.camelry.codegen

import org.specs2.mutable._
import scala.io.Source._
import scala.io.BufferedSource
;

/**
 * ScalateCode generation tests
 */
class ScalateSpec extends Specification {

  "The Code generator " should {
    "handle attribute generation" in {
      val ans = ScalateGenerator.generateFiles(author = "Alan", jaxbPaths = "foo.bar")
      ans === expectedFiles("SimpleAttributesAndProperties")
    }
  }

  def expectedFiles(simpleNames: String*): List[String] = {
    simpleNames
      .map(name => "/expected/" + name + ".txt")
      .map(path => fromInputStream(getClass.getResourceAsStream(path), "utf-8").mkString)
    .toList
  }
}
