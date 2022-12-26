# What is Ktor?

Ktor is a framework to easily build connected applications – web applications, HTTP services, mobile and browser applications using kotlin 💘

# Basics

To create ktor project you have 2 options:
- Install plugin in IDEA
- Or go to https://start.ktor.io

> Almost everything (and everything **that you create**) in Ktor is an extention **function**

For example, to define a new route, you need:  
1. Create a function with `Route.` class
2. Choose what http method you want
3. Choose route
4. Add it to route configuration to enable it in project

> Route is the class describes a **node in a route tree**

> call is the **current** call for the context (http method)
