We'll use Dagger (Dagger-Hilt) as the **[dependency injection](https://github.com/KidPudel/android-starter-kit/blob/main/design-patterns/dependency-injection.md) tool to manage dependencies.**

## Why to use it?  

- Helps easily inject our dependencies. (using annotations e.g. `@Iject`) to define which dependencies should go where
- Have central place where we manage our dependencies ("Hey, this is how you can create _API interface_")
- Control lifetime of specific dependencies (singleton, scoped)

## How to use it?

`RegistrationViewModel.kt`
```kotlin
// @Inject tells Dagger what depependencies ViewModel has, inject it
// And that it should look in modules how to create such a dependency 
@HiltViewModel
class RegistrationViewModel @Inject constructor(val userManager: UserManager) {
      ...
}
```


In Kotlin, **to apply an annotation to the constructor**, you need to **_specifically_ add the keyword `constructor`** and **introduce the annotation just _before_ it as shown in the code snippet above**.

With the `@Inject` annotation: 

- Dagger knows that `RegistrationViewModel` has `UserManager` as dependency since the constructor takes an instance of `UserManager` as an argument.
- `@Inject` tells "please inject all dependencies that we have in our `constructor` and look in modules `@Modules`.  


- How to create instances of type `RegistrationViewModel`.


Dagger doesn't know how to create types of UserManager **yet**.   
**Follow the same process**, and **add the `@Inject` annotation to UserManager's constructor.**

Open the `UserManager.kt` file and replace the class definition with this one:  


```kotlin
class UserManager @Inject constructor(private val storage: Storage) {
    ...
}
```

**Now, Dagger knows how to provide instances** of `RegistrationViewModel` and `UserManager`  

Since UserManager's dependency (i.e. Storage) is an interface, we need to tell Dagger how to create an instance of that in a **different way,** we'll cover that later.

# Views require objects from the graph

For **Activities** specifically, any initialization code **needs to go to the `onCreate` method**. **Because of that, we cannot use the `@Inject` annotation in the constructor of a View class as we did before** (that is what is called constructor injection). Instead, **we have to use field injection**.

Instead of creating the dependencies an Activity requires in the onCreate method as we do with manual dependency injection, we want **Dagger to populate those dependencies for us.**  
For field injection (that is commonly used in Activities and Fragments), we annotate with `@Inject` the fields **that we want Dagger to provide**.  

In our app, `RegistrationActivity` has a dependency on `RegistrationViewModel`.  

If you open `RegistrationActivity.kt`, we're creating the ViewModel in the onCreate method just before calling the supportFragmentManager. We don't want to create it by hand, we want Dagger to provide it. For that, we need to:  
1. Annotate the field with `@Inject`.
2. Remove its instantiation from the onCreate method.

```kotlin
class RegistrationActivity : AppCompatActivity() {

    // @Inject annotated fields will be provided by Dagger
    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        // Remove following line
        registrationViewModel = RegistrationViewModel((application as MyApplication).userManager)
    }
}
```
- When `@Inject` is annotated **on a class constructor**, it's telling Dagger **how to provide instances of that class.**   
- When it's annotated **on a class field**, it's telling Dagger **that it needs to populate the field with an instance of that type.**


