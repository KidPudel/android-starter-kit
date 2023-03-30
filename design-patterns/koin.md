Dependencies for Koin:  

```gradle
implementation "io.insert-koin:koin-android:3.2.0-beta-1"
implementation "io.insert-koin:koin-androidx-navigation:3.2.0-beta-1"
implementation "io.insert-koin:koin-androidx-compose:3.2.0-beta-1"
testImplementation "io.insert-koin:koin-test-junit4:3.2.0-beta-1"
```

So we have our viewmodel and repository
```kotlin
class MainViewModel(
    private val repository: INetworkRepository
): ViewModel() {
    
    init {
        doNetworkCall()
    }
    private fun doNetworkCall() {
        repository.doNetworkCall()
    }
}
```
```kotlin
class NetworkRepository(
    private val api: MyApi
) : INetworkRepository {
    override fun doNetworkCall() {
        // network call..
    }
}
```
But how koin will actually know what to provide?? yes, with module as always.
We will create `AppModule.kt` file
```kotlin
appModule = module {
    // singleton
    single {
        Retrofit.Builder()
            .baseUrl("https:/google.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }
    
    // specify that we get implementation when we have interface of repository
    single<IMainRepository> {
        // main repository have MyApi in parameters, so we already have our MyApi implementation above, so just pass it "get()"
        MainRepository(get())
    }
    
    // provide new instance every time
    factory {
    }
    
    // to inject a view model simpy put viewModel
    viewModel {
        MainViewModel(get())
    }
}
```

And to initialize koin:

```kotlin
class MyApp : Application() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            // if we want to inject an application context
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}
```

Now to actually inject it:

for XML project:
```kotlin
class MainActivity: ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()
}
```

for Compose project:
```kotlin
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IggyAppTheme {
                val viewModel = getViewModel<MainViewModel>()
            }
        }
    }
}
```

Now to access another dependency in activity:
```kotlin
class MainActivity: ComponentActivity() {
    val api = get<MyApi>()
    // or lazy if we want to do something at usage time (to call something)
    private api by inject<MyApi>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IggyAppTheme {
                val viewModel = getViewModel<MainViewModel>()
            }
        }
    }
}
```

# Scope dependencies
To scope our dependencies so they don't live as long as the application does.  
For example create an additional module to make dependencies live as long as our activity does
```kotlin
activityModule = module {
    scope<MainActivity> {
        scoped<ISomeViewModel>{
            SomeViewModel()
        }
    }
}
```

And now we need to add this module to our modules
```kotlin
class MyApp : Application() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules(appModule, activityModule)
        }
    }
}
```

after that we need to actually inject that in our activity:

```kotlin
class MainActivity: ComponentActivity(), AndroidScopeComponent {
    private val scope: Scope by activityScope()
    
    private val viewModel by inject<MainViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IggyAppTheme {
                val viewModel = getViewModel<MainViewModel>()
            }
        }
    }
}
```

# Qualifiers

To distinguish dependencies of the same type
```kotlin
activityModule = module {
    scope<MainActivity> {
        scoped(quailfier = named("hello")) { "Hello" }
        scoped(quailfier = named("bye")) { "Bye" }
    }
}
```

```kotlin
class MainActivity: ComponentActivity(), AndroidScopeComponent {
    private val scope: Scope by activityScope()
    // kind of viewmodel scope, keep even when activity rotated
    private val scope: Scope by activityRetainedScope()
    
    private val hello by inject<String>(named("hello"))
    private val bye by inject<String>(named("bye"))
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IggyAppTheme {
                val viewModel = getViewModel<MainViewModel>()
            }
        }
    }
}
```
