# Introduction
This plugin aims to automate the generation of [semantic version](http://semver.org/) numbers for your maven project.

# Usage

* Add the pluginto your pom.xml
``` xml
<plugin>
        <groupId>com.nimworks</groupId>
        <artifactId>buildversion-maven-plugin</artifactId>
        <version>1.0.0</version>
        <executions>
          <execution>
            <id>buildversions</id>
            <phase>validate</phase>
            <goals>
              <goal>generate</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
```
* Do something with the generate version number
 For example 
```
<groupId>com.nimworks.it</groupId>
  <artifactId>simple-it</artifactId>
  <version>${buildVersion}-SNAPSHOT</version>

  <description>A simple IT</description>
```
Builds are currently separated into 3 types
* Major
* Minor
* Patch

The generated version takes the form {Major}.{Minor}.{Pathc}

# Example
* Build PATCH
```mvn clean package ```
if this is the first run, it will generate app-1.0.0-SNAPSHOT.jar. Subsequent executions will only increment the patch version number (1.0.1, 1.0.2, etc). The example above is the same as running ```mvn clean package -Dbuild.type=PATCH```

* Build Minor Version
```mvn clean package -Dbuild.type=MINOR```
if this is the first run, it will generate app-1.1.0-SNAPSHOT.jar. Subsequent executions will only increment the patch version number (1.2.0, 1.3.0, etc). Notice that this mode will reset the patch versions to 0



