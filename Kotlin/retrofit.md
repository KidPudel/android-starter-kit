# Retrofit

> Retrofit is the library to receive and upload data via REST-based web service

# How to enable it?

1. In Manifest allow Internet
   ```<user-permission android:name="android.permission.INTERNET"/>```
2. Add following dependencies:  
   ```kotlin
   implementation 'com.google.code.gson:gson:2.8.9'
   implementation 'com.squareup.retrofit2:retrofit:2.9.0'
   implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
   
   implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1'
   implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2-native-mt'
   implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.4.0'
   ```

# How to use it?

1. Define what routes we actually need
2. Create DTO and models to store data in objects
3. Implement defined routes with Retrofit
   - Builder of retrofit
   - Construct
   - Set base URL
   - Tell retrofit how to convert json data into data classes
     - add a factory to convert
     - specify to which format
   - Build instance
   - Create with specified routes
