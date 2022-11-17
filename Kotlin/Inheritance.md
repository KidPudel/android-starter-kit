```kotlin
class Book(val pages: Int, val author: String)
```
final class. It means that this class **won't be available for inheritance** in the future.

```kotlin
open class Book(val pages: Int, val author: String)
```
```kotlin
open class Book(val pages: Int, val author: String, var cost: Float = 0F) {
    fun getFullInfo(): String {
        return "$pages pages, $author author, $$cost cost"
    }
}
```
An ```open``` modifier allows to inherit from a class  

and now our class is ready for extending.  

And then extend it:
```kotlin
class Comics(pages: Int, author: String, cost: Float) : Book(pages, author, cost) {
  fun getUSDCost(): String {
        return "$$cost cost"
    }

    fun getEuroCost(): String {
        return "€$cost cost"
    }
}
```
```kotlin
fun main() {
    val spiderManComics = Comics(60, "The Universe", 8.99F)
    print(spiderManComics.getUSDCost())
}
// output: $0.14 cost
```
As you can see, we've created a new Comics class (child class) as an extension of our Book class.  

![image](https://user-images.githubusercontent.com/63263301/202420385-a496862b-7ce4-49d4-ae03-9948fc11cd31.png)
