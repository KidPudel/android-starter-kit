# Companion objects

It's like a `static` keyword in other languages

An object declaration inside a class can be marked with the companion keyword:  

```kotlin
enum class DaysOfTheWeek {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
    companion object {
        fun printWeek() {
            for (day in DaysOfTheWeek.values()) {
                println(day.name)
            }
        }
    }
}

fun main() {
    DaysOfTheWeek.printWeek()
}
```
Members of the companion object can be called simply by using the class name as the qualifier
