# Property getter

```kotlin
class Client {
    val name = "Unknown"
}

val client = Client()
```
```kotin
class Client {
    val name = "Unknown"
        get() {
            return field
        }
}

// or with omitted curly brackets and the body of the get() function

class Client {
    val name = "Unknown"
        get() = field
}
```

# Custom getter

```kotlin
class Client {
    val name = "Unknown"
        get() {
            println("Somebody wants to know $field name")
            return field
        }
}

val client = Client()

val name = client.name // prints Somebody wants to know Unknown name
println(name)          // prints Unknown
```

# Property setters
```kotlin
class Client {
    var name = "Unknown" // default value
}

val client = Client()
client.name = "Ann"      // name property now stores "Ann"
```
```kotlin
class Client {
    var name = "Unknown"
        set(value) {
            field = value
        }
}
```

# Custom setters

```kotlin
class Client {
    var name = "Unknown"
    var age = 18
        set(value) {                      
            field = if (value < 0) {
                println("Age cannot be negative. Set to $defaultAge")
                defaultAge
            } else
                value
        }
    val defaultAge = 18
}

val client = Client()
client.age = -1      // Age cannot be negative. Set to 18.
println(client.age)  // 18
```

# Additional features

Both
```kotlin
class Client {
    var name = "Unknown"
        get() {
            println("Somebody wants to know $field name")
            return field
        }
        set(value) {
            println("The name is changing. Old value is $field. New value is $value.")
            field = value
        }
}
```

If you want to add a getter and/or a setter to a property **in a constructor, just "move" the property out**.  
Remember that in this case, you need to **use another variable, not the property from the constructor**:
