# **Design Notes – LearnTrack (Student & Course Management System)**

This document explains the important design decisions taken while building LearnTrack, focusing on Java fundamentals, OOP concepts, collections usage, static utilities, and clean architecture.

---

## Why `ArrayList` Instead of Array?
LearnTrack needs dynamic storage for Students, Courses, and Enrollments.

### Arrays 
- Fixed size
- Cannot grow automatically
- Hard to add/remove elements
- Error-prone for real-world systems

Example problem:
```java
Student[100];  // Limited to 100 students
```
## ArrayList

```
* ArrayList<Student>
* ArrayList<Course>
* ArrayList<Enrollment>
````
## Benefits:

* Dynamically grows / shrinks
* Easy add / remove / search
* Built-in helpful methods:
      ->add()
      ->remove()
      ->size()
* Clean looping support

So ArrayList provides flexibility + simplicity, which is ideal for this project.

## Where & Why Static Members Were Used?

We used a utility class IdGenerator to generate IDs:

`getNextStudentId()
 getNextCourseId()
 getNextEnrollmentId()`

### Why static?

* ID must auto-increment
* Same ID counter should be shared globally
* No need to create object for generator

for example,
private static int studentId = 100;

then: IdGenerator.getNextStudentId();

returns next unique ID.
So static helps in:
* Global shared counters
* Reusable utility behavior
* Consistency across system

## Where Inheritance Was Used & Why?

We created:
Person (Base Class)

Contains:

`id
firstName
lastName
email`

#### Student extends Person

Adds:

`batch
status`

### Why inheritance?

* Prevents code duplication
* Reuses common attributes
* Cleaner & more maintainable design
* Demonstrates real-world OOP

We also used:

`super(...)`
to call parent constructor.

### Polymorphism & Method Overriding

We override toString() in:

* Student
* Course
* Enrollment

So objects show meaningful output instead of:

`Student@17a7cec2`

This demonstrates runtime polymorphism.

### Encapsulation

All entity fields are:

`private`

We access using:

`getters & setters`

Benefits:

* Protects data
* Prevents direct modification
* Maintains integrity

### Separation of Concerns

We structured the system into clear layers:

### Entity Layer

Represents data objects `Student, Course, Enrollment`

### Repository Layer

Stores and manages lists

* StudentRepository
* CourseRepository
* EnrollmentRepository

### Service Layer

Contains business logic

* StudentService
* CourseService
* EnrollmentService

### Validation Layer

`InputValidator`

### Exception Layer

`InvalidInputException
EntityNotFoundException`

### UI Layer

Console interaction

`Main.java`


This ensures the application is:

* Organized
* Scalable
* Easy to maintain
* Easy for evaluation

### Exception Handling Design

We used:
try - catch
and custom exceptions:

* InvalidInputException
* EntityNotFoundException

This prevents application crashes and gives user-friendly messages.

### Summary

The design emphasizes:

* Core Java fundamentals
* Strong OOP principles
* Clean modular structure
* Realistic application behavior
* Beginner-friendly yet professional coding