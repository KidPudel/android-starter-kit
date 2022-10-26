## MutalbeList

```kotlin
fun main() {
    val numbers = MutableList(100) { index ->
        if (index == 0 || index == 9 || index == 99) index + 1 else 0
    }

    // do not touch the lines below 
    println(numbers.joinToString())
}
```

maxByOrNull -> returns the greatest number after checking with selector

```kotlin
3
100
50
50
10
20
10
val highestTax = incomes.maxByOrNull { it * (taxes[incomes.indexOf(it)] * 0.01) } // returns 100
```


maxOfOrNull -> returns the greatest number of selector results
```kotlin
3
100
50
50
10
20
10
val highestTax = incomes.maxOfOrNull { it * (taxes[incomes.indexOf(it)] * 0.01) } // returns 10.0
```

also we can get an index of it
```kotlin
val highestTax = incomes.indices.maxByOrNull { it * (taxes[incomes.indexOf(it)] * 0.01) } // returns 100
```
