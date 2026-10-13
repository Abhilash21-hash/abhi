import java.util.Scanner;

public class week2_practice {

    // ==========================================
    // Problem 1: Vowel & Consonant Counter
    // ==========================================
    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = Character.toLowerCase(text.charAt(i));
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void runVowelConsonant(Scanner sc) {
        System.out.println("\n--- Problem 1: Vowel & Consonant Counter ---");
        System.out.print("Enter text [e.g. Java Programming]: ");
        String text = sc.nextLine().trim();
        if (text.isEmpty()) text = "Java Programming";
        countVowelsAndConsonants(text);
    }

    // ==========================================
    // Problem 2: CSV Student Record Parser
    // ==========================================
    public static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name = fields[0].trim();
        String roll = fields[1].trim();
        String dept = fields[2].trim();

        System.out.printf("Name: %s | Roll No: %s | Dept: %s%n", name, roll, dept);
    }

    public static void runStudentRecord(Scanner sc) {
        System.out.println("\n--- Problem 2: CSV Student Record Parser ---");
        System.out.println("Sample 1: Ananya Verma,RA2211003010123,CSE");
        parseStudentRecord("Ananya Verma,RA2211003010123,CSE");

        System.out.println("\nSample 2: Ananya Verma,CSE");
        parseStudentRecord("Ananya Verma,CSE");
    }

    // ==========================================
    // Problem 3: File Extension Validator
    // ==========================================
    public static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }

        String ext = filename.substring(dotIndex + 1);
        if (ext.equalsIgnoreCase("pdf") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("zip")) {
            return "Accepted";
        }
        return "Rejected — invalid file type";
    }

    public static void runFileExtension(Scanner sc) {
        System.out.println("\n--- Problem 3: File Extension Validator ---");
        System.out.print("Enter filename [e.g. Assignment1.PDF]: ");
        String fn = sc.nextLine().trim();
        if (fn.isEmpty()) fn = "Assignment1.PDF";
        System.out.println("Result: " + validateFileExtension(fn));
    }

    // ==========================================
    // Problem 4: Masked Phone Number Formatter
    // ==========================================
    public static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXX");
        sb.append("-");
        sb.append(phone.substring(6));
        return sb.toString();
    }

    public static void runMaskPhone(Scanner sc) {
        System.out.println("\n--- Problem 4: Masked Phone Number Formatter ---");
        System.out.print("Enter 10-digit phone number [e.g. 9876543210]: ");
        String phone = sc.nextLine().trim();
        if (phone.isEmpty()) phone = "9876543210";
        System.out.println("Result: " + maskPhoneNumber(phone));
    }

    // ==========================================
    // Problem 5: Bank Transaction Reference Validator
    // ==========================================
    public static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed.toUpperCase();
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: wrong length (must be 14 characters)";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: reference body must be digits";
            }
        }

        String bankCode = reference.substring(0, 3);
        String dd = reference.substring(3, 5);
        String mm = reference.substring(5, 7);
        String yy = reference.substring(7, 9);
        String seq = reference.substring(9, 14);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ")
          .append(dd).append("/").append(mm).append("/").append(yy)
          .append(" | SEQ: ").append(seq);
        return sb.toString();
    }

    public static void runBankReference(Scanner sc) {
        System.out.println("\n--- Problem 5: Bank Transaction Reference Generator & Validator ---");
        System.out.print("Enter reference [e.g. \" hdf03022600042 \"]: ");
        String ref = sc.nextLine();
        if (ref.trim().isEmpty()) ref = " hdf03022600042 ";
        String normalized = normalizeReference(ref);
        System.out.println("Normalized: " + normalized);
        System.out.println("Formatted:  " + validateAndFormat(normalized));
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("     WEEK 2 PRACTICE - LIVE CODING");
            System.out.println("========================================");
            System.out.println("1. Vowel & Consonant Counter");
            System.out.println("2. CSV Student Record Parser");
            System.out.println("3. File Extension Validator");
            System.out.println("4. Masked Phone Number Formatter");
            System.out.println("5. Bank Transaction Reference Validator");
            System.out.println("6. Run All Demos");
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
                case 1 -> runVowelConsonant(sc);
                case 2 -> runStudentRecord(sc);
                case 3 -> runFileExtension(sc);
                case 4 -> runMaskPhone(sc);
                case 5 -> runBankReference(sc);
                case 6 -> {
                    runVowelConsonant(sc);
                    runStudentRecord(sc);
                    runFileExtension(sc);
                    runMaskPhone(sc);
                    runBankReference(sc);
                }
                case 0 -> {
                    System.out.println("Exiting Week 2 Practice. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
