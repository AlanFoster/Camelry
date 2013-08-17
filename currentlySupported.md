Currently Supported
===================

#####Screencasts

- Initial support added for Apache Blueprint + Camel http://www.youtube.com/watch?v=ttiXWpA_UWQ
- Blueprint Support Preview http://www.youtube.com/watch?v=QnJzkE5yVZg

#####File Creation
![File Creation](/documentation/screenshots/CreateNew.png "File Creation")
#####Camel Bean Intellisense
![Intellisense](/documentation/screenshots/IntelliSense.png "Intellisense")
#####Blueprint Injection Language
![Blueprint Injection Language](/documentation/screenshots/BlueprintLanguageIntellisense.png "Blueprint Injection Language")

Currently the support for the Blueprint Injection Language includes

* Reference Contribution
* Inlined Property Values
* Rename Support
* Error Highlighting

#####Karaf Features Support
![Language Injection](/documentation/screenshots/KarafFeatures.png "Karaf Features Support")
#####Reference Validation
![Reference Validation](/documentation/screenshots/BeanReferenceValidation.png "Bean Reference Validation")

##### Route refactoring

Camel Routes can be refactored by simply selecting the required elements and invoking `Extract Method` with either *ctrl+alt+m* or *Rightclick -> Extract -> Method*

##### Live Templates

Live templates can be invoked with `ctrl+j` under a Camel Context or a Camel Route

######Camel Context Templates
![Live Templates Camel Context](/documentation/screenshots/LiveTemplatesCamelContext.png "Live Templates Camel Context")
######Camel Route Templates
![Live Templates Camel Route](/documentation/screenshots/LiveTemplatesCamelRoute.png "Live Templates Camel Route")

You can also create your own Live Templates under the  Live Templates settings menu (`ctrl+alt+s`)

![Live Templates Settings](/documentation/screenshots/LiveTemplatesSettings.png "Live Templates Settings")

As part of this plugin, there are two available contexts that you can select from. The details of the contexts are

- Camel Context - The cursor is directly underneath the Camel Context element.
  You may wish to use this context if you want to add new templates for elements such as routes, dataformats, onException elements etc.
- Camel Route - The cursor is under any Camel Route.
  This context should be used if you wanted to add a new Live template for any DSL/EIP, such as an aggregator, pipeline, etc

Remember, we love contributions - so be sure to share any templates you create! :)

#####Language Injection
![Language Injection](/documentation/screenshots/LanguageInjection.png "Language Injection")

The currently injected languages are :
     
* [XPath](http://camel.apache.org/xpath.html)
* [JavaScript](http://camel.apache.org/javascript.html)
* [Groovy](http://camel.apache.org/groovy.html)
* [Simple](http://camel.apache.org/simple.html)

Note, the [Simple](http://camel.apache.org/simple.html) language support is currently very rudimentary and requires a lot of work :)
It is also now disabled by default to not cause issues during its early stages of life

This configuration value can now be configured within the Settings menu - available with the shortcut *ctrl+alt+s*

![Configuration Window](/documentation/screenshots/ConfigurationWindow.png "Configuration Window")
