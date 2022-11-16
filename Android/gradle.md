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
