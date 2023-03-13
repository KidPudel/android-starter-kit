# Back stack
A task is a collection of activities that user interact with when trying to do something in the app. 
Those activities are arranged in the stack - **called back stack** - in the order in which each activity is opened.

# NavBackStackEntry
NavBackStackEntry - **representation of an entry in the back stack** of a `androidx.navigation.NavController`. 
_The Lifecycle, ViewModelStore, and SavedStateRegistry provided via this object_ are _valid for the lifetime of this destination on the back stack_:   
when this destination is _popped off the back stack_, the _lifecycle will be destroyed_, _state will no longer be saved_, and _ViewModels will be cleared_.
