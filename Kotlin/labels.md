What is a label? Technically, it's just an identifier with the @ sign at the end, for example: ``loop@, kotlin@``.  
You can use almost any word with it, apart from the reserved words in Kotlin.  
What do you need labels for? Well, you can add your label to a loop:
```kotlin
loop@ for (i in 1..100) {
    for (j in 1..100) {
        if (...) break@loop
    }
}
```
A break qualified with a label jumps to the execution point **right after the loop marked with that label**. A continue proceeds to the next iteration of that loop.
