import java.util.Scanner;

public class week6_practice {

    // ==========================================
    // M1. Student Placement Record Management
    // ==========================================
    public static class PlacementRecord {
        String studentName;
        String company;
        double packageLpa;

        public PlacementRecord(String studentName, String company, double packageLpa) {
            this.studentName = studentName;
            this.company = company;
            this.packageLpa = packageLpa;
        }

        public void printRecord() {
            System.out.printf("%s -> %s @ %.1f LPA%n", studentName, company, packageLpa);
        }
    }

    public static void runPlacementDemo() {
        System.out.println("\n--- M1. Student Placement Record Management ---");
        PlacementRecord[] records = {
            new PlacementRecord("Ravi", "TCS", 4.5),
            new PlacementRecord("Anitha", "Zoho", 6.2),
            new PlacementRecord("Karthik", "Infosys", 4.0)
        };
        for (PlacementRecord rec : records) {
            rec.printRecord();
        }
    }

    // ==========================================
    // M2. Hostel Mess Wallet Management
    // ==========================================
    public static class MessWallet {
        private double balance;

        public MessWallet(double openingBalance) {
            if (openingBalance < 0) {
                System.out.println("Warning: Opening balance cannot be negative. Setting to 0.");
                this.balance = 0;
            } else {
                this.balance = openingBalance;
            }
        }

        public void topUp(double amount) {
            if (amount <= 0) {
                System.out.println("Top-up rejected: amount must be positive.");
            } else {
                this.balance += amount;
                System.out.printf("Balance after top-up: %.1f%n", balance);
            }
        }

        public void deduct(double amount) {
            if (amount > balance) {
                System.out.println("Deduct rejected: insufficient balance");
            } else {
                this.balance -= amount;
                System.out.printf("Deducted %.1f. New balance: %.1f%n", amount, balance);
            }
        }

        public double getBalance() {
            return balance;
        }
    }

    public static void runMessWalletDemo() {
        System.out.println("\n--- M2. Hostel Mess Wallet Management ---");
        MessWallet wallet = new MessWallet(500);
        wallet.topUp(200);
        wallet.deduct(1000);
        System.out.printf("Final balance: %.1f%n", wallet.getBalance());
    }

    // ==========================================
    // M3. Course Credit Management
    // ==========================================
    public static class Course {
        String code;
        String title;
        int credits;
        int labCredits;

        public Course(String code, String title, int credits, int labCredits) {
            this.code = code;
            this.title = title;
            this.credits = credits;
            this.labCredits = labCredits;
        }

        // Chains to 4-arg constructor via this(...)
        public Course(String code, String title, int credits) {
            this(code, title, credits, 0);
        }

        public int totalCredits() {
            return credits + labCredits;
        }
    }

    public static void runCourseCreditDemo() {
        System.out.println("\n--- M3. Course Credit Management ---");
        Course c1 = new Course("21CSC201J", "Data Structures", 4);
        Course c2 = new Course("21CSC205L", "DSA Lab", 3, 1);

        System.out.println(c1.code + " total credits: " + c1.totalCredits());
        System.out.println(c2.code + " total credits: " + c2.totalCredits());
    }

    // ==========================================
    // M4. Library ID Card Management
    // ==========================================
    public static class IdCard {
        String name;
        int booksIssued;

        public IdCard(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
        }
    }

    public static void runIdCardDemo() {
        System.out.println("\n--- M4. Library ID Card Management ---");
        IdCard ravi = new IdCard("Ravi", 0);
        IdCard duplicate = ravi; // Points to same object
        duplicate.booksIssued = 3;

        IdCard separate = new IdCard("Ravi", 3); // Separate object

        System.out.println("Ravi's booksIssued (via first variable): " + ravi.booksIssued);
        System.out.println("duplicate == ravi: " + (duplicate == ravi));
        System.out.println("separate == ravi: " + (separate == ravi));
    }

    // ==========================================
    // M5. Student and College Information Management
    // ==========================================
    public static class Student {
        String name;
        double attendance;
        static String collegeName = "SRM Institute of Science and Technology";
        static int studentCount = 0;

        public Student(String name, double attendance) {
            this.name = name;
            this.attendance = attendance;
            studentCount++;
        }

        public static void printCollegeInfo() {
            System.out.println(collegeName);
            System.out.println("Students created: " + studentCount);
        }
    }

    public static void runCollegeInfoDemo() {
        System.out.println("\n--- M5. Student and College Info Management ---");
        Student s1 = new Student("Ananya", 92.5);
        Student s2 = new Student("Karan", 88.0);
        System.out.println("2 Student objects created");
        Student.printCollegeInfo();
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("  WEEK 6 PRACTICE - CLASSES & OBJECTS");
            System.out.println("========================================");
            System.out.println("1. Student Placement Record Management (M1)");
            System.out.println("2. Hostel Mess Wallet Management (M2)");
            System.out.println("3. Course Credit Management (M3)");
            System.out.println("4. Library ID Card Management (M4)");
            System.out.println("5. Student and College Info Management (M5)");
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
                case 1 -> runPlacementDemo();
                case 2 -> runMessWalletDemo();
                case 3 -> runCourseCreditDemo();
                case 4 -> runIdCardDemo();
                case 5 -> runCollegeInfoDemo();
                case 6 -> {
                    runPlacementDemo();
                    runMessWalletDemo();
                    runCourseCreditDemo();
                    runIdCardDemo();
                    runCollegeInfoDemo();
                }
                case 0 -> {
                    System.out.println("Exiting Week 6 Practice. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
