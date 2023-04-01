![205075360-09dbf829-3c0d-40ca-9b34-cf0c2ed59aa5](https://user-images.githubusercontent.com/63263301/205435613-daebb4e3-d541-48de-b61a-c08041aedafa.png)

It for example consists of [MVVM](https://github.com/KidPudel/android-starter-kit/blob/main/Architecture/MVVM.md)

## Inside

### Devide by features
For example translator app will have:
- translate feature
- text-to-speesh feature
- core: is just a stuff that is shared between features

instead each feature we have layers

### We have "tree" _layers_:
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

# 4 layers

- Presentation layer: This layer is responsible for rendering the user interface and handling user interactions. It typically includes React components and other UI-related code.
- Application layer: This layer contains the business logic of the application. It is responsible for coordinating data flow and processing, and for implementing the use cases of the system. It can include various modules and services that handle specific aspects of the application logic.
- Domain layer: This layer contains the core business logic of the application, independent of any specific implementation details. It defines the entities, value objects, and business rules of the system.
- Data layer: This layer is responsible for managing the persistence and retrieval of data. It can include various repositories, data mappers, and other infrastructure-related code.

## Application layer vs Domain layer

The main difference between the `application layer` and the `domain layer` is that the **_application layer is responsible for managing the overall behavior and flow of the system_**, while the **_domain layer is responsible for implementing the core business logic of the system_**. The application layer interacts with the user interface and other external systems, while the domain layer is focused on the internal behavior of the system.

# UseCase vs Service
In software development, the terms "services" and "use cases" can have different meanings depending on the context and the specific development methodology being used. However, in general, the main difference between these two concepts is that **_services are typically responsible for performing specific operations or tasks in the system_**, while **_use cases represent the goals or actions that a user or actor can perform_**.

Here are some more details about each concept:

- Services: In software engineering, services are usually modules or components that provide specific functionality to the rest of the system. Services can be thought of as self-contained pieces of logic that operate on data or perform specific operations. Examples of services might include an authentication service, a payment processing service, or a data validation service.

- Use cases: A use case is a description of a user's interaction with the system in order to achieve a particular goal or perform a specific action. Use cases typically describe a sequence of steps or interactions between the user and the system, and are often used to design or specify the behavior of a software system. Examples of use cases might include "place an order", "create a new account", or "search for a product".
