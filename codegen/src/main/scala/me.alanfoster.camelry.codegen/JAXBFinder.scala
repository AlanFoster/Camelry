package me.alanfoster.camelry.codegen

import java.io.File
import java.util.zip.ZipFile
import java.util.Enumeration

/**
 * A quick test for finding jaxb camel resources on the classpath.
 * Currently this is not used, and the paths are instead hardcoded for camel 2.10.
 */
class JAXBFinder {

  class RichEnumeration[T](enumeration: Enumeration[T]) {
    def asScala = {
      var list = List[T]()
      while(enumeration.hasMoreElements) {
        list ::= enumeration.nextElement
      }
      list
    }
  }

  implicit def javaEnumerationToRichScala[T](enumeration:Enumeration[T]):RichEnumeration[T] = new RichEnumeration(enumeration)

  def getResourcesFromJar(file: File, filterFunc: String => Boolean) = {
    new ZipFile(file)
      .entries()
      .asScala
      .map(_.getName)
      .filter(filterFunc)
  }

  def getResources(filterFunc: String => Boolean) = {
    System.getProperty("java.class.path", ".")
      .split(File.pathSeparator)
      .filter(_.contains("camel"))
      .map(value => new File(value))
      .filter(!_.isDirectory)
      .map(file => ("classpath", getResourcesFromJar(file, filterFunc)))
  }

  /* getClass.getClassLoader
     .getResources("org/apache/camel/core/xml/jaxb.index")
     .asScala
     .foreach(url => {
     println(url.getPath)
   })*/

  def getResources() {
    val resources =
      getResources(_.endsWith("jaxb.index"))

    println(resources.mkString("\n"))
  }

}
