# What is State?

## State in any app is the value that can change overtime. 

State discribes how given UI, will look at the moment.  

We set state on top of Composable

Conceptually it's pretty similar to: [LiveData](https://github.com/KidPudel/android-starter-kit/blob/main/Android/live-data.md), [FLow](https://github.com/KidPudel/android-starter-kit/blob/main/Android/flow.md)  

Whenever we set state it will tell Compose: "Hey, the state is changed!"  
then ->  
Compose on the background will re-drawn (**recompose**) the **whole** Composable (our function)


# Declare state

We decalare state on top of the composable
```kotlin
val color = mutableStateOf(Color.Yellow)
```

# Use of state

### Problem
After recomposing, function is recalled and therefore reset the state :(  

### Solution
We can apply `remember` lambda from J.C.  
What it will do, **it will rememer the state value from the last composition** :)

we can use it like that:  
```kotlin
val name = remember { mutableStateOf("User") }
```
this will provide `MutableState<String>`

or we can use it like that: 
```kotlin
var name = remember by { mutableStateOf("User") }
```
this will provide `String`


# Logic in example

For example, we **change** our `TextFiled` value, therefore we shoud use state to recompose composable, also other components would know about current state
