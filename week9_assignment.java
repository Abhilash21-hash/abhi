import java.util.*;

public class week9_assignment {

    // ==========================================
    // Problem 1: Garden Plot Area Report
    // ==========================================
    abstract static class Plot {
        String owner;
        String shape;

        Plot(String owner, String shape) {
            this.owner = owner;
            this.shape = shape;
        }

        abstract double getArea();

        void printDetails() {
            System.out.printf("%s (%s): %.2f%n", owner, shape, getArea());
        }
    }

    static class CirclePlot extends Plot {
        double radius;
        CirclePlot(String owner, double radius) {
            super(owner, "CIRCLE");
            this.radius = radius;
        }
        @Override double getArea() { return Math.PI * radius * radius; }
    }

    static class RectanglePlot extends Plot {
        double length, width;
        RectanglePlot(String owner, double length, double width) {
            super(owner, "RECTANGLE");
            this.length = length;
            this.width = width;
        }
        @Override double getArea() { return length * width; }
    }

    static class TrianglePlot extends Plot {
        double base, height;
        TrianglePlot(String owner, double base, double height) {
            super(owner, "TRIANGLE");
            this.base = base;
            this.height = height;
        }
        @Override double getArea() { return 0.5 * base * height; }
    }

    public static void runGardenPlotDemo() {
        System.out.println("\n--- Problem 1: Garden Plot Area Report ---");
        List<Plot> plots = List.of(
            new CirclePlot("Asha", 5),
            new RectanglePlot("Ravi", 4, 6),
            new TrianglePlot("Neha", 10, 3)
        );
        double totalArea = 0;
        for (Plot p : plots) {
            p.printDetails();
            totalArea += p.getArea();
        }
        System.out.printf("Total Area: %.2f%n", totalArea);
    }

    // ==========================================
    // Problem 2: Weekly Staff Pay
    // ==========================================
    abstract static class Staff {
        String name;
        Staff(String name) { this.name = name; }
        abstract double getPay();
    }

    static class FullTimeStaff extends Staff {
        double weeklySalary;
        FullTimeStaff(String name, double weeklySalary) {
            super(name);
            this.weeklySalary = weeklySalary;
        }
        @Override double getPay() { return weeklySalary; }
    }

    static class HourlyStaff extends Staff {
        double hours, rate;
        HourlyStaff(String name, double hours, double rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }
        @Override double getPay() {
            if (hours <= 40) return hours * rate;
            return (40 * rate) + ((hours - 40) * 1.5 * rate);
        }
    }

    static class InternStaff extends Staff {
        double stipend;
        InternStaff(String name, double stipend) {
            super(name);
            this.stipend = stipend;
        }
        @Override double getPay() { return stipend; }
    }

    public static void runWeeklyStaffPayDemo() {
        System.out.println("\n--- Problem 2: Weekly Staff Pay ---");
        List<Staff> staffList = List.of(
            new FullTimeStaff("Asha", 12000),
            new HourlyStaff("Ravi", 45, 200),
            new InternStaff("Neha", 5000)
        );
        double totalPayroll = 0;
        for (Staff s : staffList) {
            double pay = s.getPay();
            System.out.printf("%s: %.2f%n", s.name, pay);
            totalPayroll += pay;
        }
        System.out.printf("Total Payroll: %.2f%n", totalPayroll);
    }

    // ==========================================
    // Problem 3: Library Late Fine Counter
    // ==========================================
    abstract static class LibraryItem {
        String title;
        int daysLate;
        LibraryItem(String title, int daysLate) {
            this.title = title;
            this.daysLate = daysLate;
        }
        abstract double getFine();
    }

    static class BookItem extends LibraryItem {
        BookItem(String title, int daysLate) { super(title, daysLate); }
        @Override double getFine() { return 2.0 * daysLate; }
    }

    static class DvdItem extends LibraryItem {
        DvdItem(String title, int daysLate) { super(title, daysLate); }
        @Override double getFine() { return Math.min(50.0, 5.0 * daysLate); }
    }

    static class MagazineItem extends LibraryItem {
        MagazineItem(String title, int daysLate) { super(title, daysLate); }
        @Override double getFine() { return 1.0 * daysLate; }
    }

