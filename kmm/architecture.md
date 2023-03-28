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

# Shared code
- share code between all platforms (like buisness logic that applies to all platforms)
- share code among some platforms included in your project, **_but not all_**. when you can reuse in similar plaforms
- to access platform specific API with [expect/actual](https://kotlinlang.org/docs/multiplatform-connect-to-apis.html)

![kotlin-multiplatform-hierarchical-structure](https://user-images.githubusercontent.com/63263301/228298821-66d7cb5f-10d6-48bb-a503-fb7a072a30de.svg)

## Share code on all platforms
common logic for all plaforms (ktor, sql delight, etc.) just write in `commonMain`

<img width="1161" alt="flat-structure" src="https://user-images.githubusercontent.com/63263301/228302658-7f0e6a67-a9d9-4206-a666-2f681f9d8a88.png">


## Share code among some similar plaforms
You often need a several native targets that could reuse common logic and third-party API.

[Share code among some platforms included](https://kotlinlang.org/docs/multiplatform-share-on-platforms.html#share-code-on-similar-platforms) in your project but not all. You can reuse much of the code in similar platforms using a hierarchical structure. You can use target shortcuts for common combinations of targets or create the hierarchical structure manually.


## Connect to platform-specific api
When developing multiplafrom application that needs to access platform-specific API that implement **required** functionality, there are `expect`/`actual` keywords.

Common source set defines `expect` declaration and platform source sets must provide `actual` declaration that corresponds to the `expected` declaration.  
This works for most kotlin declarations such as: functions, classes, interfaces, enumarations, properties, annotations.

![expect-actual](https://user-images.githubusercontent.com/63263301/228362659-32296733-8e2d-4523-80c4-9abd4c334523.png)

The compiler ensures that for every `expect` declaration in common modules there is `actual` declaration in all platform modules.  
The IDE provides tools that help you create the missing actual declarations.


🟢
> Use `expected` and `actual` declarations **_only for Kotlin declarations that have platform-specific dependencies_**. Implementing as much functionality as possible in the shared module is better, even if doing so takes more time.
Don't overuse expected and actual declarations – in some cases, an interface may be a better choice because it is more flexible and easier to test.
