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

![image](https://user-images.githubusercontent.com/63263301/202207309-7e59e907-c8ef-44b3-84b0-a34930538fcc.png)



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

## Running with JAR

What is good about this way of running applications is that java -jar command can be run without Gradle, you only need to have a JAR beforehand.


The classic way to run a JVM-based application is to use the java -jar command. This command can be run without Gradle, you only need to have a JAR beforehand.
So let's build the JAR file for our application:
```
gradle jar

BUILD SUCCESSFUL in 748ms
2 actionable tasks: 2 executed
```
Now, the JAR file is in the app/build/libs directory.
If you want to clean the project folder from all generated artifacts, just run the gradle clean command.

However, if you now try to run our generated application using the classic approach, there will be a problem:
```
java -jar app/build/libs/app.jar
no main manifest attribute, in app/build/libs/app.jar
```
The thing is that the application does not contain the Main-Class attribute in the MANIFEST.MF  

To fix this we need to add the required attribute when generating an archive for the application. Just add the following declaration to the build.gradle(.kts) file:

```
jar {
    manifest {
        attributes("Main-Class": "org.hyperskill.gradleapp.App")   // for Groovy DSL
    }
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "org.hyperskill.gradleapp.AppKt")   // for Kotlin DSL
    }
}
```
**and then repeat ```gradle jar```**  

and just run ```java -jar app/build/libs/app.jar```

