KMM project consists of following strucuture:
```
├── androidApp/ 
│   ├── src/       
│   │   ├── main/  
│   │   │   ├── AndroidManifest.xml
│   │   │   └── kotlin/
│   │   │       └── com/example/myapp/
│   │   │           └── MainActivity.kt
│   │   └── test/  
│   └── build.gradle
├── iosApp/      
│   ├── src/       
│   │   ├── main/  
│   │   │   ├── Info.plist
│   │   │   └── kotlin/
│   │   │       └── com/example/myapp/
│   │   │           └── AppDelegate.kt
│   │   └── test/  
│   └── build.gradle
├── shared/  
│   ├── src/       
│   │   ├── commonMain/
│   │   │   └── kotlin/
│   │   │       └── com/example/myapp/
│   │   │           ├── MyModel.kt
│   │   │           └── MyService.kt
│   │   ├── androidMain/ 
│   │   │   └── kotlin/
│   │   │       └── com/example/myapp/
│   │   │           └── MyServiceAndroid.kt
│   │   └── iosMain/    
│   │       └── kotlin/
│   │           └── com/example/myapp/
│   │               └── MyServiceiOS.kt
│   └── build.gradle.kts
├── androidTest/  
│   ├── src/       
│   │   └── androidTest/
│   └── build.gradle
├── iosTest/     
│   ├── src/       
│   │   └── iosTest/
│   └── build.gradle
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

# Understanindg KMM structure

Main parts of your multiplatform project:
- Multiplatform plugin
- Targets
- Source sets
- Compilations

## Multiplatform plugin

When you creating kmm project, Project Wizard automatically applies `kotlin-multiplatform` Gradle plugin in the file build.gradle(.kts).
```kotlin
plugins {
    kotlin("multiplatform") version "1.8.10"
}
```
The `kotlin-multiplatform` plugin configures the project for creating an application or library to work on multiple platforms and prepares it for building on these platforms.

In the file build.gradle(.kts), it creates the kotlin extension at the top level, which includes configuration for **_targets_**, **_source sets_**, and dependencies.

## Targets
_Targets are representations of different platforms_. 
Target is a part of a Build that is repsonsable for building, testing and packaging the application for specific platform.  

When you create multiplatform app targets are specified in `kotlin` block in `build.gradle`

```kotlin
kotlin {
    jvm()
    js {
        browser {}
    }
 }
```

## Source sets
Source sets are collections of Kotlin files with their resources, dependencies that are stored in `src` directory. Source set can be used in Kotlin compilation for one or more Kotlin.  

<img width="233" alt="source-sets" src="https://user-images.githubusercontent.com/63263301/228498784-170e792f-7641-41ca-96a5-7de9040d7df2.png">  


Source sets are added to the sourceSets block of the top-level kotlin block.
```kotlin
kotlin {
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}
```

**Source sets form a hierarchy, which is used for sharing the common code.** 

In a source set that is shared among other targets, you can use platform specific languages features and dependencies that are avialable for **all these targets**.

For example, all Kotlin/Native features are available in the desktopMain source set, which targets the Linux (linuxX64), Windows (mingwX64), and macOS (macosX64) platforms.
![hierarchical-structure](https://user-images.githubusercontent.com/63263301/228504317-28dd4bb9-e7d8-4d6d-b518-0f81e2e4c160.png)


## Compilations

