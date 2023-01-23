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
