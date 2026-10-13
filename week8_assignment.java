import java.time.LocalDate;
import java.util.*;

public class week8_assignment {

    // ==========================================
    // Problem 1: The Canteen Billing Counter
    // ==========================================
    abstract static class CustomerBill {
        String type;
        double amount;

        CustomerBill(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        abstract double calculateFinalAmount();
    }

    static class StudentBill extends CustomerBill {
        StudentBill(double amount) { super("STUDENT", amount); }
        @Override double calculateFinalAmount() { return amount * 0.90; }
    }

    static class StaffBill extends CustomerBill {
        StaffBill(double amount) { super("STAFF", amount); }
        @Override double calculateFinalAmount() { return amount * 0.95; }
    }

    static class GuestBill extends CustomerBill {
        GuestBill(double amount) { super("GUEST", amount); }
        @Override double calculateFinalAmount() { return amount + 10.0; }
    }

    public static void runCanteenBillingDemo() {
        System.out.println("\n--- Problem 1: The Canteen Billing Counter ---");
        List<CustomerBill> bills = List.of(
            new StudentBill(200),
            new StaffBill(300),
            new GuestBill(150)
        );
        double total = 0;
        for (CustomerBill b : bills) {
            double finalAmt = b.calculateFinalAmount();
            System.out.printf("%s: %.2f%n", b.type, finalAmt);
            total += finalAmt;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ==========================================
    // Problem 2: Campus Parking Charge Calculator
    // ==========================================
    abstract static class ParkedVehicle {
        String type;
        int hours;

        ParkedVehicle(String type, int hours) {
            this.type = type;
            this.hours = hours;
        }

        abstract double calculateCharge();
    }

    static class BikeParking extends ParkedVehicle {
        BikeParking(int hours) { super("BIKE", hours); }
        @Override double calculateCharge() { return hours * 10.0; }
    }

    static class CarParking extends ParkedVehicle {
        CarParking(int hours) { super("CAR", hours); }
        @Override double calculateCharge() {
            if (hours <= 1) return 30.0;
            return 30.0 + ((hours - 1) * 20.0);
        }
    }

    static class TruckParking extends ParkedVehicle {
        TruckParking(int hours) { super("TRUCK", hours); }
        @Override double calculateCharge() {
            return Math.max(100.0, hours * 50.0);
        }
    }

    public static void runParkingChargeDemo() {
        System.out.println("\n--- Problem 2: Campus Parking Charge Calculator ---");
        List<ParkedVehicle> vehicles = List.of(
            new BikeParking(3),
            new CarParking(4),
            new TruckParking(1),
            new CarParking(1)
        );
        double total = 0;
        for (ParkedVehicle v : vehicles) {
            double charge = v.calculateCharge();
            System.out.printf("%s: %.2f%n", v.type, charge);
            total += charge;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ==========================================
    // Problem 3: The Hostel Electricity Bill
    // ==========================================
    abstract static class HostelRoom {
        String type;
        int units;

        HostelRoom(String type, int units) {
            this.type = type;
            this.units = units;
        }

        abstract double calculateBill();
    }

    static class SingleRoom extends HostelRoom {
        SingleRoom(int units) { super("SINGLE", units); }
        @Override double calculateBill() { return units * 8.0; }
    }

    static class SharedRoom extends HostelRoom {
        int occupants;
        SharedRoom(int units, int occupants) {
            super("SHARED", units);
            this.occupants = occupants;
        }
        @Override double calculateBill() {
            return (units * 6.0) / occupants;
        }
    }

    static class AcRoom extends HostelRoom {
        AcRoom(int units) { super("AC", units); }
        @Override double calculateBill() { return (units * 10.0) + 200.0; }
    }

    public static void runHostelBillDemo() {
        System.out.println("\n--- Problem 3: The Hostel Electricity Bill ---");
        List<HostelRoom> rooms = List.of(
            new SingleRoom(120),
            new SharedRoom(150, 3),
            new AcRoom(100)
        );
        double total = 0;
        for (HostelRoom r : rooms) {
            double bill = r.calculateBill();
            System.out.printf("%s: %.2f%n", r.type, bill);
            total += bill;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ==========================================
    // Problem 4: The Festival Bonus Calculator
    // ==========================================
    abstract static class EmployeeBonus {
        String name;
        double salary;

        EmployeeBonus(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        abstract double calculateBonus();
    }

    static class FullTimeBonus extends EmployeeBonus {
        FullTimeBonus(String name, double salary) { super(name, salary); }
        @Override double calculateBonus() { return salary * 0.10; }
    }

    static class PartTimeBonus extends EmployeeBonus {
        PartTimeBonus(String name, double salary) { super(name, salary); }
        @Override double calculateBonus() { return salary * 0.05; }
    }

    static class InternBonus extends EmployeeBonus {
        InternBonus(String name, double salary) { super(name, salary); }
        @Override double calculateBonus() { return 2000.0; }
    }

    public static void runFestivalBonusDemo() {
        System.out.println("\n--- Problem 4: The Festival Bonus Calculator ---");
        List<EmployeeBonus> employees = List.of(
            new FullTimeBonus("Asha", 50000),
            new PartTimeBonus("Ravi", 30000),
            new InternBonus("Neha", 15000)
        );
        double total = 0;
        for (EmployeeBonus e : employees) {
            double b = e.calculateBonus();
            System.out.printf("%s: %.2f%n", e.name, b);
            total += b;
        }
        System.out.printf("Total Bonus: %.2f%n", total);
    }

    // ==========================================
    // Problem 5: Streaming Plan Renewal Reminder
    // ==========================================
    abstract static class StreamingPlan {
        String name;
        LocalDate startDate;

        StreamingPlan(String name, String startDate) {
            this.name = name;
            this.startDate = LocalDate.parse(startDate);
        }

        abstract int getValidityDays();

        LocalDate getRenewalDate() {
            return startDate.plusDays(getValidityDays());
        }
    }

    static class BasicPlan extends StreamingPlan {
        BasicPlan(String name, String startDate) { super(name, startDate); }
        @Override int getValidityDays() { return 30; }
    }

    static class StandardPlan extends StreamingPlan {
        StandardPlan(String name, String startDate) { super(name, startDate); }
        @Override int getValidityDays() { return 90; }
    }

    static class PremiumPlan extends StreamingPlan {
        PremiumPlan(String name, String startDate) { super(name, startDate); }
        @Override int getValidityDays() { return 365; }
    }

    public static void runStreamingPlanDemo() {
        System.out.println("\n--- Problem 5: Streaming Plan Renewal Reminder ---");
        List<StreamingPlan> subscribers = List.of(
            new BasicPlan("Asha", "2024-01-15"),
            new StandardPlan("Ravi", "2024-02-01"),
            new PremiumPlan("Neha", "2024-03-10"),
            new BasicPlan("Kiran", "2024-12-20")
        );
        for (StreamingPlan s : subscribers) {
            System.out.printf("%s: %s%n", s.name, s.getRenewalDate());
        }
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("    WEEK 8 ASSIGNMENT - POLYMORPHISM");
            System.out.println("========================================");
            System.out.println("1. The Canteen Billing Counter");
            System.out.println("2. The Campus Parking Charge Calculator");
            System.out.println("3. The Hostel Electricity Bill");
            System.out.println("4. The Festival Bonus Calculator");
            System.out.println("5. Streaming Plan Renewal Reminder");
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
                case 1 -> runCanteenBillingDemo();
                case 2 -> runParkingChargeDemo();
                case 3 -> runHostelBillDemo();
                case 4 -> runFestivalBonusDemo();
                case 5 -> runStreamingPlanDemo();
                case 6 -> {
                    runCanteenBillingDemo();
                    runParkingChargeDemo();
                    runHostelBillDemo();
                    runFestivalBonusDemo();
                    runStreamingPlanDemo();
                }
                case 0 -> {
                    System.out.println("Exiting Week 8 Assignment. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
