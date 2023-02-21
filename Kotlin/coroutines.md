# Coroutines

**it's a component that allows execution to be suspended and resumed**. (they are workers in the construction (thread))  
They **can be launched on other threads (Dispateched.IO, Default, etc.)** or they **could be launched on the same main thread**, _that would block the UI for example, but it would certanly finish their job_.  

### Reminder on how it would look with all those coroutines in threads  
![image](https://user-images.githubusercontent.com/63263301/220064187-11e14ccf-30c2-42c6-9ab0-9819ae081690.png)  


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
- `join` will **block code** in the thread it was called, until coroutine is finished (**_all joined coroutines in that scope will go together_**) - otherwise code will just go further and finish, or print null for some variables (in this example `async` and `await` is **much better solution**)
   ![image](https://user-images.githubusercontent.com/63263301/220066669-06132833-bbac-463b-a78f-c1e2190675a4.png) 
   return nulls
   ![image](https://user-images.githubusercontent.com/63263301/220058437-fa9e675f-23dc-468a-b643-8f58bfce3ac8.png)  
   return results in a 3 seconds
   #### Examples
   that is started on the same thread asynchroniously => therefore not finished
   ```kotlin
   @OptIn(DelicateCoroutinesApi::class)
   fun main() = runBlocking {
       GlobalScope.launch {
           println("Coroutine in main has started")
           delay(5000L)
       }
       println("Finish the program")
   }
   Finish the program
   ```
   that is started on the same thread asynchroniously, but that is joined => therefore blocked main execution
   ```kotlin
   @OptIn(DelicateCoroutinesApi::class)
   fun main() = runBlocking {
      val job = GlobalScope.launch {
        println("Coroutine in main has started")
        delay(5000L)
    }
    job.join()
    println("Finish the program")
   }
   Coroutine in main has started
   Finish the program
   ```
- `cancel` cancel job, but sometimes you need to check it in coroute with `isActive`, because it can be to busy to check that it's cancled

# Timeout

coroutine has function `withTimeout(timeMills)`, to cancel at some time

# Await Async

## if we put 2 suspend function to into coroutine, they will excute *sequentially*.. 

To prevent that instead of `.launch` use `.async` and call it with `.await`

```kotlin
val job = GlobalScope.launch(Dispatcher.Default) {
    val response1 = async { networkRequest1() }
    val response2 = async { networkRequest2() }
    Log.d(TAG, "${response1.await}, ${response2.await}")
}
```
it will take 3 secons instead of 6  
`await` will block our coroutine until the answer is awailable

## Bad
![image](https://user-images.githubusercontent.com/63263301/220052755-56d7bab6-8e9c-49bd-8f13-74e65788568e.png)  


## Good
![image](https://user-images.githubusercontent.com/63263301/220052842-a161aba6-25a2-46e4-9b97-22f5c7fff7f3.png)  


## Bad
![image](https://user-images.githubusercontent.com/63263301/220053484-1c4b4e2a-c886-4c37-b746-e8f98e52eeeb.png)  

## Good
![image](https://user-images.githubusercontent.com/63263301/220053543-7733ebf0-4cfb-45ab-80ba-ebf09b71d04d.png)  

## Bad
![image](https://user-images.githubusercontent.com/63263301/220055651-b126eed6-c1ea-4ad2-98db-00d4fea763a3.png)  
![image](https://user-images.githubusercontent.com/63263301/220055859-1bb8fb33-e7c0-42cc-9f32-a1e106bb39b7.png)  

it will be canceled if, you'd rotate it or switch to dark mode

## Good
![image](https://user-images.githubusercontent.com/63263301/220055959-3bd7f9df-a35f-4c40-ab7a-f652f43b982b.png)  
![image](https://user-images.githubusercontent.com/63263301/220056017-1a5aceb1-2a38-4c1d-a2ab-2ef679d32981.png)




# More details

## Silly explanation

put it at the end to not distract.
> Let's go back to the analogy with a firm. We can compare a coroutine with a usual task that can be done by an employee. First, the task is created, and then assigned to a person. When the person is ready, they decide what to do: start handling the task or transfer it to another person. When someone starts working on the task, there's no guarantee it will be done right away. For example, say the task was to call another department, and the person called but nobody answered. Then that person can "suspend" the task, that is, postpone it for a while. During this gap, the person can take another task and start working on it, or, if there are no more assigned tasks, they can take a break.

 Also, the library (kotlinx.coroutines) is specially designed to make the code similar on different platforms.
 
 
 ## [Use it instead of handlers](https://betterprogramming.pub/drift-away-from-android-handlers-use-coroutines-and-jobs-instead-581815dd3e72)

When you call a function without using async and await, the function executes synchronously. This means that the function will block the execution of the calling code until the function has completed its task.

On the other hand, when you call a function using async and await, the function executes asynchronously. This means that the function does not block the execution of the calling code, allowing other code to be executed while the function is still running.

Here's an example to illustrate the difference:

```dart
Copy code
import 'dart:async';

void main() {
  print('Before calling longRunningTask');
  longRunningTask();
  print('After calling longRunningTask');
}

Future<void> longRunningTask() async {
  print('Task started');
  await Future.delayed(Duration(seconds: 3));
  print('Task completed');
}
```
In the example above, the longRunningTask() function simulates a long-running task by waiting for 3 seconds before printing "Task completed". When we call this function without using async and await, the output will be:
```
Copy code
Before calling longRunningTask
Task started
Task completed
After calling longRunningTask
As you can see, the execution of the calling code is blocked until the function has completed its task.
```
However, if we call the function using async and await like this:

```dart
import 'dart:async';

void main() async {
  print('Before calling longRunningTask');
  await longRunningTask();
  print('After calling longRunningTask');
}

Future<void> longRunningTask() async {
  print('Task started');
  await Future.delayed(Duration(seconds: 3));
  print('Task completed');
}
```
The output will be:

```
Before calling longRunningTask
Task started
After calling longRunningTask
Task completed
As you can see, the execution of the calling code is not blocked, and the message "After calling longRunningTask" is printed before the task completes.
```

---

```dart
void _showSnackBar(BuildContext context) async {
    await _getCharacters();
    _changeColor(context);
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(
            "Navigate to character list, but i can tell that first is ${characters.isNotEmpty ? characters.first.fullName : "oops.. it isn't ready yet.."}"),
      ),
    );
  }

  Future<void> _getCharacters() async {
    final charactersUseCase = locator.get<CharactersUseCase>();
    if (characters.isEmpty) {
      final receivedCharacters = await charactersUseCase.getCharacters();
      setState(() {
        characters = receivedCharacters;
      });
    }
  }

  Future<void> _changeColor(BuildContext context) async {
    MyColors? myColors;
    if (context.mounted) {
      myColors = MyColors.of(context);
    }

    while (true) {
      await Future.delayed(const Duration(seconds: 1));
      setState(() {
        if (!(color == myColors?.bakerMilkPink)) {
          color = myColors?.bakerMilkPink;
        } else {
          color = myColors?.selectiveYellow;
        }
      });
    }
  }
```
