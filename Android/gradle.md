Gradle is a modern automation tool that helps build and manage projects written in JVM-based languages


![image](https://user-images.githubusercontent.com/63263301/202181977-e05e7933-69c4-4379-8a73-e0576955460e.png)



# Initializing a basic project managed by Gradle


1. to check if gradle is installed type ```gradle -v```
2. Create a new directory to store files of your project and go to it.
```
mkdir gradle-demo
cd gradle-demo
```   
3. ```gradle init```
```
.
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle
```

4. build project ```gradle build```

# Modify the build file

Let's make our build more interesting by adding some properties and one task to the build.gradle file using Groovy DSL.

```groovy
description = "A basic Gradle project"

task helloGradle {
    doLast {
        println 'Hello, Gradle!'
    }
```
or with kotlin
```kotlin
description = "A basic Gradle project"

tasks.register("helloGradle") {
  doLast {
      println("Hello, Gradle")
  }
}
```
run the task ```gradle (-q) helloGradle```


**Here is brief info about all the generated files:**

- The build.gradle file is a primary file that specifies the Gradle's project, including its tasks and external libraries. For now, this file doesn't contain anything useful, but in real projects it is often updated with new information.
- The files gradle-wrapper.jar, gradle-wrapper.properties, gradlew and gradlew.bat belong to Gradle Wrapper which allows you to run Gradle without its manual installation.
- The settings.gradle file specifies which projects to include in your build. This file is optional for a build that has only one project, but it is mandatory for a multi-project build.


The **repositories section** declares locations from which dependencies will be downloaded and added to the project  
The **dependencies section** is used to add external libraries to the project.


The auto-generated build.gradle(.kts) file has a section that configures the application plugin thanks to which the application runs with the gradle run command as mentioned above.

```kotlin

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.6.21"

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

application {
    // Defines the main class for the application
    mainClassName = "org.hyperskill.gradleapp.App"
}
```
The mainClassName property defines a class with the entry point of the application. It allows us to run the application invoking the gradle run command.
