# How to think about inflation?

u can think of it as a way to get _members of layout_ to our code.  

To populate very few items, we can embed or inflate them on runtime.

```kotlin
var itemsLayout: LinearLayout = findViewById(R.id.layout_items)
val view = layoutInflater.inflate(R.layout.item_child, null)
val tvItem: TextView = view.findViewById(R.id.tv_item) // Here you can access all views inside your child layout and assign them values
itemsLayout.addView(view)
```

**LayoutInflater** - **Instantiates a layout XML** file **into** its corresponding **View objects**

## What can u do?
 
 for example inflate one layout in another at Runtime using Kotlin in Android.  
