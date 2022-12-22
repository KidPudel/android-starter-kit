[You might want to use coroutines instead of handlers.](https://betterprogramming.pub/drift-away-from-android-handlers-use-coroutines-and-jobs-instead-581815dd3e72)  

**A Handler** operates with a queue of messages. It allows us to **schedule code execution** at a certain point in the future, with or without a specified **delay**.  
Handlers also enable us to execute code **outside of our thread**.  
1. put a work into a queue  
2. execute it


```kotlin
private val handler = Handler(Looper.getMainLooper())
```
to associate Our handler object with Mainthread

`Looper` - class is used to run a message loop for a thread

`post()` - causes `Runnable` to be added to message queue with a specific time spand, _in this case it's now_

`Runnable` - interface to use for any class whose instances is going to be executed by thread

![image](https://user-images.githubusercontent.com/63263301/203981052-43b57db0-23be-4333-9345-b00e0d697097.png)

Thread is looping through a message queue, and looking for a message that has a that has time stand for now, and it despatches it to handler to execute  
If there's no message in a queue that is to be executed right now, then this thread is blocks and waits untill another message hits "now"

```kotlin

override fun OnCreate(savedInstanceState: Bundle?) {
    super.OnCreate(savedInstanceState)
    
    window.decorView.setBackgroundColor(color)
    
    // call it here
    handler.postDelayed(updateLight, 1000)
}

private val updateLight: Runnable = object : Runnable {
    override fun run() {
        color = colors[(colors.indexOf(color) + 1) % colors.size]
        // and call it each time again
        handler.postDelayed(this, 1000)
        
    }
}

```

`RemoveCallback` - Then we need to delete a post from our que to avoid ANR

If we had tried to use Thread.sleep to create this effect, our application would have frozen forever,  
making it impossible to draw the next frame or receive any input. Using a Handler allowed us to avoid this situation.

We already know that we can't load the main thread with long tasks.  
For example, we shouldn't create IO tasks like accessing databases, accessing files, or interacting with networks in the main thread.

From the official documentation:

> Executing IO operations on the main thread is a common cause of slow operations on the main thread, which can cause ANRs. 
> It’s recommended to move all IO operations to a worker thread.

ANR is a type of error, and the abbreviation stands for "Application Not Responding."

We must also remember to stop the execution of our task with the `removeCallbacks()` method if the activity is stopped:

```kotlin
override fun onStop() {
    super.onStop()
    handler.removeCallbacks(updateLight)
}
```
This ensures that our activity will stop flashing if it isn't visible and,  
if it gets destroyed, we will avoid a memory leak and prevent any interaction with detached views.  

## Multithreading with Handlers

Now let's try to create a separate thread so that we can use it to increment a counter and display the result in `counterTextView`:

```kotlin
button.setOnClickListener {
    thread {
        for (i in 0..5) {
            counterTextView.text = i.toString()
            Thread.sleep(100) // let's pretend we're doing some work
        }
    }
}
```

```diff
- If you attempt to run this code, you're sure to catch an error.
```
This is because **we can only touch our UI elements from the main thread**. To solve this problem, we again need to use a **Handler**.

So, let's utilize a handler object and `post()` our logic to it:
```kotlin
button.setOnClickListener {
    thread {
        for (i in 0..5) {
            handler.post {
                counterTextView.text = i.toString()
            }
            Thread.sleep(100) // let's pretend we're doing some work
        }
    }
}
```
As you can see, we are looping through the values in a **separate thread** and **using `handler.post()` to apply the text setting.**

_**done in a separate thread to not freeze our main thread -> threfore freeze entire app**_

### Another example

You can view another small example below. We start by creating a `StringBuilder` variable to store the characters of the alphabet:

```kotlin
val sb = StringBuilder()
button.setOnClickListener {
    button.isEnabled = false
    sb.setLength(0)
    thread {
        for (char in 'a'..'z') {
            val string = sb.append(char).toString()
            handler.post {
                counterTextView.text = string
                if (char == 'z')
                    button.isEnabled = true
            }
            Thread.sleep(100) // let's pretend we're doing some work
        }
    }
}
```

Every 100 milliseconds, we increment the line containing the alphabet and append another character. This has been achieved by creating a special thread with a delay that results in the string's progressive expansion. `handler.post()` is **required** so that we can access the main thread and touch views.

> Use Handler.postDelayed to schedule tasks if you are working in UI Thread and Thread.sleep if you are working in background thread.
> Apparently you are sending some data using network, you must do it in the background thread, hence Thread.sleep is recommended.

