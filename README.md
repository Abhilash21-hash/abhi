# STEP SEM-3 Java Lab Solutions - Week 10

This branch contains the solutions for **Week 10: Algorithmic Complexity, Binary Search & 2D Arrays** (Practice & Coding Assignment) for STEP SEM-3 (Java Programming).

## Files in this Branch

1. **`week10_practice.java`**
   - **Practice 1:** Binary Search Frequency Range (`countOccurrences`, `findFirst`, `findLast`) - $O(\log n)$
   - **Practice 2:** Longest Consecutive 1s Streak (`longestConsecutiveOnes`) - $O(n)$ single pass
   - **Practice 3:** Second Smallest Distinct Element (`secondSmallest`) - $O(n)$ single pass
   - **Practice 4:** Maximum Row Sum in 2D Matrix (`maxRowSum`) - $O(r \times c)$

2. **`week10_assignment.java`**
   - **Problem 4:** Exam Score Band Counter (`countInBand`) - $O(\log n)$ modified binary search with duplicates
   - **Problem 6:** Class Attendance Tracker (`attendanceSummary`) - $O(n)$ single-pass present count & longest unbroken streak
   - **Problem 7:** Second-Best Score (`secondHighest`) - $O(n)$ single-pass second-highest distinct score without sorting
   - **Problem 10:** Busiest Bus Row (`busiestRow`) - $O(r \times c)$ 2D seat grid traversal with tie-breaking for lowest row index

## How to Compile & Run

### Practice:
```bash
javac week10_practice.java
java week10_practice
```

### Assignment:
```bash
javac week10_assignment.java
java week10_assignment
```