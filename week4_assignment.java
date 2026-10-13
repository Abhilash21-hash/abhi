import java.util.*;

public class week4_assignment {

    // ==========================================
    // A1. Product of Array Except Self
    // O(n) time, no division allowed
    // ==========================================
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Forward pass: prefix products
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // Backward pass: multiply suffix products
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * right;
            right *= nums[i];
        }

        return answer;
    }

    public static void runProductExceptSelf() {
        System.out.println("\n--- A1. Product of Array Except Self ---");
        int[] nums1 = {1, 2, 3, 4};
        System.out.printf("nums = %s -> Output: %s%n",
                Arrays.toString(nums1), Arrays.toString(productExceptSelf(nums1)));

        int[] nums2 = {-1, 1, 0, -3, 3};
        System.out.printf("nums = %s -> Output: %s%n",
                Arrays.toString(nums2), Arrays.toString(productExceptSelf(nums2)));
    }

    // ==========================================
    // A2. Maximum Subarray (Kadane's Algorithm)
    // O(n) time, O(1) space
    // ==========================================
    public static int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currentMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }

        return maxSoFar;
    }

    public static void runMaxSubArray() {
        System.out.println("\n--- A2. Maximum Subarray ---");
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.printf("nums = %s -> Max Sum: %d%n",
                Arrays.toString(nums1), maxSubArray(nums1));

        int[] nums2 = {-3, -1, -2};
        System.out.printf("nums = %s -> Max Sum: %d%n",
                Arrays.toString(nums2), maxSubArray(nums2));
    }

    // ==========================================
    // A3. 3Sum
    // O(n^2) time, avoiding duplicates
    // ==========================================
    public static int[][] threeSum(int[] nums) {
        Arrays.sort(nums);
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates for i

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(new int[]{nums[i], nums[left], nums[right]});
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void runThreeSum() {
        System.out.println("\n--- A3. 3Sum ---");
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.print("nums = " + Arrays.toString(nums1) + " -> Output: [");
        int[][] res1 = threeSum(nums1);
        for (int i = 0; i < res1.length; i++) {
            System.out.print(Arrays.toString(res1[i]) + (i < res1.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    // ==========================================
    // A4. Subarray Sum Equals K
    // O(n) time and O(n) space using prefix sums hash map
    // ==========================================
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1); // Base case: prefix sum of 0 appears once

        int count = 0;
        int currentSum = 0;

        for (int num : nums) {
            currentSum += num;
            if (prefixMap.containsKey(currentSum - k)) {
                count += prefixMap.get(currentSum - k);
            }
            prefixMap.put(currentSum, prefixMap.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public static void runSubarraySum() {
        System.out.println("\n--- A4. Subarray Sum Equals K ---");
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        System.out.printf("nums = %s, k = %d -> Count: %d%n",
                Arrays.toString(nums1), k1, subarraySum(nums1, k1));

        int[] nums2 = {1, -1, 0};
        int k2 = 0;
        System.out.printf("nums = %s, k = %d -> Count: %d%n",
                Arrays.toString(nums2), k2, subarraySum(nums2, k2));
    }

    // ==========================================
    // A5. Find Minimum in Rotated Sorted Array
    // O(log n) modified binary search
    // ==========================================
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Minimum is in the right half
                left = mid + 1;
            } else {
                // Minimum is in the left half or at mid
                right = mid;
            }
        }

        return nums[left];
    }

    public static void runFindMin() {
        System.out.println("\n--- A5. Find Minimum in Rotated Sorted Array ---");
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.printf("nums = %s -> Min: %d%n", Arrays.toString(nums1), findMin(nums1));

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.printf("nums = %s -> Min: %d%n", Arrays.toString(nums2), findMin(nums2));

        int[] nums3 = {11, 13, 15, 17};
        System.out.printf("nums = %s -> Min: %d%n", Arrays.toString(nums3), findMin(nums3));
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println(" WEEK 4 ASSIGNMENT - ARRAYS & LOGIC");
            System.out.println("========================================");
            System.out.println("1. Product of Array Except Self (A1)");
            System.out.println("2. Maximum Subarray (A2)");
            System.out.println("3. 3Sum (A3)");
            System.out.println("4. Subarray Sum Equals K (A4)");
            System.out.println("5. Find Minimum in Rotated Sorted Array (A5)");
            System.out.println("6. Run All Assignment Demos");
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
                case 1 -> runProductExceptSelf();
                case 2 -> runMaxSubArray();
                case 3 -> runThreeSum();
                case 4 -> runSubarraySum();
                case 5 -> runFindMin();
                case 6 -> {
                    runProductExceptSelf();
                    runMaxSubArray();
                    runThreeSum();
                    runSubarraySum();
                    runFindMin();
                }
                case 0 -> {
                    System.out.println("Exiting Week 4 Assignment. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
