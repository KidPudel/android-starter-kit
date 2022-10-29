In the following example, we call the prepareData function with one lambda function as an argument.

```kotlin
prepareData { data ->
    sendToServer(data)
}
```
This sendToServer lambda is often referred to as a callback. What does the term 'callback function' mean?

It's called by the first function when it's ready
