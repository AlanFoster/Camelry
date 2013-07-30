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
