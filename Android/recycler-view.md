# Basics

Recycler view is the List view  
<img src="https://user-images.githubusercontent.com/63263301/206408008-0bb9a587-50b7-42d1-b0b4-1715a858db39.png" alt="drawing" width="300"/>  

But it recycles views that out of the screen, and generates it back as needed, it **has performance benefits**.

# Include in the project

to anable `RecyclerView`, include in `build.gradle` following dependencies:
```kotlin
dependencies {
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    // For control over item selection of both touch and mouse driven selection
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
}
```

# Implement it

### To create `RecyclerView` we need to define it in our layout:

```xml
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@id/textView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"/>
```

### next we need to create item layout (how item will look)

![image](https://user-images.githubusercontent.com/63263301/206417526-be7430db-e20d-4c3d-8bdf-0f71122baef8.png)

### next step is to create data model for items

```kotlin
data class TodoModel(
    val goal: String,
    val isDone: Boolean
)
```

### next step is to create adapter

Adapter is used for:
- creating view items
- setting a content to an item

> Every adapter has view holder, inner class

`ViewHolder` is used to _hold a view_ for particular item

so it has tree functions:
- `OnCreateViewHolder` - for an item appearance
- `OnBindViewHolder` - set content to an item view
- `getItemCount` - how many items is displayed

