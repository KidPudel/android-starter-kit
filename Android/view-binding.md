Views - buttons, texts, editTexts

layouts contain views

# View binding

Once enabled in gradle it generates a binding class for each XML layout file present in that module
**An instance of a binding class contains direct references to all views that have an ID in the corresponding layout**


## Use view binding in activities

**_Steps:_**
- create an instance of the view binding class
- call the static method ```inflate()``` included in the _automatically_ generated binding class. This creates an **object** of the binding class for the activity to use
- get a reference to the root view by ```root```
- Pass the root to the ```setContentView```to make the active view on the screen

**_Now u can use the instance of the binding class to reference  any of the views_**


## a little about data binding  
**Data binding** allows us to bind UI element of the layout to the data sourse in your code using declarative format rather than programmatically


```kotlin
// declare an instance of the binding class
    // lateint to ensure that its going to be initialized
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // create an object of the binding class
        binding = ActivityMainBinding.inflate(layoutInflater)
        // root view
        val view = binding.root
        // make the view active on the screen
        setContentView(view)
```

```kotlin
// create an instance of viewmodel
val calculatorViewModel: CalculatorViewModel by viewModels()
// View.tOnClickListener is universal listener
// logic for what is the instructions of the listener
val digitalListener = View.OnClickListener { view -> digitPressed(view as Button) }
val operationListener = View.OnClickListener {view -> operationPressed(view as Button)}
```
