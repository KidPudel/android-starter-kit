In jetpack compose navigation is handled by `NavHost`, which hosts navigation.

In the end, each screen will be a composable

`NavHost` take composables and replaces it when we want it

1. Create `NavConstroller` - we want to controll `NavHost` somehow
  ```kotlin
      val navController = rememberNavController()
  ```
  Creates a `NavHostController` that handles the adding of the `ComposeNavigator` and `DialogNavigator`.  
2. Create `NavController` composable
  ```kotlin
      NavHost(navController = navConstroller, startDestination = Screen.MainScreen.route (<- sealed class)) {
          // now you can define a composable
          composable(route = Screen.MainScreen.route) {
              MainScreen(navController = navController)
          }
      }
  ```
  Provides in place in the Compose hierarchy for self contained navigation to occur.  
  Once this is called, any Composable within the given `NavGraphBuilder` can be navigated to from the provided navController.  
  The builder passed into this method is remembered. This means that for this `NavHost`, the contents of the builder cannot be changed.  
  Params:
  - `navController` - the navController for this host
  - `startDestination` - the route for the start destination
  - `modifier` - The modifier to be applied to the layout.
  - `route` - the route for the graph
  - `builder` - the builder used to construct the graph

`startDestination` instead of navigation graph, it's a routing (routes witch is just a string) between pages

```kotlin
package com.example.adventure_time_characters.presentation.views

sealed class Screen(val route: String) {
    object CharacterListScreen : Screen(route = "character_list_screen")
    object CharacterDetailScreen : Screen(route = "character_detail_screen")
}
```


## Navigate
```kotlin
NavConstroller.navigate(route = Screen.DetailScreen.route + "/{$name}")
```
## Navigate with arguments
```kotlin
composable(route = Screen.DetailScreen.route + "/{name of the variable}", arguments = listOf(
      navArgument(name= "name of the variable",) {
        type = NavType.StringType
        defaultValue = "Iggy"
        nullable = true
    }
  )
) {
    // call it with agument
    DetailScreen(name = it.arguments?.getString(key = "name of the variable"))
}
```

sealed class doesn't allow inheritance from the outside of that class
