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
