Blueprint + Camel IntelliJ Plugin
=================================

This IntelliJ plugin is designed to improve the improve the development experience when working with
both Apache Blueprint and Apache Camel.

Note, there is *currently* no planned support for Spring-DM (as it is deprecated). However, the Camel XML DSL
will be re-usable, so it is definitely a possibility in the future.

Currently Supported
------------------

[Here](http://www.youtube.com/watch?v=ttiXWpA_UWQ) is a quick video showing the initial support added for Apache Blueprint + Camel

Here are some of the currently supported features

#####File Creation
![File Creation](/documentation/screenshots/CreateNew.png "File Creation")
#####Bean Intellisense
![Intellisense](/documentation/screenshots/IntelliSense.png "Intellisense")
#####Blueprint Injection Language
![Blueprint Injection Language](/documentation/screenshots/BlueprintInjectionReferenceValidation.png "Blueprint Injection Language")

Currently the support for the Blueprint Injection Language includes

* Reference Contribution
* Inlined Property Values
* Rename Support
* Error Highlighting

![Intellisense](/documentation/screenshots/IntelliSense.png "Intellisense")
#####Karaf Features Support
![Language Injection](/documentation/screenshots/KarafFeatures.png "Karaf Features Support")
#####Reference Validation
![Reference Validation](/documentation/screenshots/BeanReferenceValidation.png "Bean Reference Validation")
#####Language Injection
![Language Injection](/documentation/screenshots/LanguageInjection.png "Language Injection")

The currently injected languages are :
     
* [XPath](http://camel.apache.org/xpath.html)
* [JavaScript](http://camel.apache.org/javascript.html)
* [Groovy](http://camel.apache.org/groovy.html)
* [Simple](http://camel.apache.org/simple.html)

Note, the [Simple](http://camel.apache.org/simple.html) language support is currently very rudimentary and requires a lot of work :)

Future plans
------------

All planned actions are available within the github issues section.