# StateFlow

StateFlow is like [LiveData](https://github.com/KidPudel/android-starter-kit/blob/main/Android/live-data.md)

# Create

```kotlin
private val _stateFlow = MutalbeStateFlow("Hello world!")
val stateFlow = _stateFlow.asStateFlow()
```


```kotlin
fun triggerLiveData() {
    stateFlow.value = "LiveData"
}
```

# Observe

`StateFlow` is based on [coroutine](https://github.com/KidPudel/android-starter-kit/blob/main/Kotlin/coroutines.md) framewrok
