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


# Fundamentals

> Fundamental concepts illustrate how ARCore enables experiences that can make virtual content appear to rest on real surfaces or be attached to real world locations.

## [Motion tracking](https://developers.google.com/ar/develop/fundamentals#motion_tracking)
allows ARCore to determine *where the device is in the world*. ARCore uses simultaneous localization and mapping (SLAM) to detect visually distinct features called ***feature points** and uses these points to calculate the device's position and orientation* in relation to the world.

## [Environmental understanding](https://developers.google.com/ar/develop/fundamentals#environmental_understanding)
allows ARCore to *detect horizontal and vertical planes in the real world, **such as tables or walls***. ARCore can also create depth maps, which can be used to make virtual objects accurately collide with observed surfaces or appear in front of or behind real-world objects. ARCore can also detect lighting information, which lets developers light virtual objects under the same conditions as the environment around them.

## [Depth understanding](https://developers.google.com/ar/develop/fundamentals#depth_understanding)
ARCore can create depth maps, images that contain *data about the distance between surfaces from a given point*, using the main RGB camera from a supported device. You can use the information provided by a depth map to enable **immersive and realistic user experiences**, such as making virtual objects accurately collide with observed surfaces, or making them appear in front of or behind real world objects.

## [Light estimation](https://developers.google.com/ar/develop/fundamentals#light_estimation)
ARCore can detect information about the lighting of its environment and *provide you with the average intensity and color correction of a given camera image*. This information lets you **light your virtual objects under the same conditions as the environment around them, *increasing the sense of realism***.

## [User interaction](https://developers.google.com/ar/develop/fundamentals#user_interaction)
allows users **_to select or interact with virtual objects in the environment using hit testing_**.   


## [Oriented points](https://developers.google.com/ar/develop/fundamentals#oriented_points)
Let's you place virtual objects on angled surfaces using oriented points.

## [Anchors and trackable](https://developers.google.com/ar/develop/fundamentals#anchors_and_trackables)
ensures that virtual objects **_remain in the same position relative to the real world even as the device moves around_**.

## [Augmented Images](https://developers.google.com/ar/develop/fundamentals#augmented_images)
Lets app to interact with 2D images like movie poster or billboard.  
images can be compiled offline to create image database or images can be added in real life from the device


## [Sharing](https://developers.google.com/ar/develop/fundamentals#sharing)
Create collaborative or multiplayer apps for Android and iOS devices.  
One device send an anchor and nearby feature points to the cloud for hosting.  
Anchors could be shared between either Android or iOS devices in the same environment, therefore rendering of 3D object attached to the anchor appears of both devices simultaneously.


# API's

- SceneGraph API (parent-child)
- Depth API
- HitTest API (select or interact with virtual objects in the environment)
- Cloud Anchor API (lets you create collaborative or multiplayer apps for Android and iOS devices.)
