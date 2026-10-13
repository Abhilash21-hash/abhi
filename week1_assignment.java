import java.util.Scanner;

public class week1_assignment {

    // ==========================================
    // Problem 1: Exam Hall Seat Duplication Checker
    // ==========================================
    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean duplicateFound = false;
        // Use arrays and nested loops only (no Collections)
        for (int i = 0; i < seatNumbers.length; i++) {
            boolean alreadyReported = false;
            // Check if this duplicate was already reported earlier
            for (int k = 0; k < i; k++) {
                if (seatNumbers[k] == seatNumbers[i]) {
                    alreadyReported = true;
                    break;
                }
            }
            if (alreadyReported) continue;

            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    duplicateFound = true;
                    break;
                }
            }
        }
        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void runExamHallChecker(Scanner sc) {
        System.out.println("\n--- Problem 1: Exam Hall Seat Duplication Checker ---");
        System.out.println("Testing sample 1: {101, 102, 103, 102, 105}");
        checkDuplicateSeats(new int[]{101, 102, 103, 102, 105});

        System.out.println("\nTesting sample 2: {101, 102, 103, 104, 105}");
        checkDuplicateSeats(new int[]{101, 102, 103, 104, 105});
    }

    // ==========================================
    // Problem 2: Typing Speed Test Accuracy Checker
    // ==========================================
    public static void checkTypingAccuracy(String original, String typed) {
        int len = Math.min(original.length(), typed.length());
        int matched = 0;
        int firstMismatchPos = -1;
        char origChar = ' ', typedChar = ' ';

        for (int i = 0; i < len; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-based index
                origChar = original.charAt(i);
                typedChar = typed.charAt(i);
            }
        }

        int total = original.length();
        double accuracy = ((double) matched / total) * 100.0;

        if (firstMismatchPos != -1) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')%n",
                    matched, total, accuracy, firstMismatchPos, origChar, typedChar);
        } else if (original.length() != typed.length()) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | Length mismatch%n",
                    matched, total, accuracy);
        } else {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches%n",
                    matched, total, accuracy);
        }
    }

    public static void runTypingAccuracy(Scanner sc) {
        System.out.println("\n--- Problem 2: Typing Speed Test Accuracy Checker ---");
        System.out.println("Sample 1: original=\"hello world\", typed=\"hello worlt\"");
        checkTypingAccuracy("hello world", "hello worlt");

        System.out.println("\nSample 2: original=\"coding\", typed=\"coding\"");
        checkTypingAccuracy("coding", "coding");
    }

    // ==========================================
    // Problem 3: Traffic Signal Streak Analyzer
    // ==========================================
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("Empty signal log");
            return;
        }

        char bestChar = signalLog.charAt(0);
        int maxStreak = 1;

        char currentChar = signalLog.charAt(0);
        int currentStreak = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentStreak++;
            } else {
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                    bestChar = currentChar;
                }
                currentChar = signalLog.charAt(i);
                currentStreak = 1;
            }
        }

        if (currentStreak > maxStreak) {
            maxStreak = currentStreak;
            bestChar = currentChar;
        }

        System.out.println("Longest Streak: '" + bestChar + "' repeated " + maxStreak + " times");
    }

    public static void runTrafficStreak(Scanner sc) {
        System.out.println("\n--- Problem 3: Traffic Signal Streak Analyzer ---");
        System.out.print("Enter signal log (e.g. RRGGGYRR): ");
        String log = sc.nextLine().trim();
        if (log.isEmpty()) log = "RRGGGYRR";
        findLongestStreak(log);
    }

    // ==========================================
    // Problem 4: Warehouse Inventory Balancer
    // ==========================================
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0, totalB = 0;
        int highestQty = Integer.MIN_VALUE;
        String highestSection = "";
        int highestItemIdx = -1;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            if (sectionA[i] > highestQty) {
                highestQty = sectionA[i];
                highestSection = "Section A";
                highestItemIdx = i + 1; // 1-based item index
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];
            if (sectionB[i] > highestQty) {
                highestQty = sectionB[i];
                highestSection = "Section B";
                highestItemIdx = i + 1; // 1-based item index
            }
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";
        System.out.printf("Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)%n",
                totalA, totalB, status, highestQty, highestSection, highestItemIdx);
    }

    public static void runWarehouseBalancer() {
        System.out.println("\n--- Problem 4: Warehouse Inventory Balancer ---");
        int[] secA = {20, 15, 30};
        int[] secB = {25, 10, 30};
        analyzeInventory(secA, secB);
    }

    // ==========================================
    // Problem 5: Movie Review Word Length Profiler
    // ==========================================
    public static void classifyWordLengths(String review) {
        if (review == null || review.trim().isEmpty()) {
            System.out.println("Short: 0 | Medium: 0 | Long: 0");
            return;
        }

        String[] words = review.trim().split("\\s+");
        int shortCount = 0, medCount = 0, longCount = 0;

        for (String w : words) {
            // strip surrounding punctuation
            String clean = w.replaceAll("[^a-zA-Z0-9]", "");
            int len = clean.length();
            if (len == 0) continue;

            if (len >= 1 && len <= 4) {
                shortCount++;
            } else if (len >= 5 && len <= 8) {
                medCount++;
            } else {
                longCount++;
            }
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d%n", shortCount, medCount, longCount);
    }

    public static void runMovieReview(Scanner sc) {
        System.out.println("\n--- Problem 5: Movie Review Word Length Profiler ---");
        System.out.print("Enter review text [or press Enter for sample]: ");
        String text = sc.nextLine().trim();
        if (text.isEmpty()) {
            text = "This movie was absolutely fantastic and thrilling";
        }
        classifyWordLengths(text);
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("        WEEK 1 CODING ASSIGNMENT");
            System.out.println("========================================");
            System.out.println("1. Exam Hall Seat Duplication Checker");
            System.out.println("2. Typing Speed Test Accuracy Checker");
            System.out.println("3. Traffic Signal Streak Analyzer");
            System.out.println("4. Warehouse Inventory Balancer");
            System.out.println("5. Movie Review Word Length Profiler");
            System.out.println("6. Run All Samples");
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
                case 1 -> runExamHallChecker(sc);
                case 2 -> runTypingAccuracy(sc);
                case 3 -> runTrafficStreak(sc);
                case 4 -> runWarehouseBalancer();
                case 5 -> runMovieReview(sc);
                case 6 -> {
                    runExamHallChecker(sc);
                    runTypingAccuracy(sc);
                    runTrafficStreak(sc);
                    runWarehouseBalancer();
                    runMovieReview(sc);
                }
                case 0 -> {
                    System.out.println("Exiting Week 1 Assignment. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
