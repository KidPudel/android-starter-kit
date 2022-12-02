# Dependency injection - what is it?

Dependency injection (DI) is a **technique** widely used in programming and well suited to Android development.  
By following the principles of DI, you lay the groundwork for a good app architecture.  

Dependency injection **is basically providing the objects that an object needs (its dependencies) instead of having it construct them itself.**   

Classes often require references to other classes.   
For example, a `Car` class might need a reference to an `Engine` class. These **required classes are called dependencies**, and in this example the `Car` class **is dependent** on having an instance of the `Engine` class to run.

There are three ways for a class to get an object it needs:

1. The class constructs the dependency it needs. In the example above, `Car` would create and initialize its own instance of Engine.
2. Grab it from somewhere else. Some Android APIs, such as Context getters and getSystemService(), work this way.
3. Have it supplied as a **parameter**. The **app can provide these dependencies when the class is constructed or pass them in to the functions that need each dependency.** In the example above, the `Car` constructor would receive Engine as a parameter.  

The **third** option is dependency injection!   
With this approach you take the dependencies of a class and **provide them rather than having the class instance obtain them itself**.


# What it for?  

It's a very useful technique for testing, since it allows dependencies to be mocked or stubbed out.  


Implementing dependency injection provides you with the following advantages: 
- Reusability of code.
- Ease of refactoring.
- Ease of testing.


# How to use it?  

Dependencies can be injected into objects by many means (such **as constructor injection or setter injection**).   
One can even **use specialized dependency injection frameworks** (e.g. Spring) to do that, but they certainly **aren't required**.   
You don't need those frameworks to have dependency injection.   
**Instantiating and passing objects (dependencies) explicitly is just as good an injection as injection by framework.**


## Esample without DI

```kotlin
class Car {

    private val engine = Engine()

    fun start() {
        engine.start()
    }
}

fun main(args: Array) {
    val car = Car()
    car.start()
}
```

the `Car` class is **constructing its own `Engine`**.  
This can be problematic because:  
- `Car` and `Engine` are **tightly coupled** - an instance of `Car` uses one type of `Engine`, **and no subclasses or alternative implementations can easily be used**. If the `Car` were to construct its own `Engine`, **you would have to create two types** of Car instead of just reusing the same Car for engines of type Gas and Electric.
- The hard dependency on `Engine` **makes testing more difficult**. `Car` uses a **real** instance of `Engine`, thus preventing you from using a **test double** (FakeEngine) to modify Engine for different test cases.
![1-car-engine-no-di](https://user-images.githubusercontent.com/63263301/205334447-085495bf-d9f7-480c-8bd1-fa8c77b8d4e5.png)


## Example with DI

### Constructor Injection

This is the way described below. You pass the dependencies of a class to its constructor.

**Instead** of each instance of `Car` **constructing** its own `Engine` object on initialization, **it receives an `Engine` object as a parameter in its constructor**:  

```kotlin
class Car(private val engine: Engine) {
    fun start() {
        engine.start()
    }
}

fun main(args: Array) {
    val engine = Engine()
    val car = Car(engine)
    car.start()
}
```
The main function **uses** `Car`.  
Because `Car` **depends** on `Engine`, the **app creates an instance** of `Engine` and then **uses it to construct an instance of `Car`**. The benefits of this DI-based approach are:
- Reusability of `Car`. You can use one Car class with different `Engines`, like `ElectricEngine` or `GasEngine`
- Easy testing of `Car`.  You can pass in **test doubles** to test your different scenarios. For example, you might create a test double of `Engine` called `FakeEngine` and configure it for different tests.

### Field Injection (or Setter Injection)
Certain **Android framework classes** such as **activities and fragments are instantiated by the system, so constructor injection is not possible.** With **field injection**, dependencies are **instantiated after the class is created**. The code would look like this:

```kotlin
class Car {
    lateinit var engine: Engine

    fun start() {
        engine.start()
    }
}

fun main(args: Array) {
    val car = Car()
    car.engine = Engine()
    car.start()
}
```

# Tools

[Dagger](https://github.com/KidPudel/android-starter-kit/edit/main/design-patterns/dagger.md)
