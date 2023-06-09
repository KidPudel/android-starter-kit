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
    val onQrCodeScanned: (String) -> Unit
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


# Full code

```kotlin
class QrCodeAnalyzer(
    val onQrCodeScanned: (String) -> Unit
): ImageAnalysis.Analyzer {

    private val acceptableFormats = listOf(
        ImageFormat.YUV_420_888,
        ImageFormat.YUV_422_888,
        ImageFormat.YUV_444_888,
    )

    override fun analyze(image: ImageProxy) {
        if (image.format in acceptableFormats) {
            val bytes = image.planes.first().buffer.toByteArray()

            // process the image
            val source = PlanarYUVLuminanceSource(
                bytes,
                image.width,
                image.height,
                0,
                0,
                image.width,
                image.height,
                false
            )
            // bitmap information about qr code
            val binaryBitmap = BinaryBitmap(HybridBinarizer(source))

            // wrap with try-catch block, because when scanning, if in the particular frame no qr code, that would throw an exception
            try {
                // MultiFormatReader is the class to read all kinds of forms of data (in this case qr code specified)
                val result = MultiFormatReader().apply {
                    setHints(mapOf(
                        DecodeHintType.POSSIBLE_FORMATS to arrayListOf(
                            // possible barcode formats
                            BarcodeFormat.QR_CODE
                        )
                    ))
                }.run {
                    decode(binaryBitmap)
                }
                // return the data inside of qr code
                onQrCodeScanned(result.text)

            } catch (e: Exception) {
                Log.v("no QR code", e.message ?: "nothing to show")
            } finally {
                image.close()
            }
        }
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        return ByteArray(remaining()).also {
            // as side-effect
            get(it)
        }
    }
}
```
