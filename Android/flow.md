# StateFlow

StateFlow is like [LiveData](https://github.com/KidPudel/android-starter-kit/blob/main/Android/live-data.md)

## Create

```kotlin
private val _stateFlow = MutalbeStateFlow("Hello world!")
val stateFlow = _stateFlow.asStateFlow()
```


```kotlin
fun triggerLiveData() {
    stateFlow.value = "LiveData"
}
```

## Observe

`StateFlow` is based on [coroutine](https://github.com/KidPudel/android-starter-kit/blob/main/Kotlin/coroutines.md) framework

```kotlin
lifecycleScope.launchWhenStarted {
    viewModel.stateFlow.collectLatest {
        binding.textView = it
    }
}
```

---

# Hot flow VS cold flow

- hot flow means that it will keep emitting value even if there's no collectors
- cold flow, it would not emitt anything if there is no collector

---

# Flow

instead of a variable we have function trigger flow, that will return `Flow`  
We **have to construct flow** with a `flow` builder, inside which we have a **_coroutine scope_**

```kotlin
fun triggerFlow: FLow<Sting> {
    return flow {
        repeat(5) {
            emit("$it times")
            delay(1000) 
        }
    }
}
```
```kotlin
lifecycleScope.launchWhenStarted {
    viewModel.triggerFlow.collectLatest {
        binding.textView = it
    }
}
```

```kotlin
fun simple(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(100) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    // Collect the flow
    simple().collect { value -> println(value) } 
}
```
```kotlin
I'm not blocked 1
1
I'm not blocked 2
2
I'm not blocked 3
3
```

doesn't containt state, it's a one time thing, when activity is recrated, it will be discarted

# SharedFlow

One time event, don's save a state, so we can do the same thing over and over again
