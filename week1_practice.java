import java.util.Random;
import java.util.Scanner;

public class week1_practice {

    // ==========================================
    // Problem 1: Rock-Paper-Scissors Game
    // ==========================================
    public static String playRound(String playerMove, String computerMove) {
        String p = playerMove.trim().toLowerCase();
        String c = computerMove.trim().toLowerCase();

        if (p.equals(c)) {
            return "Draw";
        }
        if ((p.equals("rock") && c.equals("scissors")) ||
            (p.equals("paper") && c.equals("rock")) ||
            (p.equals("scissors") && c.equals("paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void runRockPaperScissors(Scanner sc) {
        System.out.println("\n--- Problem 1: Rock-Paper-Scissors ---");
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random rand = new Random();

        int rounds = 5;
        String[] playerMoves = new String[rounds];
        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];

        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < rounds; i++) {
            System.out.print("Round " + (i + 1) + " - Enter your move (Rock/Paper/Scissors) [or press Enter for sample]: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                input = moves[i % 3];
            }
            // Capitalize
            input = Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
            playerMoves[i] = input;
            computerMoves[i] = moves[rand.nextInt(3)];
            results[i] = playRound(playerMoves[i], computerMoves[i]);

            if (results[i].equals("Player Wins")) wins++;
            else if (results[i].equals("Computer Wins")) losses++;
            else draws++;
        }

        System.out.println("\nSummary Table:");
        System.out.println("Round | Player Move | Computer Move | Result");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < rounds; i++) {
            System.out.printf("  %d   | %-11s | %-13s | %s%n", (i + 1), playerMoves[i], computerMoves[i], results[i]);
        }
        double winPct = ((double) wins / rounds) * 100.0;
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPct);
    }

    // ==========================================
    // Problem 2: Palindrome Checker (3 Approaches)
    // ==========================================
    public static boolean isPalindromeIterative(String text) {
        int left = 0, right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) return true;
        if (text.charAt(0) != text.charAt(text.length() - 1)) return false;
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] chars = text.toCharArray();
        char[] reversed = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            reversed[i] = chars[chars.length - 1 - i];
        }
        return text.equals(new String(reversed));
    }

    public static void runPalindromeChecker(Scanner sc) {
        System.out.println("\n--- Problem 2: Palindrome Checker ---");
        System.out.print("Enter text to check [e.g. madam]: ");
        String text = sc.nextLine().trim();
        if (text.isEmpty()) text = "madam";

        boolean iter = isPalindromeIterative(text);
        boolean recur = isPalindromeRecursive(text);
        boolean arrRev = isPalindromeArrayReversal(text);

        System.out.printf("Iterative: %s | Recursive: %s | Array Reversal: %s%n",
                iter ? "Palindrome" : "Not Palindrome",
                recur ? "Palindrome" : "Not Palindrome",
                arrRev ? "Palindrome" : "Not Palindrome");
    }

    // ==========================================
    // Problem 3: BMI Calculator for a Team
    // ==========================================
    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi <= 24.9) return "Normal";
        if (bmi <= 29.9) return "Overweight";
        return "Obese";
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("\nWellness Report Table:");
        System.out.println("Person   | Height (m) | Weight (kg) | BMI    | Status");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < heights.length; i++) {
            double h = heights[i];
            double w = weights[i];
            double bmi = w / (h * h);
            String status = getBmiStatus(bmi);
            System.out.printf("Person %-2d| %-10.2f | %-11.2f | %-6.2f | %s%n",
                    (i + 1), h, w, bmi, status);
        }
    }

    public static void runBmiCalculator() {
        System.out.println("\n--- Problem 3: BMI Calculator ---");
        double[] heights = {1.75, 1.60, 1.80, 1.65, 1.70};
        double[] weights = {70.0, 90.0, 68.0, 48.0, 85.0};
        printWellnessReport(heights, weights);
    }

    // ==========================================
    // Problem 4: First Non-Repeating Character
    // ==========================================
    public static char findFirstNonRepeatingChar(String text) {
        int[] freq = new int[256];
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }
        for (int i = 0; i < text.length(); i++) {
            if (freq[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }
        return '\0';
    }

    public static void runFirstNonRepeatingChar(Scanner sc) {
        System.out.println("\n--- Problem 4: First Non-Repeating Character ---");
        System.out.print("Enter string [e.g. swiss]: ");
        String text = sc.nextLine().trim();
        if (text.isEmpty()) text = "swiss";

        char ch = findFirstNonRepeatingChar(text);
        if (ch != '\0') {
            System.out.println("First Non-Repeating Character: '" + ch + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }
    }

    // ==========================================
    // Problem 5: Reverse Customer Name
    // ==========================================
    public static String reverseCustomerName(String customerName) {
        char[] chars = customerName.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static void runReverseCustomerName(Scanner sc) {
        System.out.println("\n--- Problem 5: Reverse Customer Name ---");
        System.out.print("Enter customer name [e.g. Sunil]: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = "Sunil";

        String reversed = reverseCustomerName(name);
        System.out.println("Original Name: " + name);
        System.out.println("Reversed Name: " + reversed);
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("     WEEK 1 PRACTICE - LIVE CODING");
            System.out.println("========================================");
            System.out.println("1. Rock-Paper-Scissors Game");
            System.out.println("2. Palindrome Checker (3 Approaches)");
            System.out.println("3. BMI Calculator for a Team");
            System.out.println("4. First Non-Repeating Character");
            System.out.println("5. Reverse Customer Name");
            System.out.println("6. Run All Demos");
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
                case 1 -> runRockPaperScissors(sc);
                case 2 -> runPalindromeChecker(sc);
                case 3 -> runBmiCalculator();
                case 4 -> runFirstNonRepeatingChar(sc);
                case 5 -> runReverseCustomerName(sc);
                case 6 -> {
                    runRockPaperScissors(sc);
                    runPalindromeChecker(sc);
                    runBmiCalculator();
                    runFirstNonRepeatingChar(sc);
                    runReverseCustomerName(sc);
                }
                case 0 -> {
                    System.out.println("Exiting Week 1 Practice. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
