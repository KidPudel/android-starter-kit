# Lazy instatiation

lazy instantiation is the tactic of **delaying the creation of an object**, the calculation of the value, or some other expensive process untill the first time is needed
actually create an instance on the first access

```kotlin
// lazy instantiation for object/instance to be created only on the first access
val viewModel: ViewModel as viewModels()
```
