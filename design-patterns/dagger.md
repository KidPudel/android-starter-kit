We'll use Dagger (Dagger-Hilt) as the **[dependency injection](https://github.com/KidPudel/android-starter-kit/blob/main/design-patterns/dependency-injection.md) tool to manage dependencies.**

## Why to use it?  

- Helps easily inject our dependencies. (using annotations e.g. `@Iject`) to define which dependencies should go where
- Have central place where we manage our dependencies ("Hey, this is how you can create _API interface_")
- Control lifetime of specific dependencies (singleton, scoped)

## How to use it?

We **inject** our dependencies, in other words: 
1. 🔸tell that some class has dependency of another class.
2. 🔹tell dagger to look up in module how to create it, application graph.  

_but before, we need to include some dependencies in gradle -> [include](#what-we-need-to-use-it)_

--------------------------------------------------------------

🔸
`RegistrationViewModel.kt`
```kotlin
// @Inject tells Dagger what depependencies ViewModel has, inject it
// And that it should look in modules how to create such a dependency 
@HiltViewModel // simplify the process of viewmodel DI
class MyViewModel @Inject constructor(val repository: IMyRepository): ViewModel() {
      ...
}
```


In Kotlin, **to apply an annotation to the constructor**, you need to **_specifically_ add the keyword `constructor`** and **introduce the annotation just _before_ it as shown in the code snippet above**.

With the `@Inject` annotation: 

- Dagger knows that `MyViewModel` has `IMyRepository` as dependency since the constructor takes an instance of `IMyRepository` as an argument.
- `@Inject` tells "please inject all dependencies that we have in our `constructor` and look in modules `@Modules`.  

----------------------------------------------------------------------
🔹
With the `@Module` annotation:
- Dagger knows how to create instances of type `MyViewModel`.

```kotlin
@Module
@IntallIn(SingletonComponent::class) // decides lifetime of dependencies provided in this module
object AppModule {
      @Provides
      @Singleton
      fun getMyApi(): MyApi {
            return Retrofit.Builder()
                  .BaseUrl("https://example_url.com")
                  .build()
                  .create(MyApi::class.java)
      }

      // now dagger will know how to create MyRepository type of class
      @Provides // annotate that this function provides dependency
      @Singleton
      fun getRepository(myApi: MyApi): IMyRepository {
            return MyRepository(myApi) // in example we need an instance of MyApi
      }
}
```

`@InstallIn(SingletonComponent::class)` <- decides about **lifetime** of our dependencies provided in this module.  
`@Singleton` - scope, **how many instances**, here we'll have obly a single instance, if we wouldn't have it, when we inject couple of times, we would create two instances, that would live as long as app does.  

If there is some other class that needs to be injected in, **follow the same process**, and **add the `@Inject` annotation to constructor.**

------------------------------------------------------------

# Views require objects from the graph

```kotlin
@AndroidEntryPoint
class MainActivity: AppCompatActicity() {
      override fun onCreate(savedInstanceState: Bandle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            
            // it will scope viewmodel to the current novigation praph (here we don't have this graph, scope to the Activity)
            // create an instance of MyViewModel
            // it will inject repository, and api
            val myViewModel = hiltViewModel<MyViewModel>()
      }
}
```
Scope the viewmodel to the current graph

we injected dependency, in activity (`AndroidComponent` class -> `Activity`, `Fragment`, `Service`), we need to add annotation `@AndroidEntryPoint`


--------------------------------------

## Binding abstractions

Improvement for injecting repository (because interface), it's better to abtract


So get rid of this:

`AppModule`
```kotlin
@Provides // annotate that this function provides dependency
@Singleton
fun getRepository(myApi: MyApi): IMyRepository {
      return MyRepository(myApi) // in example we need an instance of MyApi
}
```

Create a `RepositoryModule`, make it abstract class

```kotlin
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
      @Binds // bind this dependency, if want to provide interface or abstract class
      @Singleton
      abstract fun bindMyRepository(
            myRepository: MyRepository
      ): IMyRepository
}
```


------------------------

> You can use constructor injection and field injection

How can we tell Dagger **which** objects need to be injected into `RegistrationActivity`? We **need to create the Dagger graph (or [application graph](#application-graph))** and use it to inject objects into the Activity.

# Graph

### application graph
In order to build the **application graph automatically** for us,  
Dagger needs to know **how to create instances for the classes in the graph.**  


We want Dagger to **create the graph of dependencies** of our project, **manage them for us** and be able to **get dependencies from the graph**.  


#### internal process
> Since Dagger has to create an instance of `RegistrationViewModel`, internally, it also needs to satisfy `RegistrationViewModel`'s dependencies (i.e. `UserManager`). If during this recursive process of finding dependencies Dagger doesn't know how to provide a particular dependency, **it will fail at compile time** _saying there's a dependency that it cannot satisfy_.

> A `@Component` **interface gives the information Dagger needs to generate the graph at compile-time**. The **parameter** _of the interface methods_ define **what classes request injection**.  

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


# References
## [dependency injection](https://github.com/KidPudel/android-starter-kit/blob/main/design-patterns/dependency-injection.md)
## [Clean architecture](https://github.com/KidPudel/android-starter-kit/blob/main/Architecture/dependency-injection.md)
## [Dagger-hilt guide](https://www.youtube.com/watch?v=bbMsuI2p1DQ&t=573s&ab_channel=PhilippLackner)

# From google: 

![image](https://user-images.githubusercontent.com/63263301/205438733-69911a87-fa2f-4613-afef-0752d253e5fd.png)
![image](https://user-images.githubusercontent.com/63263301/205438748-92f6a1e4-88b7-4733-a13b-17c6331fc2b7.png)
![image](https://user-images.githubusercontent.com/63263301/205438758-342778b7-eb7b-4f56-82ab-00909b297723.png)
