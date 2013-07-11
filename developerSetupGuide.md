Developer Setup
===============

Setting up your environment for IntelliJ plugin development is extremely easy;
These steps should guide you through the process of getting started with IntelliJ Plugin Development.
Be sure to fire any [questions or issues](../../issues) our way!

Prerequisites
-------------------------

###### 1. Download Intellij IDE

If you haven't already, make sure you have installed either the Ultimate or Community Edition of [IntelliJ](http://www.jetbrains.com/idea/).

###### 2. Download Intellij IDE sources

The [IntelliJ community edition sourcecode](https://github.com/JetBrains/intellij-community) is available on GitHub.
You will either need to git Clone or Download the sources

###### 3. Installing Additional Developer Plugins

Developing a plugin for IntelliJ is 100% out of the box.

However the following are additional plugins which will make development work easier;

- [Grammar-Kit](http://plugins.jetbrains.com/plugin?pluginId=6606) - Tooling for editing BNF grammars and parser generation
- [JFlex](http://plugins.jetbrains.com/plugin?pluginId=263) - Enables JFlex support for [lexical analysis](http://en.wikipedia.org/wiki/Lexical_analysis)
- [PsiViewer](http://plugins.jetbrains.com/plugin/?pluginId=227) - A very nice tool which allows view in a hierarchical manner the [PSI Elements](http://confluence.jetbrains.com/display/IDEADEV/IntelliJ+IDEA+Architectural+Overview#IntelliJIDEAArchitecturalOverview-PsiElements) within a document.

*Note* If installing Grammar-Kit, one extra step is required; You must specify the paths of `JFlex` and the `Skeleton file` to point to the IntelliJ community edition sources. The JFlex settings are available under project settings - *ctrl+alt+s*


![JFlex settings](/documentation/setup/JflexSettings.png "JFlex settings")


Importing the IntelliJ project
---------------------

###### Step 1: Import the Project

Select the Import Project option from the Quick Start Menu

![ImportProject](/documentation/setup/ImportProject.png "ImportProject")

###### Step 2: Select Import Directory

Point to the root directory of your Git clone.

![ImportRootDirectory](/documentation/setup/ImportRootDirectory.png "ImportRootDirectory")

###### Step 3: Import Project Type

Select Create project from existing soruces.
This will allow IntelliJ to make use of the existing project configuration file

![ExistingSources](/documentation/setup/ExistingSources.png "ExistingSources")

###### Step 4: Select Project SDK

Create a new Intellij IDEA SDK

![IntelliJSDK](/documentation/setup/IntelliJSDK.png "IntelliJSDK")

*Note* - You may find it useful to also download the IntelliJ community edition source code and update your SDK's
sourcepath

###### Step 5: Create a plugin configuration

Once your project has loaded you will need to add a new Plugin Configuratoin

![ConfigurationWindow](/documentation/setup/ConfigurationWindow.png "ConfigurationWindow")

Here is an example of the Plugin Configuration used. You may find it useful to set specific JVM args, such as increasing
Heap Size.

![ConfigurationWindowSettings](/documentation/setup/ConfigurationWindowSettings.png "ConfigurationWindowSettings")

You will now be able to run the plugin with Run/Debug mode

![DebugMode](/documentation/setup/DebugMode.png "DebugMode")

Questions?
---------------------

[Raise an issue](../../issues) if you think any steps are unclear, missing, or if you simply have any questions :)