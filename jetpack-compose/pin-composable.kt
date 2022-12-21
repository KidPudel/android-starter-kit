class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val robRon = painterResource(id = R.drawable.rob_ron)
            val robRonTitle = "Rob & Ron"
            val robRonDescription = "Rob & Ron"
            val finn = painterResource(id = R.drawable.i_am_a_sword)
            val finnTitle = "I am a sword"
            val finnDescription = "I am a sword"
            Column() {
                Pin(
                    painter = robRon,
                    title = robRonTitle,
                    contentDescription = robRonDescription
                )
                Pin(
                    painter = finn,
                    title = finnTitle,
                    contentDescription = finnDescription
                )
            }

        }
    }
}

@Composable
fun Pin(
    painter: Painter,
    title: String,
    contentDescription: String
) {
    Card(
        modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 5.dp
    ) {
        Box() {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .matchParentSize()
                .background(brush = Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color.Black),
                startY = 500f
            )))
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(text = title, color = Color.White, fontSize = 20.sp)
            }

        }
    }
}
