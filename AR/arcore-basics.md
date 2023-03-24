ARCore is library created by google, that helps you to abstract a lot of hard thinks (like work with OpenGL)

The Sceneform API consists of two objects Scene and View:

## Scene and View
**The scenes represents an objects you are adding** to the view like 3D moddels of the solar system that you want to place.
**The view is where your scene will be drawn**, like your divice screen and where device is in the world

The renderer will drawn the scene from that perspective
The view ties to the android view system, and that's your hook into the app.  


As a developer we bu**ild our scene by defining the spatial relationships of objects**.  

To do this, Sceneform provides provides **SceneGraph API for defining the hierarchy of objects** and their spatial relationships

### Analogy
Android view hierarchy
 - but instead in 3D
 - instead of views - graph nodes

Each node contains information that sceneform needs to render and interact with  
Nodes can be added to other nodes (parent-child relationship)  
![image](https://user-images.githubusercontent.com/63263301/227622784-1d25785c-00d7-4cf1-9cdb-99202ac6453e.png)
