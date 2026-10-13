import java.util.Arrays;
import java.util.Scanner;

public class week7_assignment {

    // ==========================================
    // Problem 1: The Health Bar
    // Clamping values: [0, maxHealth]
    // ==========================================
    public static class Character {
        private final int maxHealth;
        private int currentHealth;

        public Character(int maxHealth) {
            this.maxHealth = Math.max(1, maxHealth);
            this.currentHealth = this.maxHealth;
        }

        public void takeDamage(int amount) {
            if (amount > 0) {
                currentHealth = Math.max(0, currentHealth - amount);
            }
        }

        public void heal(int amount) {
            if (amount > 0) {
                currentHealth = Math.min(maxHealth, currentHealth + amount);
            }
        }

        public int getHealth() {
            return currentHealth;
        }
    }

    public static void runHealthBarDemo() {
        System.out.println("\n--- Problem 1: The Health Bar ---");
        Character c = new Character(100);
        c.takeDamage(30);
        System.out.println("c.takeDamage(30) -> health = " + c.getHealth());
        c.heal(50);
        System.out.println("c.heal(50) -> health = " + c.getHealth() + " (capped)");
        c.takeDamage(150);
        System.out.println("c.takeDamage(150) -> health = " + c.getHealth() + " (floored)");
    }

    // ==========================================
    // Problem 2: The Playlist
    // Defensive copy returning safe duplicate
    // ==========================================
    public static class Playlist {
        private final String[] songs;
        private int count;

        public Playlist(int maxCapacity) {
            this.songs = new String[maxCapacity];
            this.count = 0;
        }

        public void addSong(String title) {
            if (count < songs.length) {
                songs[count++] = title;
            }
        }

        public String[] getSongs() {
            // Return defensive copy, not internal reference
            return Arrays.copyOf(songs, count);
        }

        public int getSongCount() {
            return count;
        }
    }

    public static void runPlaylistDemo() {
        System.out.println("\n--- Problem 2: The Playlist ---");
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");
        String[] copy = p.getSongs();
        copy[0] = "Hacked"; // Attempted external mutation
        System.out.println("Mutated copy[0] to: " + copy[0]);
        System.out.println("Actual playlist song[0]: " + p.getSongs()[0] + " (Protected!)");
        System.out.println("Song count: " + p.getSongCount());
    }

    // ==========================================
    // Problem 3: The Password Checker
    // Private password, strength evaluation
    // ==========================================
    public static class PasswordChecker {
        private final String password;

        public PasswordChecker(String password) {
            this.password = password != null ? password : "";
        }

        public String getStrength() {
            int len = password.length();
            if (len < 6) {
                return "Weak";
            } else if (len <= 9) {
                return "Medium";
            } else {
                return "Strong";
            }
        }
    }

    public static void runPasswordCheckerDemo() {
        System.out.println("\n--- Problem 3: The Password Checker ---");
        PasswordChecker pc1 = new PasswordChecker("abcd");
        System.out.println("Password \"abcd\" -> " + pc1.getStrength());

        PasswordChecker pc2 = new PasswordChecker("pass1234");
        System.out.println("Password \"pass1234\" -> " + pc2.getStrength());

        PasswordChecker pc3 = new PasswordChecker("abcdefghij");
        System.out.println("Password \"abcdefghij\" -> " + pc3.getStrength());
    }

    // ==========================================
    // Problem 4: The Traffic Light
    // Cycle: RED -> GREEN -> YELLOW -> RED
    // ==========================================
    public static class TrafficLight {
        private final String id;
        private String color;

        public TrafficLight(String id) {
            this.id = id;
            this.color = "RED"; // Starts on RED
        }

        public String getId() { return id; }

        public String getColor() { return color; }

        public void next() {
            switch (color) {
                case "RED" -> color = "GREEN";
                case "GREEN" -> color = "YELLOW";
                case "YELLOW" -> color = "RED";
            }
        }
    }

    public static void runTrafficLightDemo() {
        System.out.println("\n--- Problem 4: The Traffic Light ---");
        TrafficLight t = new TrafficLight("TL-9");
        System.out.println("t.getColor() -> " + t.getColor());
        t.next();
        System.out.println("t.next() -> " + t.getColor());
        t.next();
        System.out.println("t.next() -> " + t.getColor());
        t.next();
        System.out.println("t.next() -> " + t.getColor());
    }

    // ==========================================
    // Problem 5: The Shopping Cart
    // Private prices array, on-demand total calculation
    // ==========================================
    public static class Cart {
        private final String cartId;
        private final double[] itemPrices;
        private int itemCount;

        public Cart(String cartId, int maxItems) {
            this.cartId = cartId;
            this.itemPrices = new double[maxItems];
            this.itemCount = 0;
        }

        public String getCartId() { return cartId; }

        public void addItem(double price) {
            if (itemCount < itemPrices.length && price >= 0) {
                itemPrices[itemCount++] = price;
            }
        }

        public double getTotal() {
            double total = 0;
            for (int i = 0; i < itemCount; i++) {
                total += itemPrices[i];
            }
            return total;
        }

        public int getItemCount() {
            return itemCount;
        }
    }

    public static void runShoppingCartDemo() {
        System.out.println("\n--- Problem 5: The Shopping Cart ---");
        Cart cart = new Cart("CART-5", 20);
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);
        System.out.println("cart.getTotal() -> " + (int) cart.getTotal());
        System.out.println("cart.getItemCount() -> " + cart.getItemCount());
    }

    // ==========================================
    // Main Method with Interactive Menu
    // ==========================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   WEEK 7 ASSIGNMENT - ENCAPSULATION");
            System.out.println("========================================");
            System.out.println("1. The Health Bar");
            System.out.println("2. The Playlist (Defensive Copy)");
            System.out.println("3. The Password Checker");
            System.out.println("4. The Traffic Light Cycle");
            System.out.println("5. The Shopping Cart");
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
                case 1 -> runHealthBarDemo();
                case 2 -> runPlaylistDemo();
                case 3 -> runPasswordCheckerDemo();
                case 4 -> runTrafficLightDemo();
                case 5 -> runShoppingCartDemo();
                case 6 -> {
                    runHealthBarDemo();
                    runPlaylistDemo();
                    runPasswordCheckerDemo();
                    runTrafficLightDemo();
                    runShoppingCartDemo();
                }
                case 0 -> {
                    System.out.println("Exiting Week 7 Assignment. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please enter 0-6.");
            }
        }
    }
}
