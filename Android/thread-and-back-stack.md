> A **task** is a collection of activities that a user interacts with when using an app.
>  Activities are stacked in the back stack in the order in which they are opened.


If you click the "Recently opened" button on your phone, you will see a list of tasks:
![image](https://user-images.githubusercontent.com/63263301/202866300-ef464a14-e9c6-4e22-9901-3fc871d9f7ad.png)



The activities in a task are arranged in a stack (known as the back stack). You can see a visualization of this below:
![image](https://user-images.githubusercontent.com/63263301/202866302-d192f0da-5fd3-4525-a5aa-c9729e2946a0.png)



Let's take the **Settings app as an example**.  
**A new task will be created when you open it**, and the **main settings activity will be shown** on the screen and added (pushed) to the task's stack.  
Then, if you click on any item in the settings list, a new activity will be created, shown, and pushed to the stack.  
Pressing the "Back" button will destroy that activity and remove (pop) it from the stack. Clicking the "Back" button again will pop the previous activity off.  
And once all activities are popped off the stack, you will be returned to the Home screen, and the task will no longer exist.  
This is the stack's standard behavior. It's known as "last in, first out" and results in the most recent activity being shown on the screen.  
It's a launch mode that works well for most apps.

Here is a diagram of the example described above:
<img src="https://user-images.githubusercontent.com/63263301/202866308-379a8409-dc8f-4e7f-b684-5977081b5d00.png" width="250" height="520"/>



If a user hits the "Home" button without closing activities, the associated task will be **backgrounded**.  
It should then be** possible to return to this task later with no changes in the back stack**.  
However, if the user has many tasks (or apps) in the background, the system might kill some of them to recover resources, leading to the loss of unsaved data.  
Therefore it is the developer's responsibility to ensure that important data is saved.


## Launch modes

There are four activity launch modes available: `standard`, `singleTop`, `singleTask` and `singleInstance`.  
They are defined in the `AndroidManifest.xml` file in the activity tag <activity>:

<manifest xmlns:android="<http://schemas.android.com/apk/res/android>"
    package="com.hyperskill.tasks">

    <application
        <!--skipped some settings-->
        <activity
            android:name=".SecondActivity"
            android:launchMode="standard"/>
        <activity
            <!--...-->
        </activity>
    </application>
</manifest>

`standard`
The default launch mode.  
The system **creates a new activity** and **routes an Intent to it**.  
The **activity is pushed on top of the stack**, _even if the same activity is already foregrounded_.

`singleTop`
This mode behaves differently from `standard` if the activity you would like to start is already on the top of the stack.  
Instead of creating it again, the **system routes an Intent to the existing instance through a call to the onNewIntent() method**.
**_However_**, if the activities were ordered differently to start with (A-C-B), calling activity C again would change the stack (A-C-B-C), as you can see below.
![image](https://user-images.githubusercontent.com/63263301/202866633-abcc6a66-d2d3-452f-bc3c-310d4d53e072.png)


`singleTask`

```diff
- Be careful with this launch mode because its behavior seems strange to most users. 
```
Only one instance of an activity with this launch mode can exist at a time.

