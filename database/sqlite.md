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
