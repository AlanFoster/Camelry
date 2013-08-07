package me.alanfoster.camelry.codegen.model

/**
 * Represents the DataModel for XmlReferences.
 *
 * @param references The list of string XmlElement name references
 */
class ElementReference(name: String, dataType: String, val references: Set[String], val isCollection: Boolean) extends Base(name, dataType) {

}