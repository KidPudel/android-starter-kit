class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            var textFieldState by remember {
                mutableStateOf("")
            }
            val coroutineScope = rememberCoroutineScope()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    TextField(
                        value = textFieldState,
                        onValueChange = {
                            textFieldState = it
                        },
                        enabled = true,
                        singleLine = true,
                        placeholder = {
                            Text(text = "My name is..")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.
                            showSnackbar(message = "Hello, $textFieldState" )
                        }
                    }) {
                        Text("Greet")
                    }
                }
            }
        }
    }
}
