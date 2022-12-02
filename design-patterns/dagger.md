We'll use Dagger as the **dependency injection tool to manage dependencies.**

## Registration flow to use dagger

In order to build the application graph automatically for us,  
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
