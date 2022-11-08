# LiveData

LiveData - state holder
so it holds your UI state, if any value in your UI changes overtime, then that change will be stored
all observers will be notified of the current data


**VIEWMODEL**
```kotlin
private val liveData = MutableLiveData("Hello World")
val getLiveData: LiveData<String>
    get() = liveData
```

```kotlin
fun triggerLiveData() {
    liveData.value = "LiveData"
}
```

**MAINACTIVITY**

that will be called whenever there is a change on the livedata value
```kotlin
private fun substribeToObservalbe() {
    viewModel.liveData.observe(this) {
        binding.tvLiveData.text = it
    }
}
```
```this``` we need to pass lifecicle owner


_setValue_ is executed on the main thread
_postValue_ is executed on the background thread
when main activity is closed postValue can changed, but u will miss it, because it's going to change on the background 
