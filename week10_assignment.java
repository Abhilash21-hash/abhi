import java.util.Arrays;
import java.util.Scanner;

public class week10_assignment {

    // =========================================================================
    // Problem 4: Exam Score Band Counter
    // Function: countInBand(scores, low, high)
    // O(log n) time using binary search (lower bound and upper bound)
    // =========================================================================
    public static int lowerBound(int[] scores, int target) {
        int left = 0, right = scores.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int upperBound(int[] scores, int target) {
        int left = 0, right = scores.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int countInBand(int[] scores, int low, int high) {
        if (scores == null || scores.length == 0 || low > high) return 0;
        int firstGreaterOrEqual = lowerBound(scores, low);
        int firstStrictlyGreater = upperBound(scores, high);
        return Math.max(0, firstStrictlyGreater - firstGreaterOrEqual);
    }

    public static void runExamScoreBandCounter() {
        System.out.println("\n--- Problem 4: Exam Score Band Counter ---");
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println("scores = " + Arrays.toString(scores));

        int res1 = countInBand(scores, 42, 58);
        System.out.println("low = 42, high = 58 -> Count: " + res1 + " (Expected: 6)");

        int res2 = countInBand(scores, 90, 100);
        System.out.println("low = 90, high = 100 -> Count: " + res2 + " (Expected: 0)");
    }

    // =========================================================================
    // Problem 6: Class Attendance Tracker
    // Function: attendanceSummary(days)
    // Single pass: O(n) time, O(1) space
    // =========================================================================
    public static class AttendanceResult {
        public int presentDays;
        public int longestStreak;

        public AttendanceResult(int p, int s) {
            this.presentDays = p;
            this.longestStreak = s;
        }

        @Override
        public String toString() {
            return "Present: " + presentDays + ", Longest streak: " + longestStreak;
        }
    }

    public static AttendanceResult attendanceSummary(int[] days) {
        int presentCount = 0;
        int maxStreak = 0;
        int currentStreak = 0;

        for (int d : days) {
            if (d == 1) {
                presentCount++;
                currentStreak++;
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                }
            } else {
                currentStreak = 0;
            }
        }

        return new AttendanceResult(presentCount, maxStreak);
    }

    public static void runAttendanceTracker() {
        System.out.println("\n--- Problem 6: Class Attendance Tracker ---");
        int[] days1 = {1, 1, 0, 1, 1, 1, 0, 1};
        System.out.println("days = " + Arrays.toString(days1) + " -> " + attendanceSummary(days1));

        int[] days2 = {0, 0, 0};
        System.out.println("days = " + Arrays.toString(days2) + " -> " + attendanceSummary(days2));
    }

    // =========================================================================
    // Problem 7: Second-Best Score
    // Function: secondHighest(scores)
    // Single pass: O(n) time, O(1) space, no sorting
    // =========================================================================
    public static int secondHighest(int[] scores) {
        if (scores == null || scores.length < 2) return -1;

        int highest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int s : scores) {
            if (s > highest) {
                second = highest;
                highest = s;
            } else if (s > second && s < highest) {
                second = s;
            }
        }

        return (second == Integer.MIN_VALUE) ? -1 : second;
    }

    public static void runSecondHighestScore() {
        System.out.println("\n--- Problem 7: Second-Best Score ---");
        int[] scores1 = {45, 78, 92, 78, 60};
        System.out.println("scores = " + Arrays.toString(scores1) + " -> Output: " + secondHighest(scores1));

        int[] scores2 = {50, 50, 50};
        System.out.println("scores = " + Arrays.toString(scores2) + " -> Output: " + secondHighest(scores2));
    }

    // =========================================================================
    // Problem 10: Busiest Bus Row
    // Function: busiestRow(grid)
    // O(r * c) time, O(1) extra space
    // =========================================================================
    public static class RowResult {
        public int rowIndex;
        public int total;

        public RowResult(int r, int t) {
            this.rowIndex = r;
            this.total = t;
        }

        @Override
        public String toString() {
            return "Row " + rowIndex + ", Total " + total;
        }
    }

    public static RowResult busiestRow(int[][] grid) {
        if (grid == null || grid.length == 0) return new RowResult(-1, 0);

        int bestRow = 0;
        int maxTotal = Integer.MIN_VALUE;

        for (int r = 0; r < grid.length; r++) {
            int rowSum = 0;
            for (int c = 0; c < grid[r].length; c++) {
                rowSum += grid[r][c];
            }
            // Update only if strictly larger (preserves smaller index on tie)
            if (rowSum > maxTotal) {
                maxTotal = rowSum;
                bestRow = r;
            }
        }

        return new RowResult(bestRow, maxTotal);
    }

    public static void runBusiestBusRow() {
        System.out.println("\n--- Problem 10: Busiest Bus Row ---");
        int[][] grid = {
            {2, 0, 1},
            {3, 3, 1},
            {1, 1, 1}
        };
        System.out.println("grid = [[2,0,1], [3,3,1], [1,1,1]]");
        System.out.println("Expected: Row 1, Total 7 -> Output: " + busiestRow(grid));
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("  WEEK 10 CODING ASSIGNMENT SOLUTIONS");
            System.out.println("========================================");
            System.out.println("1. Problem 4: Exam Score Band Counter");
            System.out.println("2. Problem 6: Class Attendance Tracker");
            System.out.println("3. Problem 7: Second-Best Score");
            System.out.println("4. Problem 10: Busiest Bus Row");
            System.out.println("5. Run All Assignment Demos");
            System.out.println("0. Exit");
            System.out.print("Enter choice (0-5): ");

            String choiceStr = sc.nextLine().trim();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> runExamScoreBandCounter();
                case 2 -> runAttendanceTracker();
                case 3 -> runSecondHighestScore();
                case 4 -> runBusiestBusRow();
                case 5 -> {
                    runExamScoreBandCounter();
                    runAttendanceTracker();
                    runSecondHighestScore();
                    runBusiestBusRow();
                }
                case 0 -> {
                    System.out.println("Exiting Week 10 Assignment. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-5.");
            }
        }
    }
}