How can we tell Dagger **which** objects need to be injected into `RegistrationActivity`? We **need to create the Dagger graph (or [application graph](#application-graph))** and use it to inject objects into the Activity.

# Graph

### application graph
In order to build the **application graph automatically** for us,  
Dagger needs to know **how to create instances for the classes in the graph.**  

We want Dagger to **create the graph of dependencies** of our project, **manage them for us** and be able to **get dependencies from the graph**.  

#### To make Dagger do it
We need to create an **interface** and annotate it with `@Component`.  
**Dagger will create a Container** as we would have done with manual dependency injection.  

An interface annotated with `@Component` will make **Dagger generate code with all the dependencies required to satisfy the parameters of the methods it exposes**. `fun getCar(): Car`, and generated file will be called class will be called `DaggerAppComponent`
> Inside that interface, we can tell Dagger **that `RegistrationActivity` requests injection**.

1. **Create a new package called `di`** under `com.example.android.dagger` (same level as other packages such as registration).
2. Inside that package, create a new Kotlin file called `AppComponent.kt` and **define the interface** as we described above:  
   ```kotlin
   package com.example.android.dagger.di
   import com.example.android.dagger.registration.RegistrationActivity
   import dagger.Component
   
   // Definition of a Dagger component
   @Component
   interface AppComponent {
       // Classes that can be injected by this Component
       fun inject(activity: RegistrationActivity)
       fun getCar(): Car
   }
   ```
With the `inject(activity: RegistrationActivity)` method in the `@Component` interface, we're telling Dagger that `RegistrationActivity` **requests injection** and that **it has to provide the dependencies which are annotated with `@Inject`** (i.e. `RegistrationViewModel` as we defined in the previous step).  

and now we can use it in `MainActivity`
```kotlin
// graph
val appComponent = DaggerAppComponent.create()
val car = appComponent.getCar()
```

#### internal process
> Since Dagger has to create an instance of `RegistrationViewModel`, internally, it also needs to satisfy `RegistrationViewModel`'s dependencies (i.e. `UserManager`). If during this recursive process of finding dependencies Dagger doesn't know how to provide a particular dependency, **it will fail at compile time** _saying there's a dependency that it cannot satisfy_.

> A `@Component` **interface gives the information Dagger needs to generate the graph at compile-time**. The **parameter** _of the interface methods_ define **what classes request injection**.  


`@InstallIn(SingletonComponent::class)` <- decides about **lifetime** of our dependencies in module  
`@Singleton` - scope, **how many instances**, here we'll have obly a single instance, if we wouldn't have it, when we inject couple of times, we would create two instances, that would live as long as app does



error because...  
The way we tell Dagger how to provide `Storage` is different because `Storage` is an **interface** and as such cannot be instantiated directly. We need to tell Dagger what implementation of `Storage` we want to use. In this case it's `SharedPreferencesStorage`.  
To do this we will use a Dagger Module. A Dagger Module is a class that is annotated with `@Module`.  

Similar to Components, Dagger Modules tell Dagger how to provide instances of a **certain type**.   
Dependencies are defined using the `@Provides` and `@Binds` annotations.


# What we need to use it
`build.gradle`
```kotlin
dependencies {
    //Dagger - Hilt
    implementation "com.google.dagger:hilt-android:2.40.5"
    kapt "com.google.dagger:hilt-android-compiler:2.40.5"
    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    kapt "androidx.hilt:hilt-compiler:1.0.0"
    implementation 'androidx.hilt:hilt-navigation-compose:1.0.0'

    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation "com.squareup.okhttp3:okhttp:5.0.0-alpha.3"
}

plugins {
      id 'com.android.application'
      id 'org.jetbrains.kotlin.android'
      id 'kotlin-kapt'
      id 'dagger.hilt.android.plugin'
}
```
`build.gradle project file`
```kotlin
dependencies {
      classpath "com.google.dagger:hilt-android-gradle-plugin:2.40.5"
}
```

![image](https://user-images.githubusercontent.com/63263301/205438733-69911a87-fa2f-4613-afef-0752d253e5fd.png)
![image](https://user-images.githubusercontent.com/63263301/205438748-92f6a1e4-88b7-4733-a13b-17c6331fc2b7.png)
![image](https://user-images.githubusercontent.com/63263301/205438758-342778b7-eb7b-4f56-82ab-00909b297723.png)

# References
## [dependency injection](https://github.com/KidPudel/android-starter-kit/blob/main/design-patterns/dependency-injection.md)
## [Clean architecture](https://github.com/KidPudel/android-starter-kit/blob/main/Architecture/dependency-injection.md)
