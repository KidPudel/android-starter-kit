# Basic examples

```kotlin
fun main() {
    val grade = getGrade(true) // grade here has a type of (Int) -> Int
    println(grade(9))
}

fun getGrade(isCheater: Boolean): (Int) -> Int {
    return when (isCheater) {
        false -> ::getRealGrade
        else -> ::getPenaltyGrade
    }
}

fun getRealGrade(grade: Int) = grade
fun getPenaltyGrade(grade: Int) = grade - 1
```
