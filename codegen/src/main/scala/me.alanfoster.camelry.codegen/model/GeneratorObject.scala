package me.alanfoster.camelry.codegen.model

/**
 * The generic GeneratorObject storing the relevant information for file generation.
 *
 * @param generatorName Preferably the generatorName's fully qualified name
 * @param metadata The generatorName metadata information
 * @param beanInfo All bean information
 */
case class GeneratorObject(generatorName: String, metadata: Metadata, beanInfo : BeanInfo) {
}
