# Always look for method that matches predictable / pattert


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

withIndex() wraps element into IndexedValue containing index of the value and the value itself
```kotlin
val highestTaxIndex = incomes.withIndex().maxByOrNull { it.value * (taxes[it.index] * 0.01) }.index // <- because its all wrapped in IndexedValue
```



**filter** returns a list containing only elements matching the given predicatable/pattern (not changing the list iteslf, just returns filtered/that is satisfied pattern)

**map** returns a list conatining results of applying the given transform function to each element in the original list

**apply** executes a ginven function and applies it right away



```kotlin
import java.lang.Exception
import kotlin.NumberFormatException

fun parseCardNumber(cardNumber: String): Long {
    val validSpaces: Boolean = cardNumber.count { it == ' ' } == 3
    val groups = cardNumber.split(' ')
    val rightGroups = groups.filter { it.length == 4 }.count() == 4
    if (!validSpaces || !rightGroups) {
        throw Exception("format is not right")
    }
    return cardNumber.filter { it != ' ' }.toLongOrNull() ?: throw NumberFormatException("Wrong format")
}
```

## Other useful methods

- `.joinToString(" ")`
- `.withIndex()` - wraps element in (index, value)
- `first()` - first element _that matches predictable_
- `startsWith()`
- `maxBy()` and `maxOf()`
- `count()` - amount of occurances
- `groupingBy().eachCount()` - returns a map with (Element, Number of occurances)
- `map {}` - returns a **list** containing results of applied function on the original map (collection)
- `forEach`
- `split()`


## Think

we need to find a road that leads to a TRUE Rome (from rome no ways are going)

we could go with: 
```kotlin
    roads.forEach {
        if (!roads.keys.contains(it.value)) {
            rome = it.value
        }
    }
```

but **think**, we are here seaching for specific value (discard  others), meaning we need to **_filter_** 

```kotlin
fun main(args: Array<String>) {
    val (citiesNumber, roadsNumber) = readLine().toString().split(" ").map { it.toInt() }

    val roads = mutableMapOf<Int, Int>()

    repeat(roadsNumber) {
        val (from, to) = readLine().toString().split(" ").map {it.toInt()}
        roads.put(from, to)
    }

    val trueRome = roads.values.filter { !roads.keys.contains(it) }

    println(trueRome)

}
```
