# Dependency injection - what is ?

Dependency injection (DI) is a **technique** widely used in programming and well suited to Android development.  
By following the principles of DI, you lay the groundwork for a good app architecture.  

Dependency injection **is basically providing the objects that an object needs (its dependencies) instead of having it construct them itself.**   


# What it for?  

It's a very useful technique for testing, since it allows dependencies to be mocked or stubbed out.  


Implementing dependency injection provides you with the following advantages: 
- Reusability of code.
- Ease of refactoring.
- Ease of testing.


# How to use it?  

Dependencies can be injected into objects by many means (such **as constructor injection or setter injection**).   
One can even **use specialized dependency injection frameworks** (e.g. Spring) to do that, but they certainly **aren't required**.   
You don't need those frameworks to have dependency injection.   
**Instantiating and passing objects (dependencies) explicitly is just as good an injection as injection by framework.**
