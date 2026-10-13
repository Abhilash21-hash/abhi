import java.util.*;

public class week2_assignment {

    // ==========================================
    // Problem 1: ATM PIN Length Validator
    // ==========================================
    public static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void runPinValidator(Scanner sc) {
        System.out.println("\n--- Problem 1: ATM PIN Length Validator ---");
        System.out.println("Sample 1: \"482\"");
        checkPinLength("482");
        System.out.println("Sample 2: \"4820\"");
        checkPinLength("4820");
    }

    // ==========================================
    // Problem 2: Word Reversal Encoder
    // ==========================================
    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            result.append(sb.reverse());
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static void runWordReversal(Scanner sc) {
        System.out.println("\n--- Problem 2: Word Reversal Encoder ---");
        System.out.print("Enter sentence [e.g. \"hello club\"]: ");
        String text = sc.nextLine().trim();
        if (text.isEmpty()) text = "hello club";
        System.out.println("Reversed: " + reverseEachWord(text));
    }

    // ==========================================
    // Problem 3: Product Inventory CSV Parser
    // ==========================================
    public static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String product = fields[0].trim();
        String sku = fields[1].trim();
        String qty = fields[2].trim();

        System.out.printf("Product: %s | SKU: %s | Qty: %s%n", product, sku, qty);
    }

    public static void runInventoryParser(Scanner sc) {
        System.out.println("\n--- Problem 3: Product Inventory CSV Parser ---");
        System.out.println("Sample 1: Wireless Mouse,WM-2201,150");
        parseInventoryRecord("Wireless Mouse,WM-2201,150");
        System.out.println("Sample 2: Wireless Mouse,150");
        parseInventoryRecord("Wireless Mouse,150");
    }

    // ==========================================
    // Problem 4: Library ISBN Normalizer & Validator
    // ==========================================
    public static String normalizeCode(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed.toUpperCase();
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length (must be 13 characters)";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ")
          .append(year).append(" | CATALOG: ").append(catalog);
        return sb.toString();
    }

    public static void runIsbnValidator(Scanner sc) {
        System.out.println("\n--- Problem 4: Library ISBN Normalizer & Validator ---");
        System.out.println("Sample 1: \" pen2026004251 \"");
        String norm1 = normalizeCode(" pen2026004251 ");
        System.out.println(validateAndFormat(norm1));

        System.out.println("\nSample 2: \"12N2026004251\"");
        String norm2 = normalizeCode("12N2026004251");
        System.out.println(validateAndFormat(norm2));
    }

    // ==========================================
    // Problem 5: Stop-Word-Filtered Word Frequency Report
    // ==========================================
    public static void printFilteredWordFrequency(String feedback) {
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "was", "and", "a", "is", "of", "in"));

        // Normalize to lowercase, strip punctuation like periods and commas
        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");
        String[] words = cleaned.trim().split("\\s+");

        Map<String, Integer> freqMap = new HashMap<>();
        for (String w : words) {
            if (w.isEmpty() || stopWords.contains(w)) continue;
            freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
        }

        // Sort descending by count
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freqMap.entrySet());
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void runWordFrequency(Scanner sc) {
        System.out.println("\n--- Problem 5: Stop-Word-Filtered Word Frequency Report ---");
        System.out.println("Sample text: \"The mentor was great, the session was great and clear.\"");
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("        WEEK 2 CODING ASSIGNMENT");
            System.out.println("========================================");
            System.out.println("1. ATM PIN Length Validator");
            System.out.println("2. Word Reversal Encoder");
            System.out.println("3. Product Inventory CSV Parser");
            System.out.println("4. Library ISBN Normalizer & Validator");
            System.out.println("5. Stop-Word-Filtered Word Frequency Report");
            System.out.println("6. Run All Samples");
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
                case 1 -> runPinValidator(sc);
                case 2 -> runWordReversal(sc);
                case 3 -> runInventoryParser(sc);
                case 4 -> runIsbnValidator(sc);
                case 5 -> runWordFrequency(sc);
                case 6 -> {
                    runPinValidator(sc);
                    runWordReversal(sc);
                    runInventoryParser(sc);
                    runIsbnValidator(sc);
                    runWordFrequency(sc);
                }
                case 0 -> {
                    System.out.println("Exiting Week 2 Assignment. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
