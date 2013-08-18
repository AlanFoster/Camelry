*Note - this documentation is currently under development*

Developer Setup
===============

Setting up your environment for IntelliJ plugin development is extremely easy;
These steps should guide you through the process of getting started with IntelliJ Plugin Development.
Be sure to fire any [questions or issues](../../issues) our way!

Prerequisites
-------------------------

####Download IntelliJ
###### 1. Download Intellij IDE

If you haven't already, make sure you have installed either the Ultimate or Community Edition of [IntelliJ](http://www.jetbrains.com/idea/).


###### 2. Download Intellij IDE sources

The [IntelliJ community edition sourcecode](https://github.com/JetBrains/intellij-community) is available on GitHub.
You will either need to git Clone or Download the sources


###### 3. Add Intellij SDk

Add a new IntelliJ project SDK. Be sure to Update the sourcepath to include your downloaded IntelliJ sources.

![IntellijSDKImportSources](/documentation/setup/IntellijSDKImportSources.png "IntellijSDKImportSources")


####Setup Maven


This plugin currently makes use of [Maven](http://maven.apache.org/) for dependency management. You should install this 

Jetbrains do not currently host publicly up-to-date dependencies for IntelliJ, therefore you must install these yourself.

###### 1. Install IntelliJ JARS to your Maven repo

You will need to install all of IntelliJ's lib JARs contained within `${IDEA_INSTALL}/lib/*.jar` into your Maven repo. A shell script is available for this purpose, which is available [here](/install.sh). 

An example of executing this Shell script is as follows


	# First argument is the path to your IntelliJ 12 install
	# second argument is the build number. This plugin requires 129.173
	./install.sh "C:/Program Files (x86)/JetBrains/IntelliJ IDEA 12.1.4" "129.173"

###### 2. Install ideauidesigner-maven-plugin

As plugin makes use of IntelliJ GUI forms, we require an additional compile step handle this.

Please follow the [ideaui-designer-maven-plugin](https://github.com/gshakhn/ideauidesigner-maven-plugin/tree/12.x) 12.x branch install instructions, which are available within the project's read-me file.

####Installing Developer Plugins

Add a new IntelliJ project SDK. Update the sourcepath to include your downloaded IntelliJ sources.


Developing a plugin for IntelliJ is 100% out of the box.

However the following are additional plugins which will make development work easier;

- [Intellij plugin development with Maven](http://plugins.jetbrains.com/plugin/7127?pr=idea) - Allows for a Maven module to be picked up as an IntelliJ plugin
- [Scala Support](http://plugins.jetbrains.com/plugin/?id=1347) - Only required if you are updating the CodeGen module
- [Grammar-Kit](http://plugins.jetbrains.com/plugin?pluginId=6606) - Tooling for editing BNF grammars and parser generation
- [JFlex](http://plugins.jetbrains.com/plugin?pluginId=263) - Enables JFlex support for [lexical analysis](http://en.wikipedia.org/wiki/Lexical_analysis)
- [PsiViewer](http://plugins.jetbrains.com/plugin/?pluginId=227) - A very nice tool which allows view in a hierarchical manner the [PSI Elements](http://confluence.jetbrains.com/display/IDEADEV/IntelliJ+IDEA+Architectural+Overview#IntelliJIDEAArchitecturalOverview-PsiElements) within a document.

*Note* If installing Grammar-Kit, one extra step is required; You must specify the paths of `JFlex` and the `Skeleton file` to point to the IntelliJ community edition sources. The JFlex settings are available under project settings - *ctrl+alt+s*

![JFlex settings](/documentation/setup/JflexSettings.png "JFlex settings")


Importing the IntelliJ project
---------------------

As this plugin makes use of Maven you should make sure you have installed the pre-requiste plugin 'Intellij plugin development with Maven', which will allow for IntelliJ to pickup your plugin module succesfully.

###### 1: Import the Project

Select the Import Project option from the Quick Start Menu

![ImportProject](/documentation/setup/ImportProject.png "ImportProject")

###### 2: Import the Maven Project

Select the pom.xml file

![ImportRootDirectory](/documentation/setup/ImportRootDirectory.png "ImportRootDirectory")

###### 4: Select Project SDK

After selecting through the options you will need to create new Intellij IDEA SDK

![IntelliJSDK](/documentation/setup/IntelliJSDK.png "IntelliJSDK")

*Note* - You may find it useful to also download the IntelliJ community edition source code and update your SDK's
sourcepath, as mentioned in the Pre-requisites section.

###### 5: Create a plugin configuration

Once your project has loaded you will need to add a new Plugin Configuration

![ConfigurationWindow](/documentation/setup/ConfigurationWindow.png "ConfigurationWindow")

Here is an example of the Plugin Configuration used. You may find it useful to set specific JVM args, such as increasing
Heap Size.

![ConfigurationWindowSettings](/documentation/setup/ConfigurationWindowSettings.png "ConfigurationWindowSettings")

You will now be able to run the plugin with Run/Debug mode

![DebugMode](/documentation/setup/DebugMode.png "DebugMode")

Extra resources
---------------------
IntelliJ also offers some great great plugin development resources

- [Plugin Development](http://confluence.jetbrains.com/display/IDEADEV/PluginDevelopment)
- [Custom Language Support](http://confluence.jetbrains.com/display/IntelliJIDEA/Custom+Language+Support)
- [200+ Open-source IntelliJ plugins](http://blogs.jetbrains.com/idea/2012/10/check-out-more-than-200-open-source-plugins/)
- [Plugin Development Forum](http://devnet.jetbrains.com/community/idea/open_api_and_plugin_development)

Questions?
---------------------

[Raise an issue](../../issues) if you think any steps are unclear, missing, or if you simply have any questions :)