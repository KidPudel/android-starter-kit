# Overview
allows you to work with asynchronous data streams using the concept of Observables, which emit items over time.  
You can then apply various operators to manipulate and transform these streams of data easily.

---

# Key components
- **Observable**: Represents a _stream of data that can emit items over time_. It can emit data, error, or completion events.
- **Observer**: _Listens to the emissions from an Observable and reacts to the data_, error, or completion events.
- **Subscriber**: An alternative to Observer, which provides additional methods to handle subscription and unsubscription.
- **Operators**: Allow you to transform, filter, and combine Observables to create more complex data flows.
- **Schedulers**: Define the threading context in which Observables emit items and Observers consume them. They are used to control the execution thread for various operations.
- **Disposable**: Represents a resource that needs to be disposed of when it's no longer needed, preventing memory leaks.


# Setting up
```graddle
dependencies {
    implementation "io.reactivex.rxjava3:rxjava:3.x.x"
    implementation "io.reactivex.rxjava3:rxandroid:3.x.x"
}
```

# Basic usage

1. Creating Observables
You can create an Observable using various methods such as `Observable.create`, `Observable.fromIterable`, `Observable.just`, etc. For example:
