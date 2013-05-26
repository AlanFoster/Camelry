Blueprint + Camel IntelliJ Plugin
=================================

This IntelliJ plugin is designed to improve the improve the development experience when working with
both Apache Blueprint and Apache Camel.

Note, there is *currently* no planned support for Spring-DM (as it is deprecated). However, the Camel XML DSL
will be re-usable, so it is defintely a possibility in the future.

Currently Supports
------------------

[Here](http://www.youtube.com/watch?v=ttiXWpA_UWQ) is a quick video showing the initial support added for Apache Blueprint + Camel

Here some some screenshots from the video :

![File Creation](/documentation/screenshots/CreateNew.png "Optional title")
![Intellisense](/documentation/screenshots/IntelliSense.png "Optional title")
![Language Injection](/documentation/screenshots/LanguageSupport.png "Optional title")

Note, the [Simple](http://camel.apache.org/simple.html) language support is currently very rudimentary and requires a lot of work :)

Future plans
------------

It would be nice to add the following to the plugin

- Add more support for components ie `file:name?fileName=newName" - This should be easier in Camel 2.12.X
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
- Improve support for Apache Blueprint Configuration Management, IE highlighting when properties don't exist, goto reference, renaming etc.
- Add support for referencing OSGI Service References, as well as the existing support for Blueprint Beans.
- Add validation errors when bean references/methods don't exist