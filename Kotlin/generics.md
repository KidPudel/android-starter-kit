# `out` keyword
Let’s say that we want to create a producer class that will be producing a result of some type `T`.   
Sometimes, we want to assign that produced value to a reference that is of a supertype of the type `T`.

To achieve that using Kotlin, **we need to use the `out` keyword on the generic type.   
It means that we can assign this reference to any of its supertypes. The `out` value can only be produced by the given class but not consumed:**

![image](https://user-images.githubusercontent.com/63263301/227530896-4f824ae2-9542-4e2e-b38a-8ef6f9a28606.png)


```kotlin
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    // ...
}
```


# `in` keyword

Sometimes, we have an opposite situation meaning that we have a reference of type `T` and we want to be able to assign it to the subtype of `T`.

**We can use the `in` keyword on the generic type if we want to assign it to the reference of its subtype.   
The `in` keyword can be used only on the parameter type that is consumed, not produced:**

![image](https://user-images.githubusercontent.com/63263301/227530944-462e1b8d-5d05-4900-8a22-fb564528084d.png)

```kotlin
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}
  
fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, you can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}
```
