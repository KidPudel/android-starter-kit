`AsyncImage` is composable that makes image request asynchronously and renders the result.   

asynchronous coroutine image louder
```gradle
implementation("io.coil-kt:coil-compose:2.2.2")
```

```kotlin
AsyncImage(
    model = "https://static.wikia.nocookie.net/smiling-friends/images/e/e5/Glep1.png/revision/latest?cb=20221016232719",
    contentDescription = "glep"
    )
```
