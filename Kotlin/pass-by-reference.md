Java passes a copy (pass-by-value) of the reference (pass-by-reference)

```kotlin
void doSomething(List<Integer> x) {
  x = new ArrayList<Integer>()
}

List<Integer> x = Arrays.asList(1, 2, 3);
doSomething(x);
x.length() == 3
```
You're **copying** the **reference** to the list, so "reassigning it" will take no effect in the real object.
But since you're referring to the same object, modifying its **inner content will affect the outer object**.

copy of the reference when changing outer, changing a reference, but since it is a copy it will take on affect
but if look up for reference **inside** we can change it
