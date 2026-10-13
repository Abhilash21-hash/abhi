import java.util.*;

public class week9_practice {

    // ==========================================
    // Problem 1: Movie Ticket Counter
    // Convenience fee: 20 per ticket
    // ==========================================
    abstract static class Ticket {
        int count;
        static final double CONVENIENCE_FEE = 20.0;

        Ticket(int count) { this.count = count; }

        abstract String getSeatType();
        abstract double getBasePrice();

        double calculateTotal() {
            return count * (getBasePrice() + CONVENIENCE_FEE);
        }
    }

    static class RegularTicket extends Ticket {
        RegularTicket(int count) { super(count); }
        @Override String getSeatType() { return "REGULAR"; }
        @Override double getBasePrice() { return 150.0; }
    }

    static class PremiumTicket extends Ticket {
        PremiumTicket(int count) { super(count); }
        @Override String getSeatType() { return "PREMIUM"; }
        @Override double getBasePrice() { return 250.0; }
    }

    static class ReclinerTicket extends Ticket {
        ReclinerTicket(int count) { super(count); }
        @Override String getSeatType() { return "RECLINER"; }
        @Override double getBasePrice() { return 400.0; }
    }

    public static void runMovieTicketDemo() {
        System.out.println("\n--- Problem 1: Movie Ticket Counter ---");
        List<Ticket> bookings = List.of(
            new RegularTicket(3),
            new PremiumTicket(2),
            new ReclinerTicket(1)
        );
        double total = 0;
        for (Ticket t : bookings) {
            double amt = t.calculateTotal();
            System.out.printf("%s: %.2f%n", t.getSeatType(), amt);
            total += amt;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ==========================================
    // Problem 2: Parcel Shipping Desk
    // Interface Insurable
    // ==========================================
    interface Insurable {
        double calculateInsurance();
    }

    abstract static class Parcel {
        double weightKg, declaredValue;

        Parcel(double weightKg, double declaredValue) {
            this.weightKg = weightKg;
            this.declaredValue = declaredValue;
        }

        abstract String getType();
        abstract double calculateShippingCharge();
    }

    static class StandardParcel extends Parcel {
        StandardParcel(double w, double dv) { super(w, dv); }
        @Override String getType() { return "STANDARD"; }
        @Override double calculateShippingCharge() { return 40.0 + (10.0 * weightKg); }
    }

    static class ExpressParcel extends Parcel implements Insurable {
        ExpressParcel(double w, double dv) { super(w, dv); }
        @Override String getType() { return "EXPRESS"; }
        @Override double calculateShippingCharge() { return 80.0 + (15.0 * weightKg); }
        @Override public double calculateInsurance() { return declaredValue * 0.02; }
    }

    static class FragileParcel extends Parcel implements Insurable {
        FragileParcel(double w, double dv) { super(w, dv); }
        @Override String getType() { return "FRAGILE"; }
        @Override double calculateShippingCharge() { return (40.0 + (10.0 * weightKg)) + 50.0; }
        @Override public double calculateInsurance() { return declaredValue * 0.02; }
    }

    public static void runParcelShippingDemo() {
        System.out.println("\n--- Problem 2: Parcel Shipping Desk ---");
        List<Parcel> parcels = List.of(
            new StandardParcel(3, 500),
            new ExpressParcel(2, 1000),
            new FragileParcel(4, 2000)
        );
        double grandTotal = 0;
        for (Parcel p : parcels) {
            double charge = p.calculateShippingCharge();
            double ins = (p instanceof Insurable) ? ((Insurable) p).calculateInsurance() : 0.0;
            double total = charge + ins;
            grandTotal += total;
            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    p.getType(), charge, ins, total);
        }
        System.out.printf("Grand Total: %.2f%n", grandTotal);
    }

    // ==========================================
    // Problem 3: College Fee Counter
    // ==========================================
    interface UsesBus {
        default double getTransportFee() { return 12000.0; }
    }

    abstract static class CollegeStudent {
        String name;
        CollegeStudent(String name) { this.name = name; }
        abstract double calculateFee();
    }

    static class DayScholar extends CollegeStudent implements UsesBus {
        DayScholar(String name) { super(name); }
        @Override double calculateFee() { return 40000.0 + getTransportFee(); }
    }

    static class Hosteller extends CollegeStudent {
        Hosteller(String name) { super(name); }
        @Override double calculateFee() { return 40000.0 + 60000.0; }
    }

    static class ScholarStudent extends CollegeStudent implements UsesBus {
        ScholarStudent(String name) { super(name); }
        @Override double calculateFee() { return 20000.0 + getTransportFee(); }
    }

    public static void runCollegeFeeDemo() {
        System.out.println("\n--- Problem 3: College Fee Counter ---");
        List<CollegeStudent> students = List.of(
            new DayScholar("Asha"),
            new Hosteller("Ravi"),
            new ScholarStudent("Neha")
        );
        double total = 0;
        for (CollegeStudent s : students) {
            double fee = s.calculateFee();
            System.out.printf("%s: %.2f%n", s.name, fee);
            total += fee;
        }
        System.out.printf("Total Collected: %.2f%n", total);
    }

    // ==========================================
    // Problem 4: City Cab Fare Meter
    // ==========================================
    interface NightServiced {}

    abstract static class Cab {
        double distanceKm;
        String time; // "DAY" or "NIGHT"

        Cab(double distanceKm, String time) {
            this.distanceKm = distanceKm;
            this.time = time;
        }

        abstract String getCabType();
        abstract double getRatePerKm();

        void processTrip() {
            if (time.equalsIgnoreCase("NIGHT") && !(this instanceof NightServiced)) {
                System.out.printf("%s: night service not available%n", getCabType());
                return;
            }

            double baseFare = Math.max(100.0, distanceKm * getRatePerKm());
            if (time.equalsIgnoreCase("NIGHT")) {
                baseFare *= 1.20;
            }
            System.out.printf("%s: %.2f%n", getCabType(), baseFare);
        }

        double getCompletedFare() {
            if (time.equalsIgnoreCase("NIGHT") && !(this instanceof NightServiced)) {
                return 0.0;
            }
            double baseFare = Math.max(100.0, distanceKm * getRatePerKm());
            if (time.equalsIgnoreCase("NIGHT")) {
                baseFare *= 1.20;
            }
            return baseFare;
        }
    }

    static class MiniCab extends Cab {
        MiniCab(double km, String time) { super(km, time); }
        @Override String getCabType() { return "MINI"; }
        @Override double getRatePerKm() { return 10.0; }
    }

    static class SedanCab extends Cab implements NightServiced {
        SedanCab(double km, String time) { super(km, time); }
        @Override String getCabType() { return "SEDAN"; }
        @Override double getRatePerKm() { return 14.0; }
    }

    static class SuvCab extends Cab implements NightServiced {
        SuvCab(double km, String time) { super(km, time); }
        @Override String getCabType() { return "SUV"; }
        @Override double getRatePerKm() { return 18.0; }
    }

    public static void runCityCabDemo() {
        System.out.println("\n--- Problem 4: City Cab Fare Meter ---");
        List<Cab> trips = List.of(
            new MiniCab(8, "DAY"),
            new SedanCab(10, "NIGHT"),
            new SuvCab(20, "DAY"),
            new MiniCab(5, "NIGHT")
        );
        double total = 0;
        for (Cab c : trips) {
            c.processTrip();
            total += c.getCompletedFare();
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ==========================================
    // Problem 5: Home Appliance Energy Report
    // ==========================================
    interface SaverModeSupported {}

    abstract static class Appliance {
        int hours;
        boolean saverRequested;

        Appliance(int hours, boolean saverRequested) {
            this.hours = hours;
            this.saverRequested = saverRequested;
        }

        abstract String getName();
        abstract double getPowerWatts();

        void printReport() {
            if (saverRequested && !(this instanceof SaverModeSupported)) {
                System.out.printf("%s: saver mode not supported%n", getName());
                return;
            }
            double units = (getPowerWatts() * hours) / 1000.0;
            if (saverRequested) {
                units *= 0.75; // 25% reduction
            }
            double cost = units * 8.0;
            System.out.printf("%s: Units=%.2f Cost=%.2f%n", getName(), units, cost);
        }

        double getCost() {
            if (saverRequested && !(this instanceof SaverModeSupported)) return 0.0;
            double units = (getPowerWatts() * hours) / 1000.0;
            if (saverRequested) units *= 0.75;
            return units * 8.0;
        }
    }

    static class FridgeAppliance extends Appliance {
        FridgeAppliance(int h, boolean s) { super(h, s); }
        @Override String getName() { return "FRIDGE"; }
        @Override double getPowerWatts() { return 150.0; }
    }

    static class AcAppliance extends Appliance implements SaverModeSupported {
        AcAppliance(int h, boolean s) { super(h, s); }
        @Override String getName() { return "AC"; }
        @Override double getPowerWatts() { return 1500.0; }
    }

    static class TvAppliance extends Appliance {
        TvAppliance(int h, boolean s) { super(h, s); }
        @Override String getName() { return "TV"; }
        @Override double getPowerWatts() { return 100.0; }
    }

    static class WasherAppliance extends Appliance implements SaverModeSupported {
        WasherAppliance(int h, boolean s) { super(h, s); }
        @Override String getName() { return "WASHER"; }
        @Override double getPowerWatts() { return 500.0; }
    }

    public static void runApplianceReportDemo() {
        System.out.println("\n--- Problem 5: Home Appliance Energy Report ---");
        List<Appliance> appliances = List.of(
            new FridgeAppliance(24, false),
            new AcAppliance(8, true),
            new TvAppliance(5, false),
            new WasherAppliance(2, true)
        );
        double totalCost = 0;
        for (Appliance a : appliances) {
            a.printReport();
            totalCost += a.getCost();
        }
        System.out.printf("Total Cost: %.2f%n", totalCost);
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("     WEEK 9 PRACTICE - OOP DESIGNS");
            System.out.println("========================================");
            System.out.println("1. Movie Ticket Counter");
            System.out.println("2. Parcel Shipping Desk");
            System.out.println("3. College Fee Counter");
            System.out.println("4. City Cab Fare Meter");
            System.out.println("5. Home Appliance Energy Report");
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
                case 1 -> runMovieTicketDemo();
                case 2 -> runParcelShippingDemo();
                case 3 -> runCollegeFeeDemo();
                case 4 -> runCityCabDemo();
                case 5 -> runApplianceReportDemo();
                case 6 -> {
                    runMovieTicketDemo();
                    runParcelShippingDemo();
                    runCollegeFeeDemo();
                    runCityCabDemo();
                    runApplianceReportDemo();
                }
                case 0 -> {
                    System.out.println("Exiting Week 9 Practice. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
