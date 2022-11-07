# Nested classes

```kotlin
class Superhero {
    val power = 1000

    class MagicCloak {
        // you cannot access something from Superhero here
        val magicPower = 100
    }
    // you need to create a MagicCloak object to access its members
    val magicPower = power*MagicCloak().magicPower

    class Hammer {
        // you cannot access power property from Superhero here
        val mightPower = 100
    }
    val mightPower = power*Hammer().mightPower
}
```

to create nested object outside

```kotlin
val cloak = Superhero.MagicCloak()
val hammer = Superhero.Hammer()
```
As you can see, a simple nested class is not really connected with the outer class.

# Inner classes

A regular nested class cannot access members of its outer class. But a nested class marked as an inner class can.

```kotlin
class Cat(val name: String) {
    fun sayMeow() {
        println("$name says: \"Meow\".")
    }

    inner class Bow(val color: String) {
        fun putOnABow() {
            sayMeow()
            println("The bow is on!")
        }

        fun printColor() {
            println("The cat named $name has a $color bow.")
        }
    }
}
```
inside the Bow class, we have access to all members of the class Cat: the name property and the sayMeow function.

Create
```kotlin
fun main() {
    val cat: Cat = Cat("Princess")
    val bow: Cat.Bow = cat.Bow("golden")

    bow.printColor()
    bow.putOnABow()
}
```

> The cat named Princess has a golden bow.
Princess says: "Meow".
The bow is on!


You may encounter inner classes that have members with the same names as their outer classes.

 Write this@Cat.color in the inner class code and you will get the color of the outer class,
 while using color or this.color will always give you the color property of the current class:
 ```kotlin
 class Cat(val name: String, val color: String) {
    inner class Bow(val color: String) {
        fun printColor() {
            println("The cat named $name is ${this@Cat.color} and has a $color bow.")
        }
    }
}
```

## Rules for Inner classes
An inner class can access all members of its outer class.

To access the inner class, you need to instantiate the outer class first, as inner classes are associated with an instance of their enclosing class. In the example below, the constructor of the inner class Inner is called with an instance of the containing class:

```kotlin
val outer = Outer()
val inner = outer.Inner()
```

**Inner classes carry a reference to an object of an outer class, so to use inner classes, we must instantiate an outer class first.**
