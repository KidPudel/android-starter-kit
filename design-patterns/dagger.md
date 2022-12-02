We'll use Dagger as the **dependency injection tool to manage dependencies.**

## Registration flow to use dagger

### application graph
In order to build the **application graph automatically** for us,  
Dagger needs to know **how to create instances for the classes in the graph.**

RegistrationViewModel.kt

```kotlin
// @Inject tells Dagger how to provide instances of this type
// Dagger also knows that UserManager is a dependency
class RegistrationViewModel @Inject constructor(val userManager: UserManager) {
      ...
}
```


In Kotlin, **to apply an annotation to the constructor**, you need to **_specifically_ add the keyword `constructor`** and **introduce the annotation just _before_ it as shown in the code snippet above**.

With the `@Inject` annotation, Dagger knows:

- How to create instances of type `RegistrationViewModel`.
- `RegistrationViewModel` has `UserManager` as dependency since the constructor takes an instance of UserManager as an argument.


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





error because...  
The way we tell Dagger how to provide `Storage` is different because `Storage` is an **interface** and as such cannot be instantiated directly. We need to tell Dagger what implementation of `Storage` we want to use. In this case it's `SharedPreferencesStorage`.  
To do this we will use a Dagger Module. A Dagger Module is a class that is annotated with `@Module`.  

Similar to Components, Dagger Modules tell Dagger how to provide instances of a **certain type**.   
Dependencies are defined using the `@Provides` and `@Binds` annotations.
