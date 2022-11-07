# Assertions
> The assertion is a mechanism that monitors the program state,  
> but unlike additional print statements,  
> it terminates the program in a fail-fast manner when things go wrong.

_Fail-fast is an approach when errors that could otherwise be non-fatal are forced to cause an immediate failure, thus making them visible._


```kotlin
class Cat(val name: String, val age: Int) {
    val enoughCat = false // Of course, it's a false, there are never enough cats!
    init {
        check(!enoughCat) { "You cannot add a new cat" } // IllegalStateException
        require(age >= 0) { "Invalid age: $age" }        // IllegalArgumentException
    }
}
```
 You should use ```require``` when you are validating the argument passed to the function and ```check``` when you are checking the state of the object, as in this case.
