**Sometimes we need only a single instance, no more, no less. It can help you organize your codebase and gather similar methods together.**
Singleton is a really useful pattern, and Kotlin provides a specific structure to declare singletons: object declaration.

```kotlin
object PlayingField {

    fun getAllPlayers(): Array<Player> {
        /* ... */
    }
    
    fun isPlayerInGame(player: Player): Boolean {
        /* ... */
    }

}
```
When you use object declaration, the constructor is not available because **Kotlin does it itself**.
To get an instance of our playing field, use PlayingField declaration. We can use it anywhere and it will return the same object every time.

```kotlin
...
val players = PlayingField.getAllPlayers()
...
if (!PlayingField.isPlayerInGame(player)) {
        return println("Current player lost. Next...")
    }
...
```

## Nested object

Often you need to create a singleton that is somehow related to another class. 
For example, you create a Player class to store information about different characters in the game.
All these characters can share some characteristics, for example, speed. How would we store such information?

```kotlin
class Player(val id: Int) {
    object Properties {
        /* Default player speed in playing field – 7 cells per turn */
        val defaultSpeed = 7

        fun calcMovePenalty(cell: Int): Int {
            /* calc move speed penalty */
        }
    }
}

/* prints 7 */
println(Player.Properties.defaultSpeed)
```

**You can declare any number of objects inside another class**

## Compile-time constants

```kotlin
object Languages {
    const val FAVORITE_LANGUAGE = "Kotlin"

    // ...
}
```
You might wonder: why not just declare all constants as top-level? Why do we need to declare them in an object?

Generally speaking, both approaches may make sense depending on the situation.
The fact is that careless use of top-level properties reduces the readability and organization of the code and,
as a result, leads to unpleasant errors. If you declare all the constants at the top of the file, then eventually,
you may realize that in one place you have hundreds of declarations that are not related to each other.
Therefore, if a constant refers to a specific object, it is better to declare it in that object.

**It is better to declare constants in the object to which they relate.**

*Another useful feature of nested objects is that you can declare any number of objects inside another object.*


```kotlin
object Game {
    object Properties {
        val maxPlayersCount = 13
        val maxGameDurationInSec = 2400
    }

    object Info {
        val name = "My super game"
    }
}
```


## Summary

Object declaration is a useful feature. Mainly, we can use object keyword to create singletons.

_Another use_ of object declaration is nested objects. It is an _easy way to create a structure that is **associated with the entire class** rather than a single instance_.
If you use it wisely and correctly, you can improve your programming experience and make your code more readable.
This is helpful for organizing the data in the singletons.
