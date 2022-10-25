## Default constructor

So, when you create an object, you invoke a constructor.

```kotlin
val size = Size()
```
this is actually a **constructor call**
 Every class needs to have a constructor, so if it isn't explicitly defined, the compiler automatically generates a **default constructor**
 
 ### kotlin vs java
 kotlin
 ```kotlin
 class Person constructor(val name: String, val age: Int? = null)
 ```
 java
 
 ```java
 class PersonJava {
    final String name;
    final Integer age;

    public PersonJava(String name) {
        this.name = name;
        this.age = null;
    }

    public PersonJava(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
 ```

## Primary constructor

One or none

We **can’t** put any code in the **primary constructor**.

However, we sometimes have to execute some initialization code. A good place for it is an **initializer block**

### Init / initializer block

```kotlin
class ByteTimer(var time: Int) {
    init {
        if (time < -128) time = -128
        else if (time > 127) time = 127
    }
}
```

## Secondary constructor

one or more

**Each secondary constructor has to delegate to the primary constructor**. We’ll do this by this keyword.

```kotlin
class Car(val id: String, val type: String) {
    constructor(id: String): this(id, "unknown")
}
```




**In Kotlin, a class can have a primary constructor and one or more additional secondary constructors.**
