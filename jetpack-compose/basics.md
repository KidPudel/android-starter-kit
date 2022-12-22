# Why should I use Jetpack Compose? 🤔

If we look technically:

- Declarative UI is **_cleaner_**, **_readable_**, and **_performant_** than Imperative UI.
- Compose allows you to do **_more with less code_** compared to XML.
- Compose is **_Intuitive_**. This means that you **_just need to tell_** Compose what you want to show the user.
- Compose is **_compatible_** with all your existing code: you can call Compose code from Views and Views from Compose. Also integrated with many Jetpack Libraries.
- Compose **_improves your build time_** and APK size.


# What is it?

> Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android - Google

What once was **View** -> now is **Composable**

# How to start using it?

first add this to `build.gradle`
```kotlin
buildscript {
    ext {
        compose_ui_version = '1.1.1'
    }
}
```

```kotlin
buildFeatures {
  compose true
}
```

```kotlin
dependencies {
  implementation 'androidx.activity:activity-compose:1.3.1'
  implementation "androidx.compose.ui:ui:$compose_ui_version"
  implementation "androidx.compose.ui:ui-tooling-preview:$compose_ui_version"
  implementation 'androidx.compose.material:material:1.1.1'
  
  androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_ui_version"
  debugImplementation "androidx.compose.ui:ui-tooling:$compose_ui_version"
  debugImplementation "androidx.compose.ui:ui-test-manifest:$compose_ui_version"
}
```

# How to use it?

`SetContent` is the entry point for Composables
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}
```
now we intead of SetContentView(binding.root), we have SetContent {}

`Greeting("Android")` - this is composable  
it's a function annotated with `@Composable`
```kotlin
@Composable
fun Greeting(name: String) {
  // Text is also Composable
  Text("Hello, $name")
}
```

- arrangment - is the main axes
- alignment - is the cross axes

for a column we have vertical arrangment and horizontal alignment

# Useful Composable

- `Column` & `Row` & `Box`  
    ![image](https://user-images.githubusercontent.com/63263301/208519000-88281d26-c260-4544-8d33-d489561d4123.png)

- `Text`
- `TextFiled`
- `Card`
- `Image`
- `Scaffold`
- `Spacer` composable (add extra space)
- `Button`

# Useful modifiers

Modifier.:
- `.backgroud` draws a shape with a solid color behind the content
- `.fillMaxSize(->percentage<-), MaxHeight, MaxWidth` will make composable occupy all available space (**_If boundaries in parent are set (e.g. if only height is set, then width is going to be filled up for entire screen)_**)
- `.width, .height, .required_` just as with xml, but required is fill even furthter (can go out of screen)
-  `.padding` pushes content of container inside ([read more about padding](https://github.com/KidPudel/android-starter-kit/blob/main/XML/margin-vs-padding.md))
-  `.matchParentSize` - content will scale according to the borders of parent
-  `.size`
-  `.offest` like [margin](https://github.com/KidPudel/android-starter-kit/blob/main/XML/margin-vs-padding.md) (jetpack compose doesn't have margin, but you can use padding instead of it), but it doesn't push other elements (starts from top left corner), but you can use `Spacer` composable (add extra space)
-  `.border` gives composable a borders
-  `.clickable`
-  `.scrollable`
-  `.draggable`

# Helpful adivses

- Modifiers are applied sequentially:  
    ![image](https://user-images.githubusercontent.com/63263301/208513544-f3c03680-7c9a-4a2b-a526-2d01e8c0f130.png)  

- In order to align individual element in the box, you need to use another Box


# Helpful resourses

- [Compose layout basics](https://developer.android.com/jetpack/compose/layouts/basics)

# Examples
[Pin composable](https://github.com/KidPudel/android-starter-kit/blob/main/jetpack-compose/pin-composable.kt)
