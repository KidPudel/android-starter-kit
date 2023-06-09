# overview

to implement QR scanner we need: 
1. two libraries for scanner logic 
   - Zxing library (to get and read QR code) Google library
   - CameraX the Android camera liubrary (to get an access to the camera annd to process the frames)
3. one libarary for jetpack compose side (to place a camera to the composable)


# setup

add libraries
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

# implementation

now with the poroject setup, we need:
1. open a camera
2. analyse the qr code
3. callback (do on find)


## analyse the qr code

ImageAnalysis provides CPU accessible images for an app to perform image analysis on
```kotlin
class QrCodeAnalyzer(
    // handler callback
    onQrCodeScanned: (String) -> Unit
): ImageAnalysis.Analyzer {
    // call each frame for an image from the camera, image - info about specific frame
    override fun analyze(image: ImageProxy) {
        TODO("handle frame processing to get a QR code")
    }
}
```

now we need to setup formats our analyser will be processing further, or byte formats to support
```kotlin
private val acceptableFormats = listOf(
   ImageFormat.YUV_420_888,
   ImageFormat.YUV_422_888,
   ImageFormat.YUV_444_888,
)

override fun analyze(image: ImageProxy) {
   if (image.format in acceptableFormats) {
      //...
   }
}
```

