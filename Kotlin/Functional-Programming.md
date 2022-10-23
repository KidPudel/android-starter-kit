```kotlin
    fun main() {
    val grade = getGrade(true) // because getGrade returns Int soo we can equate it
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
