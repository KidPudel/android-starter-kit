![image](https://user-images.githubusercontent.com/63263301/205435737-46cee4cf-4582-4bfb-9738-8dc314456bd4.png)

## specifics
UI-related actions like opening new activities or showing dialogs are triggered **from the view (an activity or fragment), not from a ViewModel**.  
The ViewModel doesn't have a reference to the view to prevent leaks and keep the presentation layer "reactive".
