# Scoped functions
we have 5 scoped functions: let, with, run, appy, also

1. `let` - used to configute + manipulate on the object via getting `it` (object) and returning the result of the lambda

-----

2. `with` - simply reads: "with *this oject* do this {}", you just get the convenient scope of the object `this` convenient use when you don't need to use the returned result

-----
 
3. `run` - use to configure the object by just getting the convinient scope of the object `this` and returns the result of a lambda istead of the long repetition, u just call object's methods like `build` and returns the result

-----

4. `apply` - read: "apply this on the object" just get the convinient scope of the object `this`, but returns the object itself -> meaning, use it to _apply_ something on it

-----

5. `also` - gives you the object as it, and lets you apply on it (like side effect, because you can conveniently use the object as it and put somewhere), returns the object itself


`apply` is when you want to setup and return the object itself, `also` is when you want to do some side things on the object and return the object itself

## return
- lambda result - `let`, `with`, `run`
- object - `apply`, `also`
