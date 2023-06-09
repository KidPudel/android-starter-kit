# overview

to implement QR scanner we need: 
1. two libraries for scanner logic 
   - Zxing library (to get and read QR code) Google library
   - CameraX the Android camera liubrary (to get an access to the camera annd to process the frames)
3. one libarary for jetpack compose side (to place a camera to the composable)


# implementation

### add libraries
```kotlin
// Activity Compose
implementation "androidx.activity:activity-compose:1.4.0"

// CameraX
implementation "androidx.camera:camera-camera2:1.0.2"
implementation "androidx.camera:camera-lifecycle:1.0.2"
implementation "androidx.camera:camera-view:1.0.0-alpha81"

// Zxing
implementation "com.google.zxing:core:3.3.3"
```
