Using **Fragments** is one of several options for making an application that **will work on phones in a vertical or horizontal orientation,  
as well as tablets with larger displays**.

> A **Fragment** is like an Activity that _can be **connected to different parts** of an application_.
However, it is important to understand that **they are not the same**. This means that a **Fragment cannot exist unless it's embedded in an Activity**.

![image](https://user-images.githubusercontent.com/63263301/202905098-978e397d-2381-4050-9f59-9c049ca243f4.png)


Fragments are used in **Single Activity Architecture** to create Navigation and save state. 
They can also **control how information is displayed** on a device based on its screen size. _This means that information can be placed on a single tablet screen that would need to be split across several screens on a phone_.  
**This type of template is called Primary/Detail Flow** (renamed from Master/Detail Flow), and you can see it in use by looking at the version of Telegram messenger designed for tablets.

tablet:
![image](https://user-images.githubusercontent.com/63263301/202905488-14173b95-98a5-4b8c-91c2-b117da2f81ab.png)
phone:
![image](https://user-images.githubusercontent.com/63263301/202905585-b7c45793-c114-4707-95ed-5a8f48a8e07a.png)

in project it wold look like:  
![image](https://user-images.githubusercontent.com/63263301/202905882-d025aaf3-e6d4-4e87-9eb1-64abb631a6cc.png)




**what is container?** - This is the **parent view** that the **fragment's UI should be attached to**.  
The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view.


**LayoutInflater** - **Instantiates a layout XML **file **into** its corresponding **View objects**
