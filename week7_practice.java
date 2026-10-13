import java.util.Scanner;

public class week7_practice {

    // ==========================================
    // Problem 1: The Piggy Bank
    // ==========================================
    public static class PiggyBank {
        private final String id;
        private double savings;

        public PiggyBank(String id) {
            this.id = id;
            this.savings = 0.0;
        }

        public String getId() { return id; }

        public void deposit(double amount) {
            if (amount > 0) {
                savings += amount;
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= savings) {
                savings -= amount;
            } else {
                System.out.println("Withdrawal rejected: insufficient savings or invalid amount.");
            }
        }

        public double getSavings() {
            return savings;
        }
    }

    public static void runPiggyBankDemo() {
        System.out.println("\n--- Problem 1: The Piggy Bank ---");
        PiggyBank pb = new PiggyBank("PB-1");
        pb.deposit(100);
        System.out.println("pb.deposit(100) -> savings = " + (int) pb.getSavings());
        pb.withdraw(30);
        System.out.println("pb.withdraw(30) -> savings = " + (int) pb.getSavings());
        pb.withdraw(500);
        System.out.println("pb.withdraw(500) -> rejected, savings stays " + (int) pb.getSavings());
    }

    // ==========================================
    // Problem 2: The Quiz Scorecard
    // ==========================================
    public static class Scorecard {
        private final boolean[] answers;
        private int count;

        public Scorecard(int totalQuestions) {
            this.answers = new boolean[totalQuestions];
            this.count = 0;
        }

        public void recordAnswer(boolean isCorrect) {
            if (count < answers.length) {
                answers[count++] = isCorrect;
            }
        }

        public int getScore() {
            int score = 0;
            for (int i = 0; i < count; i++) {
                if (answers[i]) score++;
            }
            return score;
        }
    }

    public static void runScorecardDemo() {
        System.out.println("\n--- Problem 2: The Quiz Scorecard ---");
        Scorecard sc = new Scorecard(4);
        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);
        System.out.println("sc.getScore() -> " + sc.getScore());
    }

    // ==========================================
    // Problem 3: The Nickname Tag (Immutable)
    // ==========================================
    public static class NameTag {
        private final String firstName;
        private final char lastInitial;

        public NameTag(String fullName) {
            String[] parts = fullName.trim().split(" ");
            this.firstName = parts[0];
            this.lastInitial = parts.length > 1 ? parts[1].charAt(0) : ' ';
        }

        public String getNickname() {
            return firstName + " " + lastInitial + ".";
        }
    }

    public static void runNameTagDemo() {
        System.out.println("\n--- Problem 3: The Nickname Tag ---");
        NameTag tag = new NameTag("Maria Gomez");
        System.out.println("tag.getNickname() -> " + tag.getNickname());
    }

    // ==========================================
    // Problem 4: The Locker Code (Write-Only)
    // ==========================================
    public static class Locker {
        private final int lockerNumber;
        private String combinationCode;

        public Locker(int lockerNumber, String initialCode) {
            this.lockerNumber = lockerNumber;
            this.combinationCode = initialCode;
        }

        public int getLockerNumber() { return lockerNumber; }

        public boolean changeCode(String currentCode, String newCode) {
            if (this.combinationCode.equals(currentCode)) {
                this.combinationCode = newCode;
                System.out.println("changeCode -> success");
                return true;
            } else {
                System.out.println("changeCode -> rejected (wrong code)");
                return false;
            }
        }
    }

    public static void runLockerDemo() {
        System.out.println("\n--- Problem 4: The Locker Code ---");
        Locker l = new Locker(101, "1234");
        l.changeCode("1234", "5678");
        l.changeCode("0000", "9999");
    }

    // ==========================================
    // Problem 5: The Attendance Sheet
    // ==========================================
    public static class AttendanceSheet {
        private final String[] presentStudents;
        private int count;

        public AttendanceSheet(int maxCapacity) {
            this.presentStudents = new String[maxCapacity];
            this.count = 0;
        }

        public void markPresent(String name) {
            if (isPresent(name)) return; // Prevent duplicate entries
            if (count < presentStudents.length) {
                presentStudents[count++] = name;
            }
        }

        public int getPresentCount() {
            return count;
        }

        public boolean isPresent(String name) {
            for (int i = 0; i < count; i++) {
                if (presentStudents[i].equals(name)) return true;
            }
            return false;
        }
    }

    public static void runAttendanceDemo() {
        System.out.println("\n--- Problem 5: The Attendance Sheet ---");
        AttendanceSheet sheet = new AttendanceSheet(30);
        sheet.markPresent("Ana");
        sheet.markPresent("Ben");
        sheet.markPresent("Ana");
        System.out.println("sheet.getPresentCount() -> " + sheet.getPresentCount());
        System.out.println("sheet.isPresent(\"Ben\") -> " + sheet.isPresent("Ben"));
        System.out.println("sheet.isPresent(\"Chen\") -> " + sheet.isPresent("Chen"));
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("    WEEK 7 PRACTICE - ENCAPSULATION");
            System.out.println("========================================");
            System.out.println("1. The Piggy Bank");
            System.out.println("2. The Quiz Scorecard");
            System.out.println("3. The Nickname Tag");
            System.out.println("4. The Locker Code");
            System.out.println("5. The Attendance Sheet");
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
                case 1 -> runPiggyBankDemo();
                case 2 -> runScorecardDemo();
                case 3 -> runNameTagDemo();
                case 4 -> runLockerDemo();
                case 5 -> runAttendanceDemo();
                case 6 -> {
                    runPiggyBankDemo();
                    runScorecardDemo();
                    runNameTagDemo();
                    runLockerDemo();
                    runAttendanceDemo();
                }
                case 0 -> {
                    System.out.println("Exiting Week 7 Practice. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
