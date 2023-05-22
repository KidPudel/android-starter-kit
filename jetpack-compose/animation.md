# 1. create infinite transition that runs child animations
```kotlin
val infiniteTransition = rememberInfiniteTransition()
```

# create animation
```kotlin
val animation = inifinteTransition.animateFloat(
  initialValue = 0f,
  targetValue = 360f,
  animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing))
)
```

# use animation
```kotlin
Image(modifier = Modifier.rotate(animation.value))
```
