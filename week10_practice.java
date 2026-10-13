import java.util.Arrays;
import java.util.Scanner;

public class week10_practice {

    // ==========================================
    // Problem 1: Binary Search Frequency Range
    // ==========================================
    public static int countOccurrences(int[] arr, int target) {
        int first = findFirst(arr, target);
        if (first == -1) return 0;
        int last = findLast(arr, target);
        return last - first + 1;
    }

    private static int findFirst(int[] arr, int target) {
        int l = 0, r = arr.length - 1, res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                res = mid;
                r = mid - 1;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    private static int findLast(int[] arr, int target) {
        int l = 0, r = arr.length - 1, res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                res = mid;
                l = mid + 1;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    public static void runBinarySearchRangeDemo() {
        System.out.println("\n--- Practice 1: Binary Search Frequency Range ---");
        int[] arr = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        System.out.printf("Array: %s, Target: %d -> Count: %d%n",
                Arrays.toString(arr), target, countOccurrences(arr, target));
    }

    // ==========================================
    // Problem 2: Longest Consecutive 1s (Streak)
    // ==========================================
    public static int longestConsecutiveOnes(int[] binaryArray) {
        int maxStreak = 0;
        int currentStreak = 0;
        for (int bit : binaryArray) {
            if (bit == 1) {
                currentStreak++;
                maxStreak = Math.max(maxStreak, currentStreak);
            } else {
                currentStreak = 0;
            }
        }
        return maxStreak;
    }

    public static void runConsecutiveOnesDemo() {
        System.out.println("\n--- Practice 2: Longest Consecutive 1s ---");
        int[] arr = {1, 1, 0, 1, 1, 1};
        System.out.printf("Binary array: %s -> Max Streak: %d%n",
                Arrays.toString(arr), longestConsecutiveOnes(arr));
    }

    // ==========================================
    // Problem 3: Second Smallest Distinct Element
    // ==========================================
    public static int secondSmallest(int[] nums) {
        if (nums == null || nums.length < 2) return -1;
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n < first) {
                second = first;
                first = n;
            } else if (n > first && n < second) {
                second = n;
            }
        }
        return (second == Integer.MAX_VALUE) ? -1 : second;
    }

    public static void runSecondSmallestDemo() {
        System.out.println("\n--- Practice 3: Second Smallest Distinct Element ---");
        int[] nums = {12, 13, 1, 10, 34, 1};
        System.out.printf("nums = %s -> Second Smallest: %d%n",
                Arrays.toString(nums), secondSmallest(nums));
    }

    // ==========================================
    // Problem 4: Maximum Row Sum in 2D Matrix
    // ==========================================
    public static int maxRowSum(int[][] matrix) {
        int maxSum = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            int sum = 0;
            for (int val : row) sum += val;
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static void runMaxRowSumDemo() {
        System.out.println("\n--- Practice 4: Maximum Row Sum in 2D Matrix ---");
        int[][] mat = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Matrix: [[1,2,3], [4,5,6], [7,8,9]] -> Max Row Sum: " + maxRowSum(mat));
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("  WEEK 10 PRACTICE - ALGORITHMIC LAB");
            System.out.println("========================================");
            System.out.println("1. Binary Search Frequency Range");
            System.out.println("2. Longest Consecutive 1s (Streak)");
            System.out.println("3. Second Smallest Distinct Element");
            System.out.println("4. Maximum Row Sum in 2D Matrix");
            System.out.println("5. Run All Practice Demos");
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
                case 1 -> runBinarySearchRangeDemo();
                case 2 -> runConsecutiveOnesDemo();
                case 3 -> runSecondSmallestDemo();
                case 4 -> runMaxRowSumDemo();
                case 5 -> {
                    runBinarySearchRangeDemo();
                    runConsecutiveOnesDemo();
                    runSecondSmallestDemo();
                    runMaxRowSumDemo();
                }
                case 0 -> {
                    System.out.println("Exiting Week 10 Practice. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-5.");
            }
        }
    }
}
