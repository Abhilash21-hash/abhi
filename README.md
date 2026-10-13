# STEP SEM-3 Java Lab Solutions - Week 9

This branch contains the solutions for **Week 9: Object-Oriented System Designs & Interfaces** (Practice & Assignment) for STEP SEM-3 (Java Programming).

## Files in this Branch

1. **`week9_practice.java`**
   - **Problem 1:** Movie Ticket Counter (Shared convenience fee in base `Ticket`, polymorphic seat rates)
   - **Problem 2:** Parcel Shipping Desk (`Insurable` interface for Express & Fragile parcels)
   - **Problem 3:** College Fee Counter (`UsesBus` interface default transport fee for Day Scholar & Scholar)
   - **Problem 4:** City Cab Fare Meter (`NightServiced` interface capability check)
   - **Problem 5:** Home Appliance Energy Report (`SaverModeSupported` interface capability check)

2. **`week9_assignment.java`**
   - **Problem 1:** Garden Plot Area Report (Polymorphism with abstract base `Plot` for Circle, Rectangle, Triangle)
   - **Problem 2:** Weekly Staff Pay (Abstract `Staff` class with subclasses `FullTimeStaff`, `HourlyStaff`, `InternStaff`)
   - **Problem 3:** Library Late Fine Counter (Abstract `LibraryItem` with `BookItem`, `DvdItem`, `MagazineItem`)
   - **Problem 4:** Electricity Connection Billing (Abstract `ElectricityConnection` with `HomeConnection`, `ShopConnection`, `FactoryConnection`)
   - **Problem 5:** Travel Booking with a Common Fee (Common booking fee in base `TravelBooking` with `BusBooking`, `TrainBooking`, `FlightBooking`)

## How to Compile & Run

### Practice:
```bash
javac week9_practice.java
java week9_practice
```

### Assignment:
```bash
javac week9_assignment.java
java week9_assignment
```