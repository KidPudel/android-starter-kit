# Basic form

```kotlin
try {
    // code that may throw an exception
}
catch (e: Exception) {
    // exception handler
}
finally {
    // code is always executed
}
```


Perhaps you have a question: why do you need the `finally` block at all, if you can just write the code after `try-catch`?  
For a better understanding, take a look at the following example:

```kotlin
fun main() {
    try {
        val a = 0/0 // throws ArithmeticException
    }
    finally {
        println("End of the try block") // will be executed
    }
    println("End of the program") // will not be printed
}
```
After the exception is thrown, the line that prints "End of the program", which is located after try-catch, will not run. By contrast, the finally block will always be executed.

> Note that the `finally` block is executed even if an exception occurs in the catch block.


# Idiom

Using `try-catch` blocks **as _expressions_ is an idiomatic way** of working with exceptions in Kotlin.

```kotlin
val string = "abc"
val number = try {
    string.toInt()
} catch (e: NumberFormatException) {
    -1
}
```
