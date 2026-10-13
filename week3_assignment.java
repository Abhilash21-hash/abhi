import java.util.Scanner;

public class week3_assignment {

    // =========================================================================
    // Demonstration 1: Abstract Class Hierarchy & Constructor Chaining
    // (Demonstrates Part B Q10 & Part C Q3: Why abstract classes have constructors)
    // =========================================================================
    abstract static class Vehicle {
        private String make;
        private String model;

        // Abstract class constructor: runs when subclass object is created
        public Vehicle(String make, String model) {
            this.make = make;
            this.model = model;
        }

        public String getMake() { return make; }
        public String getModel() { return model; }

        // Abstract method: must be implemented by concrete subclass
        public abstract double calculateFuelEfficiency();

        public void displayInfo() {
            System.out.printf("Vehicle: %s %s | Efficiency: %.2f km/l%n",
                    make, model, calculateFuelEfficiency());
        }
    }

    static class Car extends Vehicle {
        private double engineDisplacement;

        public Car(String make, String model, double engineDisplacement) {
            super(make, model); // Invokes abstract superclass constructor
            this.engineDisplacement = engineDisplacement;
        }

        @Override
        public double calculateFuelEfficiency() {
            return 25.0 - (engineDisplacement * 2.5);
        }
    }

    public static void runConstructorChainingDemo() {
        System.out.println("\n--- 1. Abstract Class Constructor Chaining ---");
        Vehicle myCar = new Car("Honda", "Civic", 1.8);
        myCar.displayInfo();
    }

    // =========================================================================
    // Demonstration 2: Multiple Interface Implementation & Default Method Conflict
    // (Demonstrates Part B Q8, Q9 & Part C Q5: Resolving the Diamond Problem in Java)
    // =========================================================================
    interface Printer {
        default void logStatus() {
            System.out.println("[Printer] Logging printing status...");
        }
    }

    interface ScannerDevice {
        default void logStatus() {
            System.out.println("[ScannerDevice] Logging scanner status...");
        }
    }

    // Class implementing both interfaces MUST explicitly override conflicting default method
    static class AllInOneMachine implements Printer, ScannerDevice {
        @Override
        public void logStatus() {
            // Explicitly resolve ambiguity
            System.out.println("[AllInOneMachine] Resolving dual interface status:");
            Printer.super.logStatus();
            ScannerDevice.super.logStatus();
        }
    }

    public static void runDiamondProblemDemo() {
        System.out.println("\n--- 2. Multiple Interfaces & Default Method Resolution ---");
        AllInOneMachine machine = new AllInOneMachine();
        machine.logStatus();
    }

    // =========================================================================
    // Demonstration 3: Abstraction and Encapsulation Working Together
    // (Demonstrates Part C Q2: Managing complexity and data security together)
    // =========================================================================
    abstract static class BankAccount {
        // Encapsulation: private internal state
        private String accountNumber;
        private double balance;

        public BankAccount(String accountNumber, double initialBalance) {
            this.accountNumber = accountNumber;
            this.balance = Math.max(0, initialBalance);
        }

        public String getAccountNumber() { return accountNumber; }
        public double getBalance() { return balance; }

        protected void adjustBalance(double amount) {
            this.balance += amount;
        }

        // Abstraction: high-level business contract
        public abstract boolean withdraw(double amount);
        public abstract void deposit(double amount);
    }

    static class SavingsAccount extends BankAccount {
        private static final double MIN_BALANCE = 500.0;

        public SavingsAccount(String accountNumber, double initialBalance) {
            super(accountNumber, initialBalance);
        }

        @Override
        public boolean withdraw(double amount) {
            if (amount > 0 && (getBalance() - amount) >= MIN_BALANCE) {
                adjustBalance(-amount);
                System.out.printf("Withdrew: %.2f | New Balance: %.2f%n", amount, getBalance());
                return true;
            } else {
                System.out.println("Withdrawal rejected: minimum balance of 500 required.");
                return false;
            }
        }

        @Override
        public void deposit(double amount) {
            if (amount > 0) {
                adjustBalance(amount);
                System.out.printf("Deposited: %.2f | New Balance: %.2f%n", amount, getBalance());
            }
        }
    }

    public static void runAbstractionEncapsulationDemo() {
        System.out.println("\n--- 3. Abstraction + Encapsulation Synergy ---");
        BankAccount acc = new SavingsAccount("SB-987654", 2000.0);
        acc.deposit(500.0);
        acc.withdraw(1800.0);
        acc.withdraw(300.0); // Should be rejected due to min balance
    }

    // =========================================================================
    // Demonstration 4: Real-World Payment Gateway Abstraction
    // (Demonstrates Part C Q1: Real-life abstraction example hiding complex details)
    // =========================================================================
    interface PaymentGateway {
        // Implicitly public, static, and final (Part B Q5)
        String CURRENCY = "INR";

        // Abstract method: must use public access (Part B Q7)
        boolean processPayment(double amount);
        String getProviderName();
    }

    static class UpiPayment implements PaymentGateway {
        private String upiId;

        public UpiPayment(String upiId) {
            this.upiId = upiId;
        }

        @Override
        public String getProviderName() { return "UPI (" + upiId + ")"; }

        @Override
        public boolean processPayment(double amount) {
            System.out.printf("Initiating 2FA handshake with NPCI for %s %.2f via %s... [SUCCESS]%n",
                    CURRENCY, amount, upiId);
            return true;
        }
    }

    static class CreditCardPayment implements PaymentGateway {
        private String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        @Override
        public String getProviderName() {
            String masked = "XXXX-XXXX-XXXX-" + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
            return "CreditCard (" + masked + ")";
        }

        @Override
        public boolean processPayment(double amount) {
            System.out.printf("Connecting to Payment Gateway ISO 8583 protocol for %s %.2f... [SUCCESS]%n",
                    CURRENCY, amount);
            return true;
        }
    }

    public static void runPaymentGatewayDemo() {
        System.out.println("\n--- 4. Real-World Payment Gateway Abstraction ---");
        PaymentGateway[] gateways = {
            new UpiPayment("student@oksbi"),
            new CreditCardPayment("4111222233334444")
        };

        for (PaymentGateway pg : gateways) {
            System.out.println("Processing via: " + pg.getProviderName());
            pg.processPayment(1500.0);
        }
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println(" WEEK 3 ASSIGNMENT - ABSTRACTION & INTERFACES");
            System.out.println("========================================");
            System.out.println("1. Abstract Class Constructor Chaining");
            System.out.println("2. Multiple Interfaces & Diamond Problem Resolution");
            System.out.println("3. Abstraction + Encapsulation Synergy");
            System.out.println("4. Real-World Payment Gateway Abstraction");
            System.out.println("5. Run All Demonstrations");
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
                case 1 -> runConstructorChainingDemo();
                case 2 -> runDiamondProblemDemo();
                case 3 -> runAbstractionEncapsulationDemo();
                case 4 -> runPaymentGatewayDemo();
                case 5 -> {
                    runConstructorChainingDemo();
                    runDiamondProblemDemo();
                    runAbstractionEncapsulationDemo();
                    runPaymentGatewayDemo();
                }
                case 0 -> {
                    System.out.println("Exiting Week 3 Assignment. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-5.");
            }
        }
    }
}
