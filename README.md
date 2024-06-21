# Jonas_Ramanauskas_Caner_Celik_Design_Patterns_Final_Assignment

[Start Document](DesignPatterns2024_StartDocument.pdf)
<br>
[UML Diagram](UML_Updated_FINAL.png)

## Application Description

Welcome to our Farm Management Application. 
<br>
<br>
We've made farming easier with smart features. In our app, workers can handle more tasks efficiently, keeping track of workers and crops straightforward. Managers are alerted if workers need assistance, and resources and worker types are managed seamlessly. A single Farm Manager ensures smooth operations, making farming simpler and more organized

## Farm Management System

### Classes:

- **Manager**: Represents the manager of the farm. It interacts with observers (workers) and the farm itself.
- **Farm**: Represents the farm entity, which contains workers, crops, a tractor, and various fields (such as CornField, OatField, WheatField).
- **Worker**: Represents the workers on the farm, with attributes like timesWorked, hungerMeter, and sanityMeter. It can have different states like Sleeping, Eating, Working, and Idle.
- **State**: Represents different states a worker can be in, implementing the State design pattern.
- **Tractor**: Represents a tractor on the farm, which can load and unload crops.
- **Crop**, **CornCrop**, **OatCrop**, **WheatCrop**: Represent different types of crops, such as corn, oat, and wheat.
- **CropStorage**: Represents the storage for processed crops.
- **Observer** and **Subject**: Interfaces representing the Observer design pattern, facilitating communication between the Manager (subject) and workers (observers).

### Patterns:

- **Observer Pattern**: The Manager class acts as a subject, while the Worker class acts as observers. This pattern allows the Manager to notify workers about changes or events on the farm.
- **State Pattern**: The State class represents different states of a Worker. This pattern allows the Worker to change its behavior when its internal state changes, such as transitioning from Working to Sleeping based on conditions.
- **Decorator Pattern**: The WorkTypeDecorator class acts as a decorator for the Worker class, allowing additional behaviors (such as LoadTractor and UnloadTractor) to be added dynamically to the worker.


