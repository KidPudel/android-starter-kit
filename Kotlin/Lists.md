## MutalbeList

```kotlin
fun main() {
    val numbers = MutableList(100) { index ->
        if (index == 0 || index == 9 || index == 99) index + 1 else 0
    }

    // do not touch the lines below 
    println(numbers.joinToString())
}
```
