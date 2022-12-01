![69536839-9f4c8e80-0fa0-11ea-85ee-d7823e5a46b0](https://user-images.githubusercontent.com/63263301/205075360-09dbf829-3c0d-40ca-9b34-cf0c2ed59aa5.png)

## specifics
UI-related actions like opening new activities or showing dialogs are triggered **from the view (an activity or fragment), not from a ViewModel**.  
The ViewModel doesn't have a reference to the view to prevent leaks and keep the presentation layer "reactive".
