How to simplify boilerplate code? - use `data` keyword.  
It helps not only to **shorten your code** but also to **save your time**.  

```kotlin
data class Client(val name: String, val age: Int, val gender: String)
```
it already has: `equals()` and `hashCode()` for comparison, `copy()` for copying, and `toString()` for the string representation of the object.

# Rules

1. You can only count on properties that are inside the constructor. For example, this modified `Client` class:
   ```kotlin
   data class Client(val name: String, val age: Int, val gender: String) {
   var balance: Int = 0
   } 
   ```
   All those functions **won't consider balance field**, because **it isn't inside the constructor**.

2. You can _override_ all those functions, **except for `copy()`**:
   ```kotlin
   data class Client(val name: String, val age: Int, val gender: String) {
      var balance: Int = 0
  
      override fun toString(): String {
          return "Client(name='$name', age=$age, gender='$gender', balance=$balance)"
      }
   }
   ```
   Now balance field is involved in the `toString()` function.

3. The primary constructor of a data class must have **at least one parameter** and all of those parameters must be `val` or `var`.

![skeleton-meme](https://user-images.githubusercontent.com/63263301/203328709-ffeb76b4-cf17-4c0a-ab62-ca52d4fdce9a.gif)
