# Activity

> An **Activity** provides a window in which your application draws the Views that form your UI. Simply put Activity implements a single screen in your app


## Activity lyfecycles

> lifecycle callback methods enable you to control what happens when a user leaves or reopens your app.

Some key lifecycle methods are described below:
- `onCreate()`: called when the system initializes the Activity for the first time (before it becomes visible).   
  In this method, you declare your Activity's UI using `setContentView()`, make findViewById() calls,  
  and define other things that should only happen when the Activity is created.
  This method also receives a savedInstanceState bundle that contains the Activity's previous state and data.
- `onStart()`: this method is called after onCreate(). The system now prepares to make the Activity visible and interactive.
- `onResume()`: the Activity becomes visible and ready to interact with the user. The application stays in this state until something interrupts it: 
  a phone call, the screen turning off, user switching to another activity and so on.
  You can use this method **to resume everything** that should only work when the Activity is visible. If it's interrupted, the `onPause()` method is called.
- `onPause()`: this method is either called when the Activity is about to stop or when it's no longer in the foreground. **Don't use it to save data**:
  it needs to be fast because an Activity can't be resumed without pausing the previous one, which could cause data loss.
  **Instead, use `onStop()` to save things you need.** You might want to release different sensors in this method, but it can also be done in the onStop()
- `onStop()`: called if the user can no longer see your Activity.  
  This happens even if the user switches between different Activities in the same app if they cover the entire screen.
  _You can perform intensive actions here, like saving data or releasing different sensors (camera, GPS, etc.)._
   Android automatically saves the state of each View object **_(if it has an ID)_**,  
   so you don't have to worry about restoring this data if the user decides to return to your Activity again.  
   After this method, the system either invokes `onRestart()` if the user switches back to your Activity or `onDestroy()`  
   when the system or user decides to kill the app or Activity.
 - `onRestart()`: fired when the Activity is about to restart after the onStop() method. Restores the Activity from the moment it was stopped. **`onStart()` is then called again.**
 - `onDestroy()`: this method is called when the Activity is about to be destroyed. There are different reasons this might happen:  
   the system doesn't have the resources to keep your app running, the user presses the "Back" button, et
   If user rotates the screen, activity will also be destroyed:  
   the system does this so that your application can reload resources based on the new configuration.  
   **If you haven't released some resources in the `onStop()` method, you should release all resources in this callback — files**, internet connections, async operations, and so on.
  
## Saving state
  
You can see an example of how to use `savedInstanceState` below:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    // Get data if it's not the first time Activity is created
    if (savedInstanceState != null) {
        timerTextView.text(savedInstanceState.getString("timerCount"))
    }
}
// Saving data before Activity is destroyed
override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putString("timerCount", timerTextView.text.toString())
}
```

## Switching Activities

```kotlin
openSecondActivityButton.setOnClickListener {
    val intent = Intent(this, SecondActivity::class.java)
    startActivity(intent)
}
```


you can use HTML syntax for this purpose

<div style= "text-align: left;"><img src="https://user-images.githubusercontent.com/63263301/202726814-36d0e9de-df1d-4e4c-9650-e269844b7096.gif" width="250" height="520"/></div>

Now let's apply the same approach to the OPEN MAIN ACTIVITY button on the `SecondActivity`:

```kotlin
openMainActivityButton.setOnClickListener {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
}
```

**It works, but it's far from perfect.** Every time one of these buttons is pressed, a new Activity is created,  
and you can't navigate away from it with a single click of the "Back" button. That's not good for user experience or device RAM.  
To avoid this problem, we can change the behavior of the OPEN MAIN ACTIVITY button so that it will destroy the current activity using `finish()`:

```kotlin
openMainActivityButton.setOnClickListener {
    finish() // destroys activity and returns to the previous one
}
```

You should be aware that this solution isn't perfect either.  
`finish()` destroys the Activity, meaning the state won't be saved, leading to the loss of Activity data.

<img src="https://user-images.githubusercontent.com/63263301/202727367-fe18fe1b-bba7-4545-ab0e-e5e8f78a3a7d.gif" width="250" height="520"/>
