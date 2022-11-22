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

because u now, it's better than this:

```kotlin
val string = "abc"
var number = 0 // try to avoid var if possible
try {
    number = string.toInt()
} catch (e: NumberFormatException) {
    number = -1
}
```

![smurf-dance-meme](https://user-images.githubusercontent.com/63263301/203299952-e8b1c08e-daae-4f8a-aee8-9eb16fb21f19.gif)
