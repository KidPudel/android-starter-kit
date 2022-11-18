**A Handler** operates with a queue of messages. It allows us to **schedule code execution** at a certain point in the future, with or without a specified **delay**.  
Handlers also enable us to execute code **outside of our thread**.


```kotlin
private val handler = Handler(Looper.getMainLooper())
```
to associate Our handler object with Mainthread

`Looper` - class is used to run a message loop for a thread

`post()` - causes `Runnable` to be added to message queue

`Runnable` - interface to use for any class whose instances is going to be executed by thread


```kotlin
private val updateLight: Runnable = object : Runnable {
    override fun run() {
        color = colors[(colors.indexOf(color) + 1) % colors.size]
        
    }
}

override fun OnCreate(savedInstanceState: Bundle?) {
    super.OnCreate(savedInstanceState)
    
    window.decorView.setBackgroundColor(color)
    handler.postDelayed(this, 1000)
}
```

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
- If you attempt to run this code, you're sure to catch an error. This is because we can only touch our UI elements from the main thread. To solve this problem, we again need to use a Handler.
```