package me.alanfoster.camelry.codegen.model

/**
 * Represents the DataModel for XmlReferences. This class has been created to simplify
 * and decouple ourselves from the implementation of the RuntimeReferencePropertyInfo class
 *
 * // TODO Decide on name/xmlname etc
 * @param name The raw name of the element
 * @param dataType The string format of the dataType. This string should take into consideration Collections if appropriate.
 *                 For instance this value may be one of "List<Person>" or "Person"
 * @param references The list of string XmlElement name references
 */
class ElementReference(val name: String, val dataType: String, val references: Set[String]) {

}