Blueprint + Camel IntelliJ Plugin
=================================

This IntelliJ plugin is designed to improve the improve the development experience when working with
both Apache Blueprint and Apache Camel.

Note, there is *no* planned support for Spring-DM (as it is deprecated). However, the Camel DSL should still
work in most cases.

Currently Supports
------------------

- Gutter icon for navigating to bean declarations when using the camel xml dsl `<bean ref="id" method="bar" />`
- Notification if bean reference does not exist
- Ctrl+Click navigation to bean declaration from camel xml dsl
- Ctrl+click navigation to class methods with the dsl `<bean ref="id" method="bar" />`
- Language injection support, currently supported :
     * [XPath](http://camel.apache.org/xpath.html)
     * [JavaScript](http://camel.apache.org/javascript.html)
     * [Groovy](http://camel.apache.org/groovy.html)
     * [Simple](http://camel.apache.org/simple.html)
  
   Note, the [Simple](http://camel.apache.org/simple.html) language support is currently very rudimentary and requires a lot of work :)

Future plans
------------

It would be nice to work on the following

- Add pictures of examples
- Add more support for components ie `file:name?fileName=newName" 
    * Ctrl+click 'file' -> Component Declaration
    * Ctrl+space support to show available component names
    * Marking invalid when the component can't be found 
    * ctrl+space support for query string properties and red highlighting if they are invalid
    * Specific support for components :
        * file component could open the folder it is polling
        * direct/vm/seda could open the routes they link and perform validation etc    
- Be able to use ctrl+q to bring up the Apache Camel/Blueprint documentation
- Auto-generate common components, such as Content Based Router
- Improve [Camel Simple](http://camel.apache.org/simple.html) notation support
- Allow language injection for [Bean Binding](http://camel.apache.org/bean-binding.html) annotations
- Add the same level of support to the Java DSL
- Add bean handling support for `<method bean="id" method="method" />`