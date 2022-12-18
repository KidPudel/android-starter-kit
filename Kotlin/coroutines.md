# Coroutines

**it's a component that allows execution to be suspended and resumed**.  
it is similar to threading, except coroutines are even **more light-weight**.

it's used to asynchronize execution for the **outer** code, **inside coroutine use another coroutine.**  

So what we have?  
- `coroutine` - is an instance of **suspendable computation**, that is **not dependet on any thread**, and can **switch** it as needed, takes a code that **run concurrently**
- `suspend function` - function that **can be paused and resumed**  

[more info](#more-details)

# Creating coroutines 

1. Create corutines scope
   - `GlobalScope` alive as long as application
   - `lifecycleScope` if context is destroyed, coroutines are destroyed
   - `viewModelScope` the same as `lyfecycleScope`, but for ViewModel
3. Call coroutine builder
   - `launch`, this will launch coroutine, **is used to fire and forget coroutine**. It is like starting a new thread
   - `async` this will launch coroutine, but **it will return results of calculation** (used to return something)
   - `launchWhenStarted` used to collect from `stateFlow`
5. Set a [context*](#context)
```kotlin
GlobalScope.launch(context) {
}
```

# Context

Context (Dispatcher) will describe which thread corresponding coroutine uses.

- **Dispatcher.Main** - in a main thread for ui operation in coroutine 
- **.IO** - data operation, networking
- **.Default** - doing complex and doing long operation (operating through list)
- **.Unconfined** - not confined (ограничен) to a specific thread 🧵 
- **newSingleThreadContext(“myThread”)**

## Switch context

```kotlin
GlobalScope.launch(Dispatcher.IO) {
    val response = networkRequest()
    Changed context to Main ⬇️
    withContext(Dispatcher.Main) {
         Text.text = response
     }
}
```

## Suspend functions without coroutine

`runBlocking` is a coroutine that blocks thread (outside of scope)
```kotlin
Log.d("before runBlocking")
runBlocking {
    Log.d("start of runBlocking")
    delay(5000)
    Log.d("end of runBlocking")
}
Log.d("after runBlocking")
```
```kotlin
before runBlocking
start of runBlocking
---
delay of 5 seconds
---
end of runBlocking
after runBlocking
```
This coroutine is coroutine that will bock main thread

## Wait, corputine returns something? 

Yes, it returns:  
- `Job` with `launch` constructor
- `Deffered` with `async` constructor

```kotlin
val job = GlobalScope.launch(Dispatcher.Default) {
    repeat(5) {
        Log.d(TAG, "Doing something..")
    }    
}
```
job has some suspend function
- `join` block thread, until coroutine is finished (all join coroutines will go together)
- `cancel` cancel job, but sometimes you need to check it in coroute with `isActive`, because it can be to busy to check that it's cancled

# Timeout

coroutine has function `withTimeout(timeMills)`, to cancel at some time

# Await Async

if we put 2 suspend function to into coroutine, they will excute **sequentially**..  
To prevent that instead of `.launch` use `.async` and call it with `.await`

```kotlin
val job = GlobalScope.launch(Dispatcher.Default) {
    val response1 = async { networkRequest1() }
    val response2 = async { networkRequest2() }
    Log.d(TAG, "${response1.await}, ${response2.await}")
}
```
it will take 3 secons instead of 6


# More details

## Silly explanation

put it at the end to not distract.
> Let's go back to the analogy with a firm. We can compare a coroutine with a usual task that can be done by an employee. First, the task is created, and then assigned to a person. When the person is ready, they decide what to do: start handling the task or transfer it to another person. When someone starts working on the task, there's no guarantee it will be done right away. For example, say the task was to call another department, and the person called but nobody answered. Then that person can "suspend" the task, that is, postpone it for a while. During this gap, the person can take another task and start working on it, or, if there are no more assigned tasks, they can take a break.

 Also, the library (kotlinx.coroutines) is specially designed to make the code similar on different platforms.
