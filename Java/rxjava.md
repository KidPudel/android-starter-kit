# Overview
allows you to work with asynchronous data streams using the concept of Observables, which emit items over time.  
You can then apply various operators to manipulate and transform these streams of data easily.  
Basically like coroutines.

---

# Key components
- **Observable**: Represents a _stream of data that can emit items over time_. It can emit data, error, or completion events.
- **Observer**: _Listens to the emissions from an Observable and reacts to the data_, error, or completion events.
- **Subscriber**: An alternative to Observer, which provides additional methods to handle subscription and unsubscription.
- **Operators**: Allow you to transform, filter, and combine Observables to create more complex data flows.
- **Schedulers**: Define the threading context in which Observables emit items and Observers consume them. They are used to control the execution thread for various operations.
- **Disposable**: Represents a resource that needs to be disposed of when it's no longer needed, preventing memory leaks.


# Setting up
```graddle
dependencies {
    implementation "io.reactivex.rxjava3:rxjava:3.x.x"
    implementation "io.reactivex.rxjava3:rxandroid:3.x.x"
}
```

# Basic usage

1. Creating Observables
You can create an Observable using various methods such as `Observable.create`, `Observable.fromIterable`, `Observable.just`, etc. For example:
```kotlin
val observable: Observable<String> = Observable.just("Hello", "RxJava", "World")
```

2. Subscribing to Observables (with Flow that could be collectLatest())
You can subscribe to an Observable using the `subscribe` method and provide functions to handle the emitted items, errors, and completion.
```kotlin
observable.subscribe(
    { data -> println("Data: $data") }, // onNext
    { error -> println("Error: $error") }, // onError
    { println("Completed") } // onComplete
)
```

3. Additionally apply operators
You can apply operators like `map`, `filter`, `flatMap`, etc., to manipulate the data emitted by Observables. For example:
```kotlin
observable.map { it.length() }.subscribe(
    { length -> println("Length: $length") }
)
```

# Schedulers (like launch in coroutines)
RxJava operates on the same thread on which the Observable is subscribed by default.  
With schedulers you can control the threading behavior.  
For Android development, you can use `Schedulers.io()` for I/O operations and `AndroidSchedulers.mainThread()` to update the UI.
```kotlin
observable.subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe { data -> println("Data: $data") }
```

# Disposing Observables
It's crucial to manage subscriptions and dispose of them when they are no longer needed, especially in Android to avoid memory leaks. You can use the Disposable returned by the `subscribe` method to manage this.
```kotlin
val disposable: Disposable = observable.subscribe(...)
// When no longer needed
disposable.dispose()
```


# Coroutine vs RxJava
```kotlin
// Coroutine function that fetches data asynchronously
suspend fun fetchData(): Data {
    return withContext(Dispatchers.IO) {
        // Perform some asynchronous operation
        // Return the data
    }
}

// Usage of the coroutine function
fun processData() {
    viewModelScope.launch {
        try {
            val data = fetchData()
            // Process the data
        } catch (e: Exception) {
            // Handle the exception
        }
    }
}
```

```kotlin
// RxJava Observable that fetches data asynchronously
fun fetchData(): Observable<Data> {
    return Observable.create { emitter ->
        // Perform some asynchronous operation
        // Emit the data or handle the error
    }
}

// Usage of the Observable
fun processData() {
    fetchData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { data ->
                // Process the data
            },
            { error ->
                // Handle the error
            }
        )
}
```
