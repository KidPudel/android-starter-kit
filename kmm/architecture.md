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
- to access platform specific API with [expect/actual](url)

![kotlin-multiplatform-hierarchical-structure](https://user-images.githubusercontent.com/63263301/228298821-66d7cb5f-10d6-48bb-a503-fb7a072a30de.svg)

## Share code on all platforms
common logic for all plaforms (ktor, sql delight, etc.) just write in `commonMain`

<img width="1161" alt="flat-structure" src="https://user-images.githubusercontent.com/63263301/228302658-7f0e6a67-a9d9-4206-a666-2f681f9d8a88.png">


## Share code among some plaforms

..

