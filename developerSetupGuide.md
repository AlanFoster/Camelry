Developer Setup
===============

Setting up your environment for IntelliJ plugin development is extremely easy;
These steps should guide you through the process of getting started with IntelliJ Plugin Development.
Be sure to fire any [questions or issues](../../issues) our way!

Downloading the sourcecode
-------------------------

One of the first steps is to get the code!

- [Fork and Clone the Camelus Git Repo](https://help.github.com/articles/fork-a-repo)

Creating your project
---------------------

Once you have all of the required sources downloaded, it's time to fire up IntelliJ.

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

![ConfigurationWindowSettings](/documentation/screenshots/ConfigurationWindowSettings.png "ConfigurationWindowSettings")

You will now be able to run the plugin with Run/Debug mode

![DebugMode](/documentation/setup/DebugMode.png "DebugMode")

Questions?
---------------------

[Raise an issue](../../issues) if you think any steps are unclear, missing, or if you simply have any questions :)