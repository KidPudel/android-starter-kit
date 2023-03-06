> Most information is taken from [android docs](https://developer.android.com/training/data-storage/room)  

The Room persistence library provides an **abstraction layer over SQLite** to allow fluent database access while harnessing the full power of SQLite. In particular, Room provides the following benefits:
- Compile-time verification of SQL queries.
- Convenience annotations that minimize repetitive and error-prone boilerplate code.
- Streamlined database migration paths.

# Setup

```groovy
dependencies {
    def room_version = "2.5.0"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"

    // To use Kotlin annotation processing tool (kapt)
    kapt "androidx.room:room-compiler:$room_version"
    // To use Kotlin Symbol Processing (KSP)
    ksp "androidx.room:room-compiler:$room_version"

    // optional - RxJava2 support for Room
    implementation "androidx.room:room-rxjava2:$room_version"

    // optional - RxJava3 support for Room
    implementation "androidx.room:room-rxjava3:$room_version"

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation "androidx.room:room-guava:$room_version"

    // optional - Test helpers
    testImplementation "androidx.room:room-testing:$room_version"

    // optional - Paging 3 Integration
    implementation "androidx.room:room-paging:$room_version"
}
```

# Room's major components
- The `Database` class that holds databases and serves as the main access point for underling connection to persisted data
- Data `Entities` that represents tables in DB (one instance of one entity is the row in the entity table)
- `Data access objects (DAO)` that provides methods that app can use to _query_, _update_, _insert_ and _delete_ in DB


> The database class _**provides your app with instances of the DAOs associated with that database**_.  
In turn, the _**app can use the DAOs to retrieve data from the database as instances of the associated data entity objects**_.  
The app can also _use the defined data entities **to update rows from the corresponding tables**, or to **create new rows for insertion**_.

![room_architecture](https://user-images.githubusercontent.com/63263301/223070854-12949a08-a445-4286-8e74-5930ddce46f2.png)  


# Simple implementation

DB with a single data entity and DAO.  

## Data entity
The following code defines `User` data entity. Each instance of the `User` represents a row in a `user` table in app's database

![image](https://user-images.githubusercontent.com/63263301/223077337-ba3a91c2-16f7-40fe-a002-02289d7ee97a.png)


```kotlin
@Entity
data class User(
    @PrimaryKey val id: Int
    @ColumnInfo(name = "user_name") val userName: String?
    @ColumnInfo(name = "nickname") val nickname: String?
)
```

## Data access object (DAO)
The `UserDao` provides methods that the rest of the app can use to interact with data in `user` table

```kotlin
@Dto
interface UserDto {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>
    
    @Query("SELECT * FROM user WHERE id IN (:usersId)")
    fun getAllById(usersId: IntArray): List<User>
    
    @Query("SELECT * FROM user WHERE user_name LIKE :name AND nickname LIKE :nickname LIMIT 1")
    fun findByName(name: String, nickname: String): User
    
    @Insert
    fun insertAll(vararg users: User)
    
    @Delete
    fun deleteUser(user: User)
}
```

## Database
the following `AppDatabase` class defined to holds the database.  
`AppDatabase` **defines database configuration** and **serves as the app's main access to the persisted data**.

`AppDatabase` class must sutisfy following conditions:
- class must be annotated with `@Database` annotation that includes array of entites that list data entities associated with database.
- class must be abstract and inherit from RoomDatabase
- for each DAO that is associatied with database, database must define abstaract method that has no arguments and returns an instance of DAO class

```kotlin
@Database(entities = [User::class], version = 1)
abstact class AppDatabase : RoomDatabase() {
    abstact fun userDao: UserDao()
}
```
