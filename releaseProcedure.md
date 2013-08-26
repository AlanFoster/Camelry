Release Procedure
-----------------

If you are preparing a release, ensure you have checked the following -

- Update any remaining change logs
    - These are currently documented both within the main readme and the plugin.xml files
- Ensure all tests pass within IntelliJ
- Use the generated JAR from Maven, and *not* IntelliJ's `prepare Module` operation
    - This is to stop compiling against the incorrect JDK version
- Upload to Jetbrain's plugin repository
- Tag the release
- Update version numbers, both within the plugin.xml and POM files
- Relax