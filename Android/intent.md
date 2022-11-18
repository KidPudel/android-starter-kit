**Intent** is an object for messaging between the app components and other apps. Simply saying, Intent does the job of telling Android what should be done or has been done.   
One of the most popular use cases for Intents is launching another activity of your application.  
It also can be used to tell other applications that your app needs another app to perform some action, for example, get a selected photo from your Photos app.  

There are two types of intent: _explicit_ and _implicit_.  

**Explicit intents** specify which _app component_ should satisfy the intent. This type of intent is normally used _inside your app_.   
For example, you can start an activity or service by using explicit intents.  

**Implicit intents** are a little bit more abstract.   
They don't name a specific component to perform the action, so the system will find apps that can perform the required action

## Explicit intents

![image](https://user-images.githubusercontent.com/63263301/202568081-4d0d0162-290d-4cf9-b719-b7272f2756c6.png)

To do that, set `onClickListener` on the button and define the Intent:

```kotlin
button.setOnClickListener {
    val intent = Intent(this, UserActivity::class.java)
    startActivity(intent)
}
```
Here, we explicitly define the context (`this` or `this@NameOfYourActivity`) as the first parameter,  
and the name of the Activity to which the app should switch as the second parameter.  
After, we call `startActivity(intentName)`, and we will see the layout we have requested. You can call any activity by its class name using this function.  
Intent constructor asks us to pass a **Context** as a first parameter. A **Context** is an abstract class whose implementation is provided by Android, as Android Documentation states. It provides access to app resources, assets, and so on. Because Activity extends from Context, we can use this as an argument in this Intent.

> You need to check whether the activity is defined in the manifest file. If that is not the case, your app will crash at runtime.

### Some extra

You can also put some data in your intent by using the `putExtra()` function. 
Let's see how it works: using the text from `EditText`, we will change the name of the user on the second screen.

![image](https://user-images.githubusercontent.com/63263301/202568872-c15eb241-e047-40cb-931b-9f0a1aeac0af.png)
 your first activity (the one where you have defined an intent), call the `putExtra()` function:
```kotlin
val intent = Intent(this, UserActivity::class.java)
intent.putExtra("userName", editText.text.toString())
startActivity(intent)
```

The first parameter in this function defines the name of the intent extra.  
You need to define it to find this data in the activity you launched. In the second activity, we type this:
```kotlin
name.text = intent.getStringExtra("userName")
```
In the getStringExtra(), we type in the name of our intent extra as a parameter. Done!  
We have received our String from the first activity and successfully assigned it to our TextView in the second activity.

> Functions like `getIntExtra()`, `getBooleanExtra()`, and others require you to set a default value,  
> which will be returned if there's no value associated with the requested key.  
> The default value should be specified as the second parameter. Example:` getIntExtra("number", 0)`; `getBooleanExtra("boolean", false)`
