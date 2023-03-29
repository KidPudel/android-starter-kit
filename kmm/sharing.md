# Shared code
- share code between all platforms (like buisness logic that applies to all platforms)
- share code among some platforms included in your project, **_but not all_**. when you can reuse in similar plaforms
- to access platform specific API with [expect/actual](https://kotlinlang.org/docs/multiplatform-connect-to-apis.html)

![kotlin-multiplatform-hierarchical-structure](https://user-images.githubusercontent.com/63263301/228298821-66d7cb5f-10d6-48bb-a503-fb7a072a30de.svg)

## Share code on all platforms
common logic for all plaforms (ktor, sql delight, etc.) just write in `commonMain`

<img width="1161" alt="flat-structure" src="https://user-images.githubusercontent.com/63263301/228302658-7f0e6a67-a9d9-4206-a666-2f681f9d8a88.png">


## Share code among some similar plaforms
You often need a several native targets that could reuse common logic and third-party API.

[Share code among some platforms included](https://kotlinlang.org/docs/multiplatform-share-on-platforms.html#share-code-on-similar-platforms) in your project but not all. You can reuse much of the code in similar platforms using a hierarchical structure. You can use target shortcuts for common combinations of targets or create the hierarchical structure manually.


## Connect to platform-specific api
When developing multiplafrom application that needs to access platform-specific API that implement **required** functionality, there are `expect`/`actual` keywords.

Common source set defines `expect` declaration and platform source sets must provide `actual` declaration that corresponds to the `expected` declaration.  
This works for most kotlin declarations such as: functions, classes, interfaces, enumarations, properties, annotations.

![expect-actual](https://user-images.githubusercontent.com/63263301/228362659-32296733-8e2d-4523-80c4-9abd4c334523.png)

The compiler ensures that for every `expect` declaration in common modules there is `actual` declaration in all platform modules.  
The IDE provides tools that help you create the missing actual declarations.

--- 

### expect/actual explained
Imagine you have `Database` class in shared source set and you want to use it later, but database logic is platform specific, so you use `expect` and implement logic in platform specific source set by using `actual` keyword, so what is happening, when you use `Database` in shared source set you use one class, but when you run application `Database` will have platform specific logic

---


🟢
> Use `expected` and `actual` declarations **_only for Kotlin declarations that have platform-specific dependencies_**. Implementing as much functionality as possible in the shared module is better, even if doing so takes more time.
Don't overuse expected and actual declarations – in some cases, an interface may be a better choice because it is more flexible and easier to test.


## Rules for expected and actual declarations
The main rules regarding expected and actual declarations are:

- An expected declaration is marked with the `expect` keyword; the `actual` declaration is marked with the actual keyword.

- `expect` and actual declarations have the same name and are located in the same package (have the same fully qualified name).

- `expect` declarations never contain any implementation code and are abstract by default.

- In interfaces, functions in `expect` declarations cannot have bodies, but their `actual` counterparts can be non-abstract and have a body. It allows the inheritors not to implement a particular function.

To indicate that common inheritors don't need to implement a function, mark it as open. All its actual implementations will be required to have a body:

```kotlin
// Common
expect interface Mascot {
    open fun display(): String
}

class MascotImpl : Mascot {
    // it's ok not to implement `display()`: all `actual`s are guaranteed to have a default implementation
}

// Platform-specific
actual interface Mascot {
    actual fun display(): String {
        TODO()
    }
}
```

During each platform compilation, the compiler ensures that every declaration marked with the `expect` keyword in the common or intermediate source set has the corresponding declarations marked with the `actual` keyword in all platform source sets. The IDE provides tools that help you create the missing `actual` declarations.

If you have a platform-specific library that you want to use in shared code while providing your own implementation for another platform, you can provide a `typealias` to an existing class as the actual declaration:

```kotlin
expect class AtomicRef<V>(value: V) {
    fun get(): V
    fun set(value: V)
    fun getAndSet(value: V): V
    fun compareAndSet(expect: V, update: V): Boolean
}
```
```kotlin
actual typealias AtomicRef<V> = java.util.concurrent.atomic.AtomicReference<V>
```
