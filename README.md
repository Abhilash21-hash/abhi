# STEP SEM-3 Java Lab Solutions - Week 7

This branch contains the solutions for **Week 7: Encapsulation & Access Control** (Practice & Assignment) for STEP SEM-3 (Java Programming).

## Files in this Branch

1. **`week7_practice.java`**
   - **Problem 1:** The Piggy Bank (`PiggyBank`) - Encapsulated balance, withdrawal validation, final ID
   - **Problem 2:** The Quiz Scorecard (`Scorecard`) - Private boolean results array, expose score only
   - **Problem 3:** The Nickname Tag (`NameTag`) - Immutable class with final fields
   - **Problem 4:** The Locker Code (`Locker`) - Write-only access with security check, no getter
   - **Problem 5:** The Attendance Sheet (`AttendanceSheet`) - Internal presence array, duplicate protection

2. **`week7_assignment.java`**
   - **Problem 1:** The Health Bar (`Character`) - Value clamping between 0 and maxHealth
   - **Problem 2:** The Playlist (`Playlist`) - Defensive copying with `Arrays.copyOf(...)`
   - **Problem 3:** The Password Checker (`PasswordChecker`) - Private credential analysis without exposing plaintext
   - **Problem 4:** The Traffic Light (`TrafficLight`) - Controlled state transitions (RED -> GREEN -> YELLOW -> RED)
   - **Problem 5:** The Shopping Cart (`Cart`) - On-demand sum calculation, private price encapsulation

## How to Compile & Run

### Practice:
```bash
javac week7_practice.java
java week7_practice
```

### Assignment:
```bash
javac week7_assignment.java
java week7_assignment
```