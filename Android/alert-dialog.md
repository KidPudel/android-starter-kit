# Simple AlertDialog

```kotlin
alertDialogButton.setOnClickListener {
    AlertDialog.Builder(this)
        .setTitle("Simple Alert Dialog!")
        .setMessage("Now you know how to use a new component!")
        .setPositiveButton(android.R.string.ok) { _, _ ->
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
        }
        .setNegativeButton(android.R.string.cancel, null)
        .show()
}
```
The `show()` method of the Builder will display our AlertDialog. This is what we will see after clicking the button:  

![player_6WAioGf2AX](https://user-images.githubusercontent.com/63263301/205080192-a0c08880-c60f-48e3-bdb5-0a291a70673d.png)


`set**Button` functions _**accept** button text and a DialogInterface.OnClickListener instance with a single abstract onClick method_.  
It receives the following parameters:  
- **`dialog`** (the dialog that received the click)  
- **`which`** (the button that was clicked or the position of the item clicked). 

When the user clicks on **any** button, the dialog gets dismissed automatically.  
Thus, if no more action needed, the listener could be null.  


## dialog in a variable
We can **create a dialog** using Builder's `create() `method, save it to a variable, and only then show it:  

```kotlin
val dialog = AlertDialog.Builder(this)
    .setTitle("Simple Alert Dialog!")
    .setMessage("Now you know how to use a new component!")
    .create()

alertDialogButton.setOnClickListener {
    dialog.show()
}
```
