```kotlin
when 
```
is a keyword that helps reduce boilerplate code and increase readability

it's kind of like "switch"

## Simple example
```kotlin
fun main(){
    val (var1, op, var2) = readln().split(" ")

    val a = var1.toInt()
    val b = var2.toInt()

    when (op) {
        "+" -> println(a + b)
        "-" -> println(a - b)
        "*" -> println(a * b)
        else -> println("Unknown operator")
    }
}
```

We can use multiple cases
```kotlin
when (op) {
    "+", "plus" -> println(a + b)
    "-", "minus", -> println(a - b) // trailing comma
    "*", "times" -> println(a * b)
    else -> println("Unknown operator")
}
```

We can use complex blocks, **but try to the most readable one.*

```kotlin
when (op) {
    "+", "plus" -> {
        val sum = a + b
        println(sum)
    }
    "-", "minus" -> {
        val diff = a - b
        println(diff)
    }
    "*", "times" -> {
        val product = a * b
        println(product)
    }
    else -> println("Unknown operator")
}
```


We can use "when" just like "if", as an expression
```kotlin
val result = when (op) {
    "+" -> a + b
    "-" -> a - b
    "*" -> a * b
    else -> "Unknown operator"
}
println(result)
```

 When provides more complex checking, not only directly matching a value.
 ```kotlin
 println(when (c) {
        a + b -> "$c equals $a plus $b"
        a - b -> "$c equals $a minus $b"
        a * b -> "$c equals $a times $b"
        else -> "We do not know how to calculate $c"
    })
 ```
 
 Another interesting possibility is to check whether a value belongs to a range. Take a look:
 ```kotlin
 when (n) {
    0 -> println("n is zero")
    in 1..10 -> println("n is between 1 and 10 (inclusive)")
    in 25..30 -> println("n is between 25 and 30 (inclusive)")
    else -> println("n is outside a range")
}
 ```
 
 You can use a "when" expression without arguments. In this case, every branch condition is a simple boolean expression:
 ```kotlin
 fun main(){
    val n = readln().toInt()
    
    when {
        n == 0 -> println("n is zero")
        n in 100..200 -> println("n is between 100 and 200")
        n > 300 -> println("n is greater than 300")
        n < 0 -> println("n is negative")
        // else-branch is optional here
    }
}
 ```
