# Example

* examples to support the documentation

## How has it been created?
* `mvn archetype:generate \
      -DarchetypeGroupId=org.apache.maven.archetypes \
      -DarchetypeArtifactId=maven-archetype-quickstart \
      -DarchetypeVersion=1.4`
  * ❌if you try to run -- via IntelliJ -- NOT "src/" generated ❌

## How to run locally?
* `mvn clean compile`
* `java -classpath target/classes/ com.App`