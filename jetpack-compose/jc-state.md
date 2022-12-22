# What is State?

State discribes how given UI, will look at the moment.  

We set state on top of Composable

Conceptually it's pretty similar to: [LiveData](https://github.com/KidPudel/android-starter-kit/blob/main/Android/live-data.md), [FLow](https://github.com/KidPudel/android-starter-kit/blob/main/Android/flow.md)  

Whenever we set state it will tell Compose: "Hey, the state is changed!"  
then ->  
Compose on the background will re-drawn (**recompose**) the **whole** Composable (our function)

### Problem
After recomposing, function is recalled and therefore reset the state :(  

### Solution
We can apply `remember` lambda from J.C.  
What it will do, **it will rememer the state value from the last composition**
