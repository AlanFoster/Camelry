#!/bin/sh

#
# Install script to install all intellij libs into a local repo
# This script has been written to work with Cygwin
# Therefore, YMMV with other terminals :)
#

INTELLIJ_HOME=$1
INTELLIJ_VERSION=$2

# Constants
INTELLIJ_GROUP_ID="com.intellij"

# Functions
function quitWith {
    echo "$@"
    exit 1
}

# Argument Validation - Both the IntelliJ home path and Maven version number must be supplied
[ ! -d "$INTELLIJ_HOME" ] &&
     quitWith "First argument intellij path '$INTELLIJ_HOME' did not exist. For example - 'C:/Program Files (x86)/JetBrains/IntelliJ IDEA 12.1.4'"

[ -z "$INTELLIJ_VERSION" ] &&
    quitWith "Second argument '$INTELLIJ_HOME' must be a valid maven version number. For example '129.173'"

echo "Running with intellij home set as '$INTELLIJ_HOME' and version '$INTELLIJ_VERSION'"

# Explicit path /usr/bin/find used to stop using Window's `find` function
/usr/bin/find "$INTELLIJ_HOME/lib" -type f -name '*.jar' | while read filePath
do
    fileName="${filePath%.*}"
    artifactId="${fileName##*/}"
    echo "Installing $artifactId to maven with the groupid '$INTELLIJ_GROUP_ID' artifact id '$artifactId'"
    mvn install:install-file -Dfile="$filePath" -DgroupId=${INTELLIJ_GROUP_ID} -DartifactId=$artifactId -Dversion=${INTELLIJ_VERSION} -Dpackaging=jar
done
