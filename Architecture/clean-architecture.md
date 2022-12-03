![205075360-09dbf829-3c0d-40ca-9b34-cf0c2ed59aa5](https://user-images.githubusercontent.com/63263301/205435613-daebb4e3-d541-48de-b61a-c08041aedafa.png)

It for example consists of [MVVM](https://github.com/KidPudel/android-starter-kit/new/main/Architecture.MVVM.md)

## Inside

We have tree _layers_:
1. Presentation (UI <-> `ViewModel`)
2. Domain (`UseCases` <- `Entities`)
3. Data (`Repositories` <-> (DataBase, Web))


`ViewModel` is binding _data_ with _view_.  

`UseCases` handle _buisnes logic_ (some part handle `ViewModel` and some specific to _data_ - `Repositories`  


<span style="color: red;">text</span>
