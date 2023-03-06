# SQLite is a local storage very light SQL

# Why local storage?
- Access fast
- Offline Mode

# SQLite is
- for one person, Small size (300 kb)
- embeded storage (no installation, it's built in)
- ACID  Compliant. Can handle sudden power failure  (**A**tomic, **C**onsistent, **I**solation, **D**urability)
- close the database connectiona
- read only queries can be done simultaneous


# Data types
- NULL - null value
- INTEGER - single integer stored in 1 2 3 4 6 or 8 bytes
- REAL - floating point value
- TEXT - text string
- !(not advisible) BLOB - binary large object (for big objects like JPG, MP3, MOV)  
  **Instead, save it in the directory and put the text of that link in the database** 

# Classes
- `SQLiteDatabase` - `db: SQLiteDatabase = this.getWritableDatabase()`
- `SQLiteOpenHelper`
  - `onCreate`, `onUpdate`
  - Custom written methods that perform CRUD, such as `getAllCustomers()`

# Methods
- `openOrCreateDatabase()` - open an existing DB or create one in the application data area
  ```kotlin
  val myDatabase: SQLiteDatabase = openOrCreateDatabase("customers.db", SQLiteDatabase.CREATE_IF_NECESSARY, null)
  ```

## Creating tables
- Create a string containg the SQLite table definition
  ```kotlin
  val queryString: String = "CREATE TABLE CUSTOMER_TABLE (id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE INTEGER)
  ```
- Use the `execSQL()` method to execute it
  ```kotlin
  myDatabase.execSQL(queryString)
  ```

# Cursor (result set)
- Is the result set from the query operation
- We can loop through the cursor items to process each line of a search result

# Content values
Is an associated array or Hashmap
```kotlin
cv: ContentValues = ContentValues()
cv.put("student_id", 1)
cv.put("student_name", "Jeff")
cv.put("student_grade", 9)

val studentGrade = cv.getInt("student_grade")
val studentName = cv.getString("student_name")
```
