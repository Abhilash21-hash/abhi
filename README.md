# STEP SEM-3 Java Lab Solutions - Week 3

This branch contains the solutions for **Week 3: Abstraction & Interfaces** (Live-Coding Practice & Assignment) for STEP SEM-3 (Java Programming).

## Files in this Branch

1. **`week3_practice.java`** (Part A Coding Problems)
   - **Problem 1:** Garden Plot Area Report (Polymorphism with abstract base `Plot` for Circle, Rectangle, Triangle)
   - **Problem 2:** Weekly Staff Pay (Abstract `Staff` class with subclasses `FullTimeStaff`, `HourlyStaff`, `InternStaff`)
   - **Problem 3:** Library Late Fine Counter (Abstract `LibraryItem` with `BookItem`, `DvdItem`, `MagazineItem`)
   - **Problem 4:** Electricity Connection Billing (Abstract `ElectricityConnection` with `HomeConnection`, `ShopConnection`, `FactoryConnection`)
   - **Problem 5:** Travel Booking with a Common Fee (Common booking fee in base `TravelBooking` with `BusBooking`, `TrainBooking`, `FlightBooking`)

2. **`week3_assignment.java`** (Concept Architecture Demonstrations)
   - **Demo 1:** Abstract Class Hierarchy & Constructor Chaining
   - **Demo 2:** Multiple Interfaces & Resolving the Diamond Problem with default methods
   - **Demo 3:** Abstraction & Encapsulation Synergy
   - **Demo 4:** Real-World Payment Gateway Abstraction (`UpiPayment`, `CreditCardPayment`)

---

## Part B: Quiz Solutions

1. **Q1:** Which of the following best describes abstraction in object-oriented programming?  
   **Answer:** **C** (Hiding how an object performs its operations and showing only what it does)
2. **Q2:** Which statement about an abstract class in Java is true?  
   **Answer:** **C** (It cannot be instantiated directly)
3. **Q3:** If a concrete class extends an abstract class but fails to implement an inherited abstract method:  
   **Answer:** **B** (The class must be declared abstract, or the code will not compile)
4. **Q4:** Which of the following modifiers is NOT allowed for an abstract method in Java?  
   **Answer:** **C** (private)
5. **Q5:** Fields declared within a Java interface are implicitly:  
   **Answer:** **C** (public, static, and final)
6. **Q6:** How many interfaces can a single Java class implement?  
   **Answer:** **C** (Any number of interfaces)
7. **Q7:** If a class implements an interface, what access modifier must it use for the implemented methods?  
   **Answer:** **D** (public)
8. **Q8:** Why does Java allow a class to implement multiple interfaces but only extend a single class?  
   **Answer:** **C** (Interfaces carry no instance state, avoiding the 'diamond problem' ambiguity with conflicting data inheritance)
9. **Q9:** What happens if a class implements two interfaces that both provide a default method with the same signature?  
   **Answer:** **D** (A compile-time error occurs until the class overrides the method)
10. **Q10:** Which of the following is true regarding constructors in Java?  
    **Answer:** **B** (Abstract classes can have constructors that run when a subclass object is created)

---

## Part C: Concept Questions

- **Question 1 (Abstraction):** Hiding implementation details and showing only essential features. *Example:* An ATM machine displays options like "Withdraw Cash", hiding the backend banking API calls, currency dispenser mechanics, and ledger updates.
- **Question 2 (Abstraction vs Encapsulation):** Abstraction hides *complexity* (what it does at high level), while Encapsulation hides *internal data/state* (restricting direct variable access via `private` fields and getters/setters). Together, encapsulation protects data integrity and abstraction provides clean APIs to manipulate that data.
- **Question 3 (Abstract Class Constructors):** Abstract classes cannot be instantiated with `new`, but they define constructors to initialize common state/fields inherited by subclasses via `super(...)`.
- **Question 4 (Forbidden Modifiers for Abstract Methods):**  
  - `private`: Must be visible to subclasses to be overridden.  
  - `static`: Belongs to the class, not instances; cannot be polymorphically overridden.  
  - `final`: Prohibits overriding, directly contradicting the purpose of an abstract method.
- **Question 5 (Diamond Problem):** Occurs when a class inherits from two parent classes having conflicting definitions of the same method. Java avoids this by permitting only single class inheritance while allowing multiple interfaces (since interfaces do not hold instance state, and default method conflicts require explicit resolution).

---

## How to Compile & Run

### Practice:
```bash
javac week3_practice.java
java week3_practice
```

### Assignment:
```bash
javac week3_assignment.java
java week3_assignment
```