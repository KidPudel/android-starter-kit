Fragmented KMM

Kmm is just a tool for writing mobile, your android knowledge will be gratefully helpful

Diagram kmm clean architecture 
![clean_architecture_kmm](https://user-images.githubusercontent.com/63263301/228532984-d26e4474-dc26-4e0c-b2fe-1370ebc17d11.png)

https://raw.githubusercontent.com/mitchtabian/KMM-Playground/test/extras/clean_architecture_kmm.png

# Learning
Learn via samples, just see how it implemented, kotlin kmm documentation for android developers
John O’Riley
Build !!


# Ideal way to build that is to use clean architecture 
Domain - shared
Data - shared
UseCases - pure kotlin

Framework specific layer
Presentation
Services

Kotlin specifics:

Cashing: SQL Delight
Networking: Ktor

Serialization: kotlix.serialization 
Dates: kotlinxdatetime

Ktor is better than retrofit?
Kotlin not only for building API, it’s also for consuming!!

What if i have something platform specific, but i want to share it?
`expect` `actual` pattern with KMM
Common directory
In common section for example declare a class using ‘expect’ Donn Felker
And then in android or ios side use ‘actual’

---

_If you want to cache with coreData (ios), and Room (android)
So you have `Database` class in shared, declare with `expect`
And in platform specific use `actual`_

---

So you can go both ways

---

**It’s a safer choice to share code that should be shared, and not share code that shouldn’t be shared**

---

For now, sharing viewmodels is not practical, 

