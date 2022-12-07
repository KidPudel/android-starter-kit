# Basics

Recycler view is the list view:  
![image](https://user-images.githubusercontent.com/63263301/206232091-8b158ba1-34d9-47d5-9636-376de85a72a6.png)  

But it recycles the view items once it out of the screen, and creates them back as needed

**Therefore, `RecyclerView` is for performance**  

# Include to the project

```gradle
// Recycler view
implementation 'androidx.recyclerview:recyclerview:1.2.1'
```

# Create Recycler view

```xml
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintBottom_toTopOf="@id/textView"  />
```


then you need to create a simple layout (how your item will look)  

![image](https://user-images.githubusercontent.com/63263301/206235881-11b561f9-bb94-4e7a-bb32-3c18703d3fdd.png)  


The next step is to create an **adapter** for recycler views.   

The adapter's job is:
- To create a item views for our recycler view
- to assign a contents for that item view


our `TodoAdapter` must have inner class, `ViewHolder`

`ViewHolder` - is used for holding view for a particular item
- this class takes in constructor a view for our _current_ item
- this class inherits from `RecyclerView.ViewHolder(itemView)`

so far it looks something like that:  
```kotlin
class TodoAdapter {
    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}
```

Now we need a data that particular item is going to have (what it is going to consist of):  

```kotlin
data class Todo {
  val title: String
  val isDone: Boolean
}
```

now that we have a data, we need to pass a list of our data that recycler view is going to consist of:  
