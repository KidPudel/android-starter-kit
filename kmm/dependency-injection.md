Dependency injection is just **_giving_** an object an instance variables (pass it through a constructor), so injection is basically just **_giving_** variables, dependencies

# Example
We have our main viewmodel
```kotlin
class MainViewModel: ViewModel() {
    init {
        doNetworkCall()
    }
    private fun doNetworkCall() {
    
    }
}

```

And we want to make a network call, we can do it by implementing repository first

```kotlin
class NetworkRepository {
    fun doNetworkCall() {
        // network call..
    }
}

```

And now we can call it in viewmodel:

```kotlin
class MainViewModel: ViewModel() {
    
    val repository = NetworkRepository()
    
    init {
        doNetworkCall()
    }
    private fun doNetworkCall() {
        repository.doNetworkCall()
    }
}

```

**_This is bad_** because we force our viewmodel to have an instance of our specific repository implementation

To resolve that we can create a layer of an abstraction:
```kotlin
interface INetworkRepository {
    fun doNetworkCall()
}
```

Now we can force our `NetworkRepository` to implement that interface:  

```kotlin
class NetworkRepository : INetworkRepository {
    override fun doNetworkCall() {
        // network call..
    }
}

```

And now we can actually flexibly **_use our network repository without telling viewmodel the exact implementation !!_**

```kotlin
class MainViewModel(
    private val repository: INetworkRepository
): ViewModel() {
    
    init {
        doNetworkCall()
    }
    private fun doNetworkCall() {
        repository.doNetworkCall()
    }
}

```

