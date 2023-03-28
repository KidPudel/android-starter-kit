KMM project consists of following strucuture:
```
├── androidApp/     # Android specific code
│   ├── src/       
│   │   ├── main/   # Android specific source code
│   │   │   ├── AndroidManifest.xml
│   │   │   └── kotlin/
│   │   │       └── com/example/myapp/
│   │   │           └── MainActivity.kt
│   │   └── test/   # Android specific test code
│   └── build.gradle
├── iosApp/         # iOS specific code
│   ├── src/       
│   │   ├── main/   # iOS specific source code
│   │   │   ├── Info.plist
│   │   │   └── kotlin/
│   │   │       └── com/example/myapp/
│   │   │           └── AppDelegate.kt
│   │   └── test/   # iOS specific test code
│   └── build.gradle
├── shared/         # Shared code
│   ├── src/       
│   │   ├── commonMain/  # Shared Kotlin code
│   │   │   └── kotlin/
│   │   │       └── com/example/myapp/
│   │   │           ├── MyModel.kt
│   │   │           └── MyService.kt
│   │   ├── androidMain/  # Android specific Kotlin code
│   │   │   └── kotlin/
│   │   │       └── com/example/myapp/
│   │   │           └── MyServiceAndroid.kt
│   │   └── iosMain/      # iOS specific Kotlin code
│   │       └── kotlin/
│   │           └── com/example/myapp/
│   │               └── MyServiceiOS.kt
│   └── build.gradle.kts
├── androidTest/    # Android specific test code
│   ├── src/       
│   │   └── androidTest/
│   └── build.gradle
├── iosTest/        # iOS specific test code
│   ├── src/       
│   │   └── iosTest/
│   └── build.gradle
├── build.gradle.kts # Project level build file
├── settings.gradle.kts
└── gradle.properties
```
