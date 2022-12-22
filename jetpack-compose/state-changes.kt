class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorfulBox()

        }
    }
}

@Composable
fun ColorfulBox() {
    val color = remember { mutableStateOf(Color.Yellow) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.25f)
                .background(color = color.value)
                .clickable {
                    color.value = Color(
                        Random.nextInt(),
                        Random.nextInt(),
                        Random.nextInt()
                    )
                }
        )
    }
}
