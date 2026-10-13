import java.time.LocalDate;
import java.util.*;

public class week8_practice {

    // ==========================================
    // Problem 1: Payment System Fee Calculation
    // ==========================================
    abstract static class Payment {
        double amount;

        Payment(double amount) {
            this.amount = amount;
        }

        abstract String getType();
        abstract double calculateAdjustedAmount();
    }

    static class CardPayment extends Payment {
        CardPayment(double amount) { super(amount); }
        @Override String getType() { return "CARD"; }
        @Override double calculateAdjustedAmount() { return amount * 1.02; }
    }

    static class WalletPayment extends Payment {
        WalletPayment(double amount) { super(amount); }
        @Override String getType() { return "WALLET"; }
        @Override double calculateAdjustedAmount() { return amount * 1.01; }
    }

    static class BankTransferPayment extends Payment {
        BankTransferPayment(double amount) { super(amount); }
        @Override String getType() { return "BANKTRANSFER"; }
        @Override double calculateAdjustedAmount() { return amount; }
    }

    public static void runPaymentDemo() {
        System.out.println("\n--- Problem 1: Payment System Fee Calculation ---");
        List<Payment> list = List.of(
            new CardPayment(1000),
            new WalletPayment(500),
            new BankTransferPayment(2000)
        );
        double total = 0;
        for (Payment p : list) {
            double adj = p.calculateAdjustedAmount();
            System.out.printf("%s: %.2f%n", p.getType(), adj);
            total += adj;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ==========================================
    // Problem 2: Library Item Due Date Calculator
    // ==========================================
    abstract static class LibraryItem {
        String title;
        LibraryItem(String title) { this.title = title; }
        abstract int getLoanDays();
        LocalDate getDueDate(LocalDate fromDate) {
            return fromDate.plusDays(getLoanDays());
        }
    }

    static class BookItem extends LibraryItem {
        BookItem(String title) { super(title); }
        @Override int getLoanDays() { return 14; }
    }

    static class DvdItem extends LibraryItem {
        DvdItem(String title) { super(title); }
        @Override int getLoanDays() { return 7; }
    }

    static class MagazineItem extends LibraryItem {
        MagazineItem(String title) { super(title); }
        @Override int getLoanDays() { return 3; }
    }

    public static void runLibraryDueDateDemo() {
        System.out.println("\n--- Problem 2: Library Item Due Date Calculator ---");
        LocalDate baseDate = LocalDate.parse("2023-10-26");
        List<LibraryItem> items = List.of(
            new BookItem("1984"),
            new DvdItem("The Matrix"),
            new MagazineItem("Forbes Issue 500")
        );
        for (LibraryItem item : items) {
            System.out.printf("%s: %s%n", item.title, item.getDueDate(baseDate));
        }
    }

    // ==========================================
    // Problem 3: Delivery Fee Calculator
    // ==========================================
    abstract static class Delivery {
        double weight, distance;
        Delivery(double weight, double distance) {
            this.weight = weight;
            this.distance = distance;
        }
        abstract String getType();
        abstract double calculateFee();
    }

    static class StandardDelivery extends Delivery {
        StandardDelivery(double weight, double distance) { super(weight, distance); }
        @Override String getType() { return "STANDARD"; }
        @Override double calculateFee() { return 5.0 + (0.50 * weight) + (0.10 * distance); }
    }

    static class ExpressDelivery extends Delivery {
        ExpressDelivery(double weight, double distance) { super(weight, distance); }
        @Override String getType() { return "EXPRESS"; }
        @Override double calculateFee() { return 15.0 + (1.00 * weight) + (0.20 * distance); }
    }

    static class InternationalDelivery extends Delivery {
        double customsFee;
        InternationalDelivery(double weight, double distance, double customsFee) {
            super(weight, distance);
            this.customsFee = customsFee;
        }
        @Override String getType() { return "INTERNATIONAL"; }
        @Override double calculateFee() {
            return 25.0 + (2.00 * weight) + (0.50 * distance) + customsFee;
        }
    }

    public static void runDeliveryDemo() {
        System.out.println("\n--- Problem 3: Delivery Fee Calculator ---");
        List<Delivery> deliveries = List.of(
            new StandardDelivery(10, 50),
            new ExpressDelivery(5, 20),
            new InternationalDelivery(20, 100, 30)
        );
        double total = 0;
        for (Delivery d : deliveries) {
            double fee = d.calculateFee();
            System.out.printf("%s: %.2f%n", d.getType(), fee);
            total += fee;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ==========================================
    // Problem 4: Examination Question Grader
    // ==========================================
    abstract static class ExamQuestion {
        String questionText, correctAnswer, studentAnswer;
        int points;

        ExamQuestion(String questionText, String correctAnswer, String studentAnswer, int points) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }

        abstract String getType();
        abstract double evaluateScore();
    }

    static class McqQuestion extends ExamQuestion {
        McqQuestion(String q, String ca, String sa, int p) { super(q, ca, sa, p); }
        @Override String getType() { return "MCQ"; }
        @Override double evaluateScore() {
            return studentAnswer.equalsIgnoreCase(correctAnswer) ? points : 0.0;
        }
    }

    static class TrueFalseQuestion extends ExamQuestion {
        TrueFalseQuestion(String q, String ca, String sa, int p) { super(q, ca, sa, p); }
        @Override String getType() { return "TF"; }
        @Override double evaluateScore() {
            return studentAnswer.equalsIgnoreCase(correctAnswer) ? points : 0.0;
        }
    }

    static class EssayQuestion extends ExamQuestion {
        EssayQuestion(String q, String ca, String sa, int p) { super(q, ca, sa, p); }
        @Override String getType() { return "ESSAY"; }
        @Override double evaluateScore() {
            String[] keywords = correctAnswer.split(",");
            int matches = 0;
            String lowerAnswer = studentAnswer.toLowerCase();
            for (String kw : keywords) {
                if (lowerAnswer.contains(kw.trim().toLowerCase())) {
                    matches++;
                }
            }
            if (matches >= 2) return points * 0.75;
            if (matches == 1) return points * 0.50;
            return 0.0;
        }
    }

    public static void runExamGraderDemo() {
        System.out.println("\n--- Problem 4: Examination Question Grader ---");
        List<ExamQuestion> questions = List.of(
            new McqQuestion("What is the capital of France?", "Paris", "Paris", 10),
            new TrueFalseQuestion("The Earth is flat?", "False", "True", 5),
            new EssayQuestion("Name two primary OOP principles.", "Inheritance, Polymorphism, Encapsulation", "Polymorphism is one.", 20),
            new EssayQuestion("Describe abstraction and composition.", "Abstraction, Composition", "I talked about abstraction.", 15)
        );
        double total = 0;
        for (ExamQuestion q : questions) {
            double s = q.evaluateScore();
            System.out.printf("%s: %.2f%n", q.getType(), s);
            total += s;
        }
        System.out.printf("Total Score: %.2f%n", total);
    }

    // ==========================================
    // Problem 5: Public Transport Fare Calculator
    // ==========================================
    abstract static class Transport {
        double distance;
        Transport(double distance) { this.distance = distance; }
        abstract String getType();
        abstract double calculateFare();
    }

    static class BusTransport extends Transport {
        BusTransport(double distance) { super(distance); }
        @Override String getType() { return "BUS"; }
        @Override double calculateFare() { return Math.min(10.0, 2.0 + (0.10 * distance)); }
    }

    static class TrainTransport extends Transport {
        TrainTransport(double distance) { super(distance); }
        @Override String getType() { return "TRAIN"; }
        @Override double calculateFare() { return 3.0 + (0.15 * distance); }
    }

    static class MetroTransport extends Transport {
        double peakHourFactor;
        MetroTransport(double distance, double peakHourFactor) {
            super(distance);
            this.peakHourFactor = peakHourFactor;
        }
        @Override String getType() { return "METRO"; }
        @Override double calculateFare() {
            return (1.50 + (0.20 * distance)) * peakHourFactor;
        }
    }

    public static void runTransportFareDemo() {
        System.out.println("\n--- Problem 5: Public Transport Fare Calculator ---");
        List<Transport> journeys = List.of(
            new BusTransport(15),
            new TrainTransport(50),
            new MetroTransport(10, 1.5)
        );
        double total = 0;
        for (Transport t : journeys) {
            double fare = t.calculateFare();
            System.out.printf("%s: %.2f%n", t.getType(), fare);
            total += fare;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("     WEEK 8 PRACTICE - POLYMORPHISM");
            System.out.println("========================================");
            System.out.println("1. Payment System Fee Calculation");
            System.out.println("2. Library Item Due Date Calculator");
            System.out.println("3. Delivery Fee Calculator");
            System.out.println("4. Examination Question Grader");
            System.out.println("5. Public Transport Fare Calculator");
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
                case 1 -> runPaymentDemo();
                case 2 -> runLibraryDueDateDemo();
                case 3 -> runDeliveryDemo();
                case 4 -> runExamGraderDemo();
                case 5 -> runTransportFareDemo();
                case 6 -> {
                    runPaymentDemo();
                    runLibraryDueDateDemo();
                    runDeliveryDemo();
                    runExamGraderDemo();
                    runTransportFareDemo();
                }
                case 0 -> {
                    System.out.println("Exiting Week 8 Practice. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
