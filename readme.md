Camelry - A Blueprint + Camel IntelliJ Plugin
=================================

This IntelliJ plugin is designed to improve the improve the development experience when working with
both Apache Blueprint and Apache Camel.

Note, there is *currently* no support for Spring-DM. This is due to both Spring-DM being deprecated,
and that the IntelliJ Spring plugin is currently closed sourced.

Currently Supported Features
----------------------------

Click [Here](/currentlySupported.md) to view all of the currently supported features;

Here's a sneak preview of some of the currently supported features

#####Camel Bean Intellisense
![Intellisense](/documentation/screenshots/IntelliSense.png "Intellisense")
#####Blueprint Injection Language
![Blueprint Injection Language](/documentation/screenshots/BlueprintLanguageIntellisense.png "Blueprint Injection Language")

Check out the [currently supported](/currentlySupported.md) page too

Getting it
-----------

Camelry 0.1 has now officially been released!
To download this plugin, simply use IntelliJ's plugin settings window and search for 'Camelry'.

![Install Camelry Window](/documentation/screenshots/InstallCamelryWindow.png "Install Camelry Window")

All official releases will be available from the [Camelry Jetbrains plugin repository page](http://plugins.jetbrains.com/plugin/7302?pr=idea_ce).

GitHub currently tracks all of our [planned milestones](../../issues/milestones)

Contributing
------------

- Hitting a bug? [Send an issue our way!](../../issues)
- Got a cool idea? Create a feature request [here](../../issues)
- Want to contribute some code?
    - Check out some of the [existing issues](../../issues), or create a new one for what you want to work on!
    - Create a fork, do your commits, send a pull request! Check out Github's great article on the [Fork & Pull model](https://help.github.com/articles/using-pull-requests)
    - Read the [Developer setup guide](/developerSetupGuide.md)!

Remember, all forms of contributions are most welcome!

Change Log
----------

<ul>
	<li>
		0.1.1
		<ul>
			<li>Update Target Compile Version to be JDK 1.6</li>
			<li>Improve reference finding support for `direct-vm` within URI attributes.</li>
		</ul>
	</li>
	<li>
		0.1.0
		<ul>
			<li>Initial Release</li>
		</ul>
	</li>
</ul>


Future plans
------------

All planned actions are available within the GitHub issues section [here](../../issues).

Be sure to send any additional feature requests or cool ideas our way :)
