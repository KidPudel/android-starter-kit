# How to change font?

we can change:  
1. Font itself, with `fontFamily` (font family is levels of the font (`Font` composable) e.g. Weight.thin, bold, etc.)
2. `fontSize`
3. `fontWeight`
4. `fontStyle` - e.g. italic


To set different styles withing the same `Text` composable, you have to use an `AnnotatedString`.  

Simular to `StringBuilder`.   

To apply different styles, use `withStyle` method, and inside that function create `Text` composable
