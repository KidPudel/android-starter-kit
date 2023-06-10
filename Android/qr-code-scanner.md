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


# Full analyzer code

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

# Scanner

for that we need:
1. camera permission
   - + check permission
- + set permission
2. display camera

camera will be displayed in viewm for that use `AndroidView` with factory that returns `PreviewView`
in factory in order to set up scanner:
1. get preview and previewView (to display camera preview)
2. setup it
   - bind previewView to preview. Setting a surface provider, tells camera that usecase is ready to reaceive data 
   - set camera selector lens
   - set image analyzer, what to scan and how
   - get camera provider future `ProcessCameraProvider`, **_binds a camera's lifecycle to the Lifecycle Owner, lifecycle state will determine should camera open, start, stop or close. And when started, use case will receive camera's data_**
      - for heavyweight things like processing something with camera (this case), we should use `bindToLifecycle()`
   - and then return previewView from a factory that is for creation a view to be composed

```kotlin
@Composable
fun ScannerScreen(navigationController: NavController) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val cameraProviderFuture = remember {
        // singleton which binds camera's lifecycle to lifecycle owner, and the state of lifefycle lets open a camera and more
        ProcessCameraProvider.getInstance(context)
    }

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launchCameraRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { permissionGranted ->
            hasCameraPermission = permissionGranted
        }
    )

    LaunchedEffect(Unit) {
        launchCameraRequest.launch(android.Manifest.permission.CAMERA)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (hasCameraPermission) {
            AndroidView(factory = { viewContext ->
                val previewView = PreviewView(viewContext)
                val preview = Preview.Builder().build()

                // setting provider, tels camera that usecase (preview where to render) ready to receive a data (images), if removed, this process stops
                preview.setSurfaceProvider(previewView.surfaceProvider)

                // setup a selector for the camera's properties and requirements to select a camera
                val selector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()

                /*
                * setup image analyzer with our qr code analyzer
                * collect only latest if qr code analyzer slower
                * run it in a main thread
                * */
                val imageAnalyzer = ImageAnalysis.Builder()
                    .setTargetResolution(Size(previewView.width, previewView.height))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build().apply {
                        setAnalyzer(ContextCompat.getMainExecutor(viewContext), QrCodeAnalyzer {
                            TODO(reason = "do on qr code analyzed")
                        })
                    }

                try {
                    // the state of lifecycle determines should camera open, started, stopped and closed
                    // bind it
                    cameraProviderFuture.get().bindToLifecycle(lifecycleOwner, selector, preview)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                previewView
            })
        }
    }
}
```
