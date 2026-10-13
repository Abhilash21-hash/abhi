import java.util.Arrays;
import java.util.Scanner;

public class week4_practice {

    // ==========================================
    // L1. Two Sum
    // ==========================================
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public static void runTwoSum() {
        System.out.println("\n--- L1. Two Sum ---");
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.printf("nums = %s, target = %d -> Result: %s%n",
                Arrays.toString(nums), target, Arrays.toString(result));
    }

    // ==========================================
    // L2. Best Time to Buy and Sell Stock
    // ==========================================
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                int profit = prices[i] - minPrice;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    public static void runMaxProfit() {
        System.out.println("\n--- L2. Best Time to Buy and Sell Stock ---");
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.printf("prices = %s -> Max Profit: %d%n",
                Arrays.toString(prices1), maxProfit(prices1));

        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.printf("prices = %s -> Max Profit: %d%n",
                Arrays.toString(prices2), maxProfit(prices2));
    }

    // ==========================================
    // L3. Contains Duplicate
    // ==========================================
    public static boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void runContainsDuplicate() {
        System.out.println("\n--- L3. Contains Duplicate ---");
        int[] nums1 = {1, 2, 3, 1};
        System.out.printf("nums = %s -> %b%n", Arrays.toString(nums1), containsDuplicate(nums1));

        int[] nums2 = {1, 2, 3, 4};
        System.out.printf("nums = %s -> %b%n", Arrays.toString(nums2), containsDuplicate(nums2));
    }

    // ==========================================
    // L4. Merge Two Sorted Arrays
    // ==========================================
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static void runMergeSortedArrays() {
        System.out.println("\n--- L4. Merge Two Sorted Arrays ---");
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        System.out.printf("arr1 = %s, arr2 = %s -> %s%n",
                Arrays.toString(arr1), Arrays.toString(arr2),
                Arrays.toString(mergeSortedArrays(arr1, arr2)));
    }

    // ==========================================
    // L5. Rotate Array
    // ==========================================
    public static int[] rotateArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        k = k % n;
        int[] newArray = new int[n];

        for (int i = 0; i < n; i++) {
            newArray[(i + k) % n] = nums[i];
        }

        // Copy back to original
        System.arraycopy(newArray, 0, nums, 0, n);
        return newArray;
    }

    public static void runRotateArray() {
        System.out.println("\n--- L5. Rotate Array ---");
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.printf("Original = %s, k = %d%n", Arrays.toString(nums), k);
        rotateArray(nums, k);
        System.out.printf("Rotated  = %s%n", Arrays.toString(nums));
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("  WEEK 4 PRACTICE - FUNCTIONS & ARRAYS");
            System.out.println("========================================");
            System.out.println("1. Two Sum (L1)");
            System.out.println("2. Best Time to Buy and Sell Stock (L2)");
            System.out.println("3. Contains Duplicate (L3)");
            System.out.println("4. Merge Two Sorted Arrays (L4)");
            System.out.println("5. Rotate Array (L5)");
            System.out.println("6. Run All Practice Demos");
            System.out.println("0. Exit");
            System.out.print("Enter choice (0-6): ");

            String choiceStr = sc.nextLine().trim();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> runTwoSum();
                case 2 -> runMaxProfit();
                case 3 -> runContainsDuplicate();
                case 4 -> runMergeSortedArrays();
                case 5 -> runRotateArray();
                case 6 -> {
                    runTwoSum();
                    runMaxProfit();
                    runContainsDuplicate();
                    runMergeSortedArrays();
                    runRotateArray();
                }
                case 0 -> {
                    System.out.println("Exiting Week 4 Practice. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
