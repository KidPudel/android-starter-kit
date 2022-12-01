```kotlin
fun main() {
    readlnOrNull()?.let { println("Elvis says: $it") } ?: throw IllegalStateException()
}
```
function is for **that** member only
