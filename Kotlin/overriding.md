```kotlin
open class Transport(val cost: Int) {
    open fun getFullInfo(): String {
        return "$$cost cost"
    }

    fun getTax(): String {
        return "$${(cost * 0.25).roundToInt()} tax"
    }
}
```
Now we are ready to extend the Transport class with its own getFullInfo() function:

```kotlin
open class Ship(cost: Int, val color: String) : Transport(cost) {
    override fun getFullInfo(): String {
        return super.getFullInfo() + ", $color color"
    }
}
```
By default any overridden function in Kotlin is **open**

Also, if you want to call a parent function, you can use **super**, as we did it in the example above.

- If you forget about ```override``` keyword, the compiler will warn you because there cannot be two functions ```getFullInfo()``` with the same parameters.
- You also cannot override the ```getTax()``` function because it's not ```open```.

In both cases, the source code wouldn't compile at all.  
Let's check our freshly created classes in the next example:

```kotlin
fun main() {
    val transport = Transport(1000)
    val ship = Ship(2000, "marine")
    println(transport.getFullInfo())
    println(ship.getFullInfo())
}
// $1000 cost
// $2000 cost, marine color
```
