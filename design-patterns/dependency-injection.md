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
![1-car-engine-di](https://user-images.githubusercontent.com/63263301/205339429-dd8990c5-bd62-4cc1-8bfc-b1f4a28b881d.png)

# Automated dependency injection

In the previous example, you created, provided, and managed the dependencies of the different classes yourself, without relying on a library. This is called **dependency injection by hand, or manual dependency injection**. In the Car example, there was only one dependency, **but more dependencies and classes can make manual injection of dependencies more tedious**. Manual dependency injection also presents several problems:
- For big apps, taking all the dependencies and connecting them correctly can require a large amount of **boilerplate code**. In a multi-layered architecture, in order to create an object for a top layer, you have to provide all the dependencies of the layers below it. As a concrete example, to build a real car you might need an engine, a transmission, a chassis, and other parts; and an engine in turn needs cylinders and spark plugs.
- **When you're not able to construct dependencies before passing them in — for example when using lazy initializations** or scoping objects to flows of your app — you need to write and maintain a custom container (or graph of dependencies) that manages the lifetimes of your dependencies in memory.

There are libraries that solve this problem by automating the process of creating and providing dependencies. They fit into two categories: 

- Reflection-based solutions that connect dependencies at runtime.
- Static solutions that generate the code to connect dependencies at compile time.

**Dagger** is a popular dependency injection library for Java, Kotlin, and Android that is maintained by Google.  
**Dagger facilitates using DI in your app by creating and managing the graph of dependencies for you**. **It provides fully static and compile-time dependencies** addressing many of the development and performance issues of reflection-based solutions such as **Guice**.

# Tools

[Dagger](https://github.com/KidPudel/android-starter-kit/edit/main/design-patterns/dagger.md)
