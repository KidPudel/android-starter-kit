We want to display a greeting a platform, meaning based on which platform we launch our app, the greeting will be specific.  
For example: `hello, Android 33!`, `hello, IOS 17!`  

Meaning that we have a _**common function greeting**_ `Greeting` with _platform specific name_, _**get the platfrom**_ `Platform`.

1. So we create a greeting function:  
```kotlin
class Greeting {
    fun greet() {
        return "Hello, specific platform"
    }
}
```
We have our function, but it just returns _common for all_ function that returns "Hello, specific platform", but how to actually return name for a _specific platform_?  

Meaning we need to access a platform-specific API's, and this is the perfect case for `expect`/`actual` !! 


2. We create an `expect` function (kind of strict interface-ish thing)
```kotlin
// to force platform soure-sets to implement the return type
interface Platform {
    val platformName: String
}

expect fun getPlatform(): Platform

```

3. The next step is to actually implement our "Interface"/`expect` with implementation/`actual`
```kotlin
package com.iggydev.airchat

class AndroidPlatform() : Platform {
    override val platformName: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
```

4. Call a platform function in shared
Now we can complete our `greet()` function :)
```kotlin
class Greeting {
    privateval platform: Platform = getPlatform()
    fun greet(): String {
        return "hello, ${platform.platformName}!"
    }
}
```

As a result we get platform specific greeting.
For android:  
<img width="434" alt="first-multiplatform-project-on-android-1" src="https://user-images.githubusercontent.com/63263301/228821164-64f658d4-10ec-4e7b-8e77-0c2214159fe6.png">  

And for IOS:  

<img width="434" alt="first-multiplatform-project-on-ios-1" src="https://user-images.githubusercontent.com/63263301/228821310-215d4cf6-911e-4cc3-b007-28f539e7432b.png">
