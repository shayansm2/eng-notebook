# Design Patterns

Design Pattern Resources:
- Head First Design Pattern
- [Design Patterns and Refactoring](https://sourcemaking.com/design_patterns)

Solid Principles Resources:
- Agile Software Development
- Clean Architecture

# Object Oriented
## OOP Basics

1. Abstraction → implementation of private/protected methods inside objects
2. Encapsulation → private/protected methods/variables + setters
3. Polymorphism →interface and abstract classes
4. Inheritance → subclass and super-classes

## OOP Principles

1. Encapsulate what varies: 
    
    take the parts that vary and encapsulate them, so that later you can alter or extend the parts that vary without affecting those that don’t.
    
    ![Untitled](static/Untitled.png)
    
2. composition over inheritance
    
    HAS-A can be better than IS-A
    
    composition is more dynamic than inheritance. so when you want dynamic behaviour (changing in runtime) use composition. when you want static behaviour use inheritance
    
    ![Untitled](static/Untitled%201.png)
    
3. program to interface, not to implement
    
    apply polymorphism
    
    ![Untitled](static/Untitled%202.png)
    
4. loosely coupled designs →flexible OO systems which can handle changes
    
    ![Untitled](static/Untitled%203.png)
    
# SOLID Principles
## SRP
Responsibility: reason for a change

## OCP
## DIP
# Design Patterns

#### Strategy:
separate the part of the code that varies, encapsulate them and use dependency injection for setting the strategy 

![Untitled](static/Untitled%205.png)

#### Observer:
some objects are observers (listeners) which will get notified when the subject is changed → kinda like dirty queues which make a loosely coupled system.
    
observers can be updated by two ways:
 
1. subject push the current data to the observers
2. subject notify observers, and they will pull the data from the subject

![Untitled](static/Untitled%207.png)

![Untitled](static/Untitled%208.png)