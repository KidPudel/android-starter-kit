Simple lambda:
```kotlin
val mul2: (Int, Int) -> Int = { a: Int, b: Int -> a * b }

or

val mul2: = { a: Int, b: Int -> a * b }
```

Simple lambda call is going to look like this:

```kotlin
mul2(2, 3)
```

Complex lambda:

```kotlin
val lambda: (Long, Long) -> Long = {border1: Long, border2: Long ->
    var result: Long = 1 // we can declare inside and outside
    for (i in border1..border2) result *= i
    result // result is the last line
}
```

Qualified/Early return:
```kotlin
val textWithoutSmallDigits = "some text".filter {
    if (!it.isDigit()) return@filter true // qualified/early return
    it.toString().toInt() > 9
}
```

Capturing values:

```kotlin
fun placeArgument(value: Int, function: (Int, Int) -> Int): (Int) -> Int {
    return { function(value, it) }
}

fun sum(a: Int, b: Int): Int = a + b
val mul2 = {a: Int, b: Int -> a * b}

fun main() {
    val increase = placeArgument(1, ::sum) // because placeArgument (Int, (Int, Int) -> Int) -> Int
    val multiply = placeArgument(1, mul2)
    println(increase(5))
    println(multiply(5))
}
```
placeArgument (*Int*, *(Int, Int) -> Int*) -> *Int*
where first argument **Int** is placeArgument(**1**, ::sum) - 1
and second is **::sum** respectively
and return is a Int, but increase: (Int) -> Int, and its going to have int when we call it increase(5) -> Int
5 here is second parameter of function argument -> placeArgument(1, (1, 5)) -> 6

Other examples:
```kotlin
val reversePredicate: (Char) -> Boolean = {!originalPredicate(it)} // 'it' is a char
```
