package me.alanfoster.camelry.codegen.model

/**
 * Represents the base binded field within a bean class. This class has been created to simplify
 * and decouple ourselves from the implementation of the RuntimeReferencePropertyInfo class
 *
 * // TODO Decide on name/xmlname etc
 * @param name The raw name of the element
 * @param dataType The string format of the dataType. This string should take into consideration Collections if appropriate.
 *                 For instance this value may be one of "List<Person>" or "Person"
 * @param rawDataType The 'Raw' datatype, for non-collections this will be equal to 'dataType'.
 *                    In the scenario of collections, ie "List<Person>", this value will be "Person"
 */
case class Base(name: String, dataType: String, rawDataType: String = "TODO")  {
  override def equals(obj: scala.Any): Boolean = obj match {
    case obj: Base => name.equals(obj.name) && dataType.equals(obj.dataType)
    case _ => false
  }
}
