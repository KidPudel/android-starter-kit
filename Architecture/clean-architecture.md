![205075360-09dbf829-3c0d-40ca-9b34-cf0c2ed59aa5](https://user-images.githubusercontent.com/63263301/205435613-daebb4e3-d541-48de-b61a-c08041aedafa.png)

It for example consists of [MVVM](https://github.com/KidPudel/android-starter-kit/blob/main/Architecture/MVVM.md)

## Inside

### We have tree _layers_:
1. Presentation (UI <-> `ViewModel`)
2. Domain (`UseCases` <- `Entities`)
3. Data (`Repositories` <-> (DataBase, Web))

### Knowledge between layers:
1. Presentation - knows about domain
2. Domain - doesn't know about other layers. get repository from data layer, but via dependency injection
3. Data - knows about domain


`ViewModel` is binding _data_ with _view_.  

`UseCases` handle _buisnes logic_ (some part handle `ViewModel` and some specific to _data_ - `Repositories`  

## Presentation
- View
- ViewModel

## Domain
- UseCase (**for some specific operation**, _like convert to roman from decimal, search in list, get some info from our data or api_)
- Model (is adjusted dto)
- Interface for repository

## Data
- Repository (handle data specific things like call API methods from remote, implement interface from domain)
- remote (also define interface of routes we want to access from API)
  - dto (data transfer object, from our JSON response of API (_**use plugin to convert**_))
