# Quik theory
Using **Fragments** is one of several options for making an application that **will work on phones in a vertical or horizontal orientation,  
as well as tablets with larger displays**.

> A **Fragment** is like an Activity that _can be **connected to different parts** of an application_.
However, it is important to understand that **they are not the same**. This means that a **Fragment cannot exist unless it's embedded in an Activity**.

![image](https://user-images.githubusercontent.com/63263301/202905098-978e397d-2381-4050-9f59-9c049ca243f4.png)


Fragments are used in **Single Activity Architecture** to create Navigation and save state. 
They can also **control how information is displayed** on a device based on its screen size. _This means that information can be placed on a single tablet screen that would need to be split across several screens on a phone_.  
**This type of template is called Primary/Detail Flow** (renamed from Master/Detail Flow), and you can see it in use by looking at the version of Telegram messenger designed for tablets.

### Tablet:   
<div style= "text-align: left;"><img src="https://user-images.githubusercontent.com/63263301/202905488-14173b95-98a5-4b8c-91c2-b117da2f81ab.png" width="620" height="520"/></div>  

### Phone: 
<div style= "text-align: left;"><img src="https://user-images.githubusercontent.com/63263301/202905585-b7c45793-c114-4707-95ed-5a8f48a8e07a.png" width="620" height="520"/></div>

### in project it wold look like:  
![image](https://user-images.githubusercontent.com/63263301/202905882-d025aaf3-e6d4-4e87-9eb1-64abb631a6cc.png)


**what is container?** - This is the **parent view** that the **fragment's UI should be attached to**.  
The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view.



# Fragment lifecycle

Since Fragments are closely **intertwined** with Activities, their lifecycles are also **intertwined**.  
The lifecycle methods that either aren't present in Activities or that have the same name but perform a different role are detailed below:

- `onAttach()` is called when a **Fragment is first attached to its Activity**.
- `onCreate()` is very **similar to the onCreate() Activity** method, but it **doesn't have access to the UI in Fragments**.
- `onCreateView()` **creates the Fragment View**. Its only recommended use is **to [inflate](https://github.com/KidPudel/android-starter-kit/blob/main/Android/inflate.md) the layout**.
- `onViewCreated()` is called immediately after onCreateView(). It is recommended to use it **for logic that works with the View that is returned**.
- `onDestroyView()` is a **method that reports** that the **View** created in onCreateView() is **no longer available**.
- `onDetach()` detaches a Fragment from its associated Activity.

The onStart(), onStop(), onResume(), and onPause() lifecycle callbacks are all identical to the ones inside Activity.

-> [activity lifecycle](https://github.com/KidPudel/android-starter-kit/blob/main/Android/activity.md) <-

# Fragment manager
Fragments inherit from the `androidx.fragment.app.Fragment`.  
There are two methods in `FragmentManager` that can be used to find Fragments associated with an Activity.
These are `findFragmentById(int id)` and `findFragmentByTag(String tag)`.
_If you want to perform any operations on Fragments, be it replacing, deleting, or hiding them, **you need to use the special `androidx.app.FragmentTransaction class`**._

The main methods of the `FragmentTransaction` class are below:
- `add()` adds the Fragment to the Activity.
- `remove()` remove the Fragment from the Activity.
- `replace()` replaces one Fragment with another.
- `hide()` hide the Fragment on the screen.
- `show()` shows the Fragment on the screen.

it's **important** to remember that **you must call the `commit()`** method **at the end of every transaction**!

# How to use Fragments?

1. Start by creating a **.xml file to _contain_ the markup for our Fragment**
2. Import and inherit from Fragment class
   - ```kotlin 
     import androidx.fragment.app.Fragment
     ```
   - ```kotlin 
     class YourFragment : Fragment() {
     }
     ```
3. [inflate](https://github.com/KidPudel/android-starter-kit/blob/main/Android/inflate.md) some .xml markup within the Fragment.
 With an Activity, this is done via the `setContentView()`, but with Fragments, **we need to return our View from `onCreateView()` instead**.
 **Here we receive a `LayoutInflater`** that can turn an XML layout resource into a View hierarchy, a parent container, and a saved state.
   - We invoke the `LayoutInflater.inflate` method, passing our **layout resource ID (fragment layout)**, **parent container (main layout that fragment will be attached)**, and **false**, meaning "no, I don't want you to attach a newly created hierarchy to the container." `FragmentManager` will do this **on its own**.
   - ```kotlin
     override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
     ): View? =
      inflater.inflate(R.layout.xml_document_name, container, false)
      ```
4. in the .xml layout for MainActivity, we add a `FrameLayout` **that will be a container (main layout) for Fragments**. After this, in the onCreate()method, you need to add the following check:
   ```kotlin
   setContentView(R.layout.activity_main)
   
   if (savedInstanceState == null) {
       supportFragmentManager.beginTransaction()
           .add(R.id.your_container_id, DefaultFragment())
           .commit()
   }
   ```
   You should also **create a default Fragment in this block** of code to **avoid the possibility of entering a blank screen**. 
   A `savedInstanceState` check is **needed**, too. This allows us to avoid a situation where our Activity gets recreated, and Fragments are restored, **but we forcibly add our Fragment.**
5. Once the default Fragment has been added, any other Fragments must be added using the replace() method and placed in the back stack via addToBackStack(). [what is back stack?](https://github.com/KidPudel/android-starter-kit/blob/main/Android/task-and-back-stack.md)
   **This will make it possible** to go back either by pressing "back" button or by calling `popBackStack()` on `FragmentManager`.
   ```kotlin
   supportFragmentManager.beginTransaction()
    .replace(R.id.your_container_id, YourFragment())
    .addToBackStack(null)
    .commit()
    ```
