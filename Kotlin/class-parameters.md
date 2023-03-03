When you write val/var within the constructor, it declares a property inside the class. When you do not write it, it is simply a parameter passed to the primary constructor, where you can access the parameters within the init block or use it to initialize other properties. For example,

```kotlin
class User(val id: Long, email: String) {
    val hasEmail = email.isNotBlank()    //email can be accessed here
    init {
        //email can be accessed here
    }

    fun getEmail(){
        //email can't be accessed here
    }
}
```
