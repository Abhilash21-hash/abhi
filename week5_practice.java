import java.util.*;

public class week5_practice {

    // ==========================================
    // Problem 1: Hackathon Score Curve Booster
    // ==========================================
    public static void curveScores(int[] scores, int bonus) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] += bonus;
        }
    }

    public static void runCurveScores() {
        System.out.println("\n--- Problem 1: Hackathon Score Curve Booster ---");
        int[] scores = {70, 85, 60};
        System.out.println("Original: " + Arrays.toString(scores));
        curveScores(scores, 10);
        System.out.println("Curved (+10): " + Arrays.toString(scores));
    }

    // ==========================================
    // Problem 2: Duplicate Team Name Finder
    // Plain nested loops, no Collections
    // ==========================================
    public static String findDuplicateTeam(String[] teamNames) {
        for (int i = 0; i < teamNames.length; i++) {
            for (int j = i + 1; j < teamNames.length; j++) {
                if (teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    public static void runDuplicateTeam() {
        System.out.println("\n--- Problem 2: Duplicate Team Name Finder ---");
        String[] teams1 = {"ByteForce", "CodeCrafters", "ByteForce"};
        System.out.println(Arrays.toString(teams1) + " -> " + findDuplicateTeam(teams1));

        String[] teams2 = {"ByteForce", "CodeCrafters", "NullPointers"};
        System.out.println(Arrays.toString(teams2) + " -> " + findDuplicateTeam(teams2));
    }

    // ==========================================
    // Problem 3: Top-3 Podium Finder
    // Single pass, no sorting
    // ==========================================
    public static int[] findTopThreeScores(int[] scores) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int s : scores) {
            if (s > first) {
                third = second;
                second = first;
                first = s;
            } else if (s > second) {
                third = second;
                second = s;
            } else if (s > third) {
                third = s;
            }
        }

        return new int[]{first, second, third};
    }

    public static void runTopThreeScores() {
        System.out.println("\n--- Problem 3: Top-3 Podium Finder ---");
        int[] scores = {45, 82, 79, 90, 33, 90, 61};
        System.out.println("Scores: " + Arrays.toString(scores));
        System.out.println("Top-3 Podium: " + Arrays.toString(findTopThreeScores(scores)));
    }

    // ==========================================
    // Problem 4: Hackathon Seating Grid Optimizer
    // Jagged 2D array support
    // ==========================================
    private static double rowAverage(int[] row) {
        if (row == null || row.length == 0) return 0.0;
        int sum = 0;
        for (int s : row) sum += s;
        return (double) sum / row.length;
    }

    public static String classifyRows(int[][] seatingScores, int threshold) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seatingScores.length; i++) {
            double avg = rowAverage(seatingScores[i]);
            String zone = (avg < threshold) ? "Quiet Zone" : "Buzzing Zone";
            sb.append("Row ").append(i).append(": ").append(zone);
            if (i < seatingScores.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void runSeatingGrid() {
        System.out.println("\n--- Problem 4: Hackathon Seating Grid Optimizer ---");
        int[][] grid = {
            {40, 50, 45},
            {85, 90, 95},
            {30, 20, 25}
        };
        int threshold = 60;
        System.out.println(classifyRows(grid, threshold));
    }

    // ==========================================
    // Problem 5: Placement Drive Shortlisting & Ranking Engine
    // Overloading, Comparable, Arrays.sort
    // ==========================================
    public static class Candidate implements Comparable<Candidate> {
        String name;
        double cgpa;
        int codingScore;
        double compositeScore;

        public Candidate(String name, double cgpa, int codingScore) {
            this.name = name;
            this.cgpa = cgpa;
            this.codingScore = codingScore;
            // Composite score calculation: (CGPA * 10) + (codingScore * 0.5)
            this.compositeScore = (cgpa * 10.0) + (codingScore * 0.5);
        }

        @Override
        public int compareTo(Candidate other) {
            // Descending order by compositeScore
            return Double.compare(other.compositeScore, this.compositeScore);
        }
    }

    // CGPA-only filter: >= 7.5
    public static boolean isEligible(double cgpa) {
        return cgpa >= 7.5;
    }

    // Borderline filter: CGPA >= 6.5 AND codingScore >= 60
    public static boolean isEligible(double cgpa, int codingScore) {
        return (cgpa >= 7.5) || (cgpa >= 6.5 && codingScore >= 60);
    }

    public static String shortlistAndRank(Candidate[] candidates) {
        List<Candidate> qualified = new ArrayList<>();
        for (Candidate c : candidates) {
            if (isEligible(c.cgpa, c.codingScore)) {
                qualified.add(c);
            }
        }

        Candidate[] arr = qualified.toArray(new Candidate[0]);
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(i + 1).append(". ").append(arr[i].name)
              .append(" (").append(String.format("%.1f", arr[i].compositeScore)).append(")");
            if (i < arr.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void runPlacementEngine() {
        System.out.println("\n--- Problem 5: Placement Drive Ranking Engine ---");
        Candidate[] batch = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };
        System.out.println("Result: " + shortlistAndRank(batch));
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   WEEK 5 PRACTICE - ARRAYS & METHODS");
            System.out.println("========================================");
            System.out.println("1. Hackathon Score Curve Booster");
            System.out.println("2. Duplicate Team Name Finder");
            System.out.println("3. Top-3 Podium Finder");
            System.out.println("4. Hackathon Seating Grid Optimizer");
            System.out.println("5. Placement Drive Ranking Engine");
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
                case 1 -> runCurveScores();
                case 2 -> runDuplicateTeam();
                case 3 -> runTopThreeScores();
                case 4 -> runSeatingGrid();
                case 5 -> runPlacementEngine();
                case 6 -> {
                    runCurveScores();
                    runDuplicateTeam();
                    runTopThreeScores();
                    runSeatingGrid();
                    runPlacementEngine();
                }
                case 0 -> {
                    System.out.println("Exiting Week 5 Practice. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
