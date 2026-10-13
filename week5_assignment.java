import java.util.*;

public class week5_assignment {

    // ==========================================
    // Problem 1: Fantasy Team Score Multiplier
    // Modifies array directly in place
    // ==========================================
    public static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        playerScores[captainIndex] *= 2.0;
        playerScores[viceCaptainIndex] *= 1.5;
    }

    public static void runApplyMultipliers() {
        System.out.println("\n--- Problem 1: Fantasy Team Score Multiplier ---");
        double[] scores = {40.0, 55.0, 30.0, 62.0};
        System.out.println("Original Scores: " + Arrays.toString(scores));
        applyMultipliers(scores, 1, 3);
        System.out.println("After Multipliers (Captain=1, ViceCaptain=3): " + Arrays.toString(scores));
    }

    // ==========================================
    // Problem 2: Duplicate Player Pick Checker
    // Nested-loop pairwise comparison, no Collections
    // ==========================================
    public static String findDuplicatePick(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    public static void runDuplicatePick() {
        System.out.println("\n--- Problem 2: Duplicate Player Pick Checker ---");
        String[] lineup1 = {"Kohli", "Bumrah", "Kohli", "Rohit"};
        System.out.println(Arrays.toString(lineup1) + " -> " + findDuplicatePick(lineup1));

        String[] lineup2 = {"Kohli", "Bumrah", "Rohit"};
        System.out.println(Arrays.toString(lineup2) + " -> " + findDuplicatePick(lineup2));
    }

    // ==========================================
    // Problem 3: Top Performer Tracker
    // Single-pass min/max tracking without sorting
    // ==========================================
    public static String findMinMaxSpread(int[] scores) {
        int min = scores[0];
        int max = scores[0];

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) min = scores[i];
            if (scores[i] > max) max = scores[i];
        }

        int spread = max - min;
        return "Min: " + min + " | Max: " + max + " | Spread: " + spread;
    }

    public static void runMinMaxSpread() {
        System.out.println("\n--- Problem 3: Top Performer Tracker ---");
        int[] scores = {45, 82, 79, 90, 33, 90, 61};
        System.out.println("Scores: " + Arrays.toString(scores));
        System.out.println("Summary: " + findMinMaxSpread(scores));
    }

    // ==========================================
    // Problem 4: Match Day Grid Analyzer
    // Jagged 2D array support
    // ==========================================
    private static double rowAverage(int[] row) {
        if (row == null || row.length == 0) return 0.0;
        int sum = 0;
        for (int s : row) sum += s;
        return (double) sum / row.length;
    }

    public static String classifyMatches(int[][] runsPerOver, int threshold) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < runsPerOver.length; i++) {
            double avg = rowAverage(runsPerOver[i]);
            String tag = (avg >= threshold) ? "Power Surge" : "Normal";
            sb.append("Match ").append(i).append(": ").append(tag);
            if (i < runsPerOver.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void runMatchDayGrid() {
        System.out.println("\n--- Problem 4: Match Day Grid Analyzer ---");
        int[][] runs = {
            {4, 6, 8},
            {10, 12, 14},
            {2, 3, 1}
        };
        int threshold = 8;
        System.out.println(classifyMatches(runs, threshold));
    }

    // ==========================================
    // Problem 5: Fantasy League Auto-Draft Ranking Engine
    // Overloaded isDraftable, Comparable, Arrays.sort
    // ==========================================
    public static class Player implements Comparable<Player> {
        String name;
        int matchesPlayed;
        double battingAverage;
        boolean injured;

        public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        @Override
        public int compareTo(Player other) {
            // Sort descending by battingAverage (fantasy points)
            return Double.compare(other.battingAverage, this.battingAverage);
        }
    }

    // Established player: matchesPlayed >= 10
    public static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    // Borderline player: matchesPlayed >= 5 and NOT injured
    public static boolean isDraftable(int matchesPlayed, boolean injured) {
        return (matchesPlayed >= 10) || (matchesPlayed >= 5 && !injured);
    }

    public static String draftAndRank(Player[] players) {
        List<Player> drafted = new ArrayList<>();
        for (Player p : players) {
            if (isDraftable(p.matchesPlayed, p.injured)) {
                drafted.add(p);
            }
        }

        Player[] arr = drafted.toArray(new Player[0]);
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(i + 1).append(". ").append(arr[i].name);
            if (i < arr.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void runAutoDraft() {
        System.out.println("\n--- Problem 5: Fantasy League Auto-Draft Ranking Engine ---");
        Player[] lineup = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };
        System.out.println("Result: " + draftAndRank(lineup));
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("  WEEK 5 ASSIGNMENT - ARRAYS & METHODS");
            System.out.println("========================================");
            System.out.println("1. Fantasy Team Score Multiplier");
            System.out.println("2. Duplicate Player Pick Checker");
            System.out.println("3. Top Performer Tracker");
            System.out.println("4. Match Day Grid Analyzer");
            System.out.println("5. Fantasy League Auto-Draft Ranking Engine");
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
                case 1 -> runApplyMultipliers();
                case 2 -> runDuplicatePick();
                case 3 -> runMinMaxSpread();
                case 4 -> runMatchDayGrid();
                case 5 -> runAutoDraft();
                case 6 -> {
                    runApplyMultipliers();
                    runDuplicatePick();
                    runMinMaxSpread();
                    runMatchDayGrid();
                    runAutoDraft();
                }
                case 0 -> {
                    System.out.println("Exiting Week 5 Assignment. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