    public static void runLibraryFineDemo() {
        System.out.println("\n--- Problem 3: Library Late Fine Counter ---");
        List<LibraryItem> items = List.of(
            new BookItem("Algebra", 4),
            new DvdItem("Inception", 12),
            new MagazineItem("Sports", 3)
        );
        double totalFines = 0;
        for (LibraryItem item : items) {
            double fine = item.getFine();
            System.out.printf("%s: %.2f%n", item.title, fine);
            totalFines += fine;
        }
        System.out.printf("Total Fines: %.2f%n", totalFines);
    }

    // ==========================================
    // Problem 4: Electricity Connection Billing
    // ==========================================
    abstract static class ElectricityConnection {
        int units;
        ElectricityConnection(int units) { this.units = units; }
        abstract String getType();
        abstract double calculateBill();
    }

    static class HomeConnection extends ElectricityConnection {
        HomeConnection(int units) { super(units); }
        @Override String getType() { return "HOME"; }
        @Override double calculateBill() {
            if (units <= 100) return units * 5.0;
            return (100 * 5.0) + ((units - 100) * 7.0);
        }
    }

    static class ShopConnection extends ElectricityConnection {
        ShopConnection(int units) { super(units); }
        @Override String getType() { return "SHOP"; }
        @Override double calculateBill() { return (units * 8.0) + 100.0; }
    }

    static class FactoryConnection extends ElectricityConnection {
        FactoryConnection(int units) { super(units); }
        @Override String getType() { return "FACTORY"; }
        @Override double calculateBill() { return Math.max(1000.0, units * 6.0); }
    }

    public static void runElectricityBillingDemo() {
        System.out.println("\n--- Problem 4: Electricity Connection Billing ---");
        List<ElectricityConnection> connections = List.of(
            new HomeConnection(150),
            new ShopConnection(90),
            new FactoryConnection(120)
        );
        double totalBill = 0;
        for (ElectricityConnection conn : connections) {
            double bill = conn.calculateBill();
            System.out.printf("%s: %.2f%n", conn.getType(), bill);
            totalBill += bill;
        }
        System.out.printf("Total: %.2f%n", totalBill);
    }

    // ==========================================
    // Problem 5: Travel Booking with Common Fee
    // ==========================================
    abstract static class TravelBooking {
        static final double BOOKING_FEE = 50.0;
        double distanceKm;
        TravelBooking(double distanceKm) { this.distanceKm = distanceKm; }
        abstract String getMode();
        abstract double getBaseFare();
        double getTotalFare() { return getBaseFare() + BOOKING_FEE; }
    }

    static class BusBooking extends TravelBooking {
        BusBooking(double d) { super(d); }
        @Override String getMode() { return "BUS"; }
        @Override double getBaseFare() { return 2.0 * distanceKm; }
    }

    static class TrainBooking extends TravelBooking {
        TrainBooking(double d) { super(d); }
        @Override String getMode() { return "TRAIN"; }
        @Override double getBaseFare() { return 1.5 * distanceKm; }
    }

    static class FlightBooking extends TravelBooking {
        FlightBooking(double d) { super(d); }
        @Override String getMode() { return "FLIGHT"; }
        @Override double getBaseFare() { return 2500.0 + (4.0 * distanceKm); }
    }

    public static void runTravelBookingDemo() {
        System.out.println("\n--- Problem 5: Travel Booking with a Common Fee ---");
        List<TravelBooking> bookings = List.of(
            new BusBooking(200),
            new TrainBooking(300),
            new FlightBooking(500)
        );
        for (TravelBooking b : bookings) {
            System.out.printf("%s: %.2f%n", b.getMode(), b.getTotalFare());
        }
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println(" WEEK 9 ASSIGNMENT - ABSTRACTION & INTERFACES");
            System.out.println("========================================");
            System.out.println("1. Garden Plot Area Report");
            System.out.println("2. Weekly Staff Pay");
            System.out.println("3. Library Late Fine Counter");
            System.out.println("4. Electricity Connection Billing");
            System.out.println("5. Travel Booking with Common Fee");
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
                case 1 -> runGardenPlotDemo();
                case 2 -> runWeeklyStaffPayDemo();
                case 3 -> runLibraryFineDemo();
                case 4 -> runElectricityBillingDemo();
                case 5 -> runTravelBookingDemo();
                case 6 -> {
                    runGardenPlotDemo();
                    runWeeklyStaffPayDemo();
                    runLibraryFineDemo();
                    runElectricityBillingDemo();
                    runTravelBookingDemo();
                }
                case 0 -> {
                    System.out.println("Exiting Week 9 Assignment. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
