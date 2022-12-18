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

instead of a fariable we have function trigger flow, that will return `Flow`  
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

doesn't containt state, it's a one time thing, when activity is recrated, it will be discarted

# SharedFlow

One time event, don's save a state, so we can do the same thing over and over again
