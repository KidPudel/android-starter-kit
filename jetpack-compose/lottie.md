# to enable:
```kotlin
implementation 'com.airbnb.android:lottie-compose:5.2.0'
```

# what to animate
```kotlin
val lottieCompositionResult = rememberLottieComposition(spec = LottieCombinationSpec.RawRes(R.raw.rocket)
```

# how it will progress
```kotlin
val progressAnimation = by animateLottieCompositionAsState(
  composition = lottieCompositionResult.value
  isPlaying = true
  iteration = LottieConstants.IterateForever
  speed = 1.0f
)
```

# display
```kotlin
LittieAnimation(composition = lottieCompositionResult.value, progress = { progressAnimation })
```
