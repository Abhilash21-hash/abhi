import java.util.Scanner;

public class week6_assignment {

    // ==========================================
    // M1. Library Inventory Management
    // ==========================================
    public static class BookInventory {
        String title;
        String author;
        int copiesAvailable;

        public BookInventory(String title, String author, int copiesAvailable) {
            this.title = title;
            this.author = author;
            this.copiesAvailable = copiesAvailable;
        }

        public void printEntry() {
            System.out.printf("%s by %s - %d copies available%n", title, author, copiesAvailable);
        }
    }

    public static void runLibraryInventoryDemo() {
        System.out.println("\n--- M1. Library Inventory Management ---");
        BookInventory[] books = {
            new BookInventory("Clean Code", "Robert C. Martin", 3),
            new BookInventory("Effective Java", "Joshua Bloch", 5),
            new BookInventory("Refactoring", "Martin Fowler", 0),
            new BookInventory("Design Patterns", "GoF", 2)
        };
        for (BookInventory b : books) {
            b.printEntry();
        }
    }

    // ==========================================
    // M2. Payroll Salary Management
    // ==========================================
    public static class PayrollAccount {
        private double basicSalary;
        private double bonus;

        public PayrollAccount(double openingBasicSalary) {
            if (openingBasicSalary < 0) {
                System.out.println("Warning: Basic salary cannot be negative. Setting to 0.");
                this.basicSalary = 0;
            } else {
                this.basicSalary = openingBasicSalary;
            }
            this.bonus = 0;
        }

        public void creditBonus(double amount) {
            if (amount <= 0) {
                System.out.println("Bonus rejected: amount must be positive.");
            } else {
                this.bonus += amount;
                System.out.println("Bonus credited: Rs " + bonus);
            }
        }

        public void deductTax(double percent) {
            if (percent < 0 || percent > 100) {
                System.out.println("Tax deduction rejected: percent must be between 0 and 100.");
            } else {
                this.basicSalary -= (this.basicSalary * (percent / 100.0));
                System.out.println("Tax deducted: " + (int) percent + "%");
            }
        }

        public double getNetSalary() {
            return basicSalary + bonus;
        }
    }

    public static void runPayrollDemo() {
        System.out.println("\n--- M2. Payroll Salary Management ---");
        PayrollAccount acc = new PayrollAccount(50000);
        acc.creditBonus(5000);
        acc.deductTax(10);
        System.out.println("Net salary: Rs " + acc.getNetSalary());
    }

    // ==========================================
    // M3. Employee Profile Creation
    // Constructor chaining via this(...)
    // ==========================================
    public static class Employee {
        String empId;
        String empName;
        double salary;
        boolean isIntern;

        // Constructor for permanent employees
        public Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
            this.isIntern = false;
        }

        // Constructor for interns: chains via this(...)
        public Employee(String empId, String empName) {
            this(empId, empName, 0.0);
            this.isIntern = true;
        }

        public void printProfile() {
            System.out.printf("%s | %s | Rs %.1f | Intern: %b%n",
                    empId, empName, salary, isIntern);
        }
    }

    public static void runEmployeeProfileDemo() {
        System.out.println("\n--- M3. Employee Profile Creation ---");
        Employee e1 = new Employee("E-101", "Divya", 65000);
        Employee e2 = new Employee("E-102", "Arjun");

        e1.printProfile();
        e2.printProfile();
    }

    // ==========================================
    // M4. Exam Hall Ticket Reference Management
    // ==========================================
    public static class HallTicket {
        String studentName;
        int seatNumber;

        public HallTicket(String studentName, int seatNumber) {
            this.studentName = studentName;
            this.seatNumber = seatNumber;
        }
    }

    public static void runHallTicketDemo() {
        System.out.println("\n--- M4. Exam Hall Ticket Reference Management ---");
        HallTicket priya = new HallTicket("Priya", 0);
        HallTicket copy = priya; // Points to the same instance
        copy.seatNumber = 45;

        HallTicket separate = new HallTicket("Priya", 45); // Distinct instance

        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);
        System.out.println("copy == priya: " + (copy == priya));
        System.out.println("separate == priya: " + (separate == priya));
    }

    // ==========================================
    // M5. Employee and Company Information Management
    // Static vs Instance fields
    // ==========================================
    public static class CompanyEmployee {
        String empName;
        double salary;
        static String companyName = "Bright Horizon Technologies";
        static int employeeCount = 0;

        public CompanyEmployee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }

        public static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }

    public static void runCompanyInfoDemo() {
        System.out.println("\n--- M5. Employee and Company Info Management ---");
        new CompanyEmployee("Ramesh", 55000);
        new CompanyEmployee("Suresh", 60000);
        new CompanyEmployee("Deepa", 75000);
        System.out.println("3 Employee objects created");
        CompanyEmployee.printCompanyInfo();
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println(" WEEK 6 ASSIGNMENT - CLASSES & OBJECTS");
            System.out.println("========================================");
            System.out.println("1. Library Inventory Management (M1)");
            System.out.println("2. Payroll Salary Management (M2)");
            System.out.println("3. Employee Profile Creation (M3)");
            System.out.println("4. Exam Hall Ticket Reference Management (M4)");
            System.out.println("5. Employee & Company Info Management (M5)");
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
                case 1 -> runLibraryInventoryDemo();
                case 2 -> runPayrollDemo();
                case 3 -> runEmployeeProfileDemo();
                case 4 -> runHallTicketDemo();
                case 5 -> runCompanyInfoDemo();
                case 6 -> {
                    runLibraryInventoryDemo();
                    runPayrollDemo();
                    runEmployeeProfileDemo();
                    runHallTicketDemo();
                    runCompanyInfoDemo();
                }
                case 0 -> {
                    System.out.println("Exiting Week 6 Assignment. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
