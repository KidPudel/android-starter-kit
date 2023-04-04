When using `by` keyword in Kotlin, you're telling that you want to delegate implementation of functionality to someone else.

```kotlin
val bluetoothAdapter: BluetoothAdapter? by lazy {
    bluetoothManager?.adapter
}
```


```kotlin
interface Animal {
    fun makeSound()
}

class Cat : Animal by Lion() {
    
}

class Lion : Animal {
    override fun makeSound() {
        println("meow")
    }
}


Lion.makeSound() // meow
Cat.makeSound() // meow

```
