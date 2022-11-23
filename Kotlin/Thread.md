Process is the self-contained unit of execution that has all thing it needs.
**In short**, the process is the container of the threads, all the necessities for their work, and their shared resources.


![image](https://user-images.githubusercontent.com/63263301/198831110-2de21967-ccb1-4d12-a420-5f99cc6093f5.png)

![image](https://user-images.githubusercontent.com/63263301/198831115-dda1753a-4bce-4290-9c85-85b9f3d1404c.png)


**Thread of execution is a stream of instructions inside a process** that can be scheduled and run independently
**Each thread has its executor**, and this executor can perform only one thread at a time
Several threads inside the same process can run concurrently or in parallel.

Workers in a pizzeria play a role of thread executors. Tasks that workers accomplish are the threads in a pizzeria (process)

```kotlin
val thread: Thread = Thread.currentThread() // the current thread
```


# Coroutines

it is similar to threading, except coroutines are even more light-weight. Also, the library (kotlinx.coroutines) is specially designed to make the code similar on different platforms.

Let's go back to the analogy with a firm. We can compare a coroutine with a usual task that can be done by an employee. First, the task is created, and then assigned to a person. When the person is ready, they decide what to do: start handling the task or transfer it to another person. When someone starts working on the task, there's no guarantee it will be done right away. For example, say the task was to call another department, and the person called but nobody answered. Then that person can "suspend" the task, that is, postpone it for a while. During this gap, the person can take another task and start working on it, or, if there are no more assigned tasks, they can take a break.

```kotlin
import kotlinx.coroutines.delay

suspend fun main() {
    println("Hello")
    delay(500)
    println("World!")
}
```

- A thread is a sequence of instructions that can be performed in parallel.
- The class ```Thread``` allows working with a thread as an object.
- You can obtain a reference to the currently executing thread using the static method ```Thread.currentThread()```.
- Any thread has a name, an identifier, a priority, as well as isAlive and isDaemon characteristics, which you can change.
