# Result from an Activity

## Overview

Sometimes we need to **get information from activity**, for example:
> App opens camera, camera takes a photo, and camera activity destroyed, and previous activity need to get that photo

This functionality is provided by **_Activity Result APIs_** introduced in `AndroidX` **Activity** and **Fragment**.
> The Activity Result APIs provide components for **registering** for a result, **launching** the result, and **handling** the result once it is dispatched by the system.

## Registering a callback for an Activity Result

When in a ComponentActivity or a Fragment, the Activity Result APIs provide:  
`registerForActivityResult()` API for **registering the result callback**. `registerForActivityResult()` takes an `ActivityResultContract` and an `ActivityResultCallback` and returns an `ActivityResultLauncher` which you’ll use **to launch the other activity**.

### Summary
- `registerForActivityResult()` API for **registering the result callback**.
- `ActivityResultContract` defines the **input** type needed to produce a result along with the **output** type of the result.
- `ActivityResultCallback` is a single method interface with an `onActivityResult()` method **that takes an object of the output type defined in the `ActivityResultContract`**
- `ActivityResultLauncher` which you’ll use **to launch the other activity**.


```kotlin
val getContent = registerForActivityResult(ActivityResultContracts.GetContent()"<String, Uri?>") { uri: Uri? ->
    // Handle the returned Uri
}

val newsResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()"<Intent, ActivityResult>") { result: ActivityResult
    when(result.statusCode) {
        // Handle the result
    }
}
```
If you have multiple activity result calls that either use different contracts or want separate callbacks, you can call `registerForActivityResult()` multiple times to register multiple `ActivityResultLauncher` instances. You must always call registerForActivityResult() in the same order for each creation of your fragment or activity to ensure that the inflight results are delivered to the correct callback.

## Launching an activity for result

`registerForActivityResult()` registers your callback, but it does **not** launch the _other activity and kick off the request for a result_. 

- _This is the **responsibility of the returned `ActivityResultLauncher` instance**._  
- _If input exists_, the **launcher takes the input that matches the type of the `ActivityResultContract`**. _(`String` (1st example), `Intent` (2nd example))_
- Calling `launch()` **starts** the process of **producing the result**.

When the user is done with the subsequent activity and returns:
- the `onActivityResult()` from the `ActivityResultCallback` is then **executed**, as shown in the following example:  

```kotlin
val getContent = registerForActivityResult(GetContent()) { uri: Uri? ->
    // Handle the returned Uri
}

override fun onCreate(savedInstanceState: Bundle?) {
    // ...

    val selectButton = findViewById<Button>(R.id.select_button)

    selectButton.setOnClickListener {
        // Pass in the mime type you'd like to allow the user to select
        // as the input
        getContent.launch("image/*")
    }
}
```
