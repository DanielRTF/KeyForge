import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class KeyForge {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<KeyForgeEntry> entries = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to KeyForge!");
            System.out.println("1. Add a new password");
            System.out.println("2. View all passwords");
            System.out.println("3. Edit a password");
            System.out.println("4. Generate a password");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    addPassword();
                    break;
                case 2:
                    viewPasswords();
                    break;
                case 3:
                    editPassword();
                    break;
                case 4:
                    generatePassword();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void addPassword() {
        System.out.print("Enter site name: ");
        String site = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        Date startDate = new Date();
        KeyForgeEntry entry = new KeyForgeEntry(site, username, password, startDate);
        entries.add(entry);
        System.out.println("Password added successfully.");
    }
    
    private static void viewPasswords() {
        System.out.println("Stored passwords:");
        for (KeyForgeEntry entry : entries) {
            System.out.println(entry);
        }
    }
    
    private static void editPassword() {
        System.out.print("Enter site name to edit: ");
        String site = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        for (KeyForgeEntry entry : entries) {
            if (entry.getSite().equalsIgnoreCase(site)) {
                entry.setPassword(newPassword);
                System.out.println("Password updated successfully.");
                return;
            }
        }
        System.out.println("Site not found.");
    }
    
    private static void generatePassword() {
        System.out.print("Enter the desired length for the password: ");
        int length = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        String password = generateRandomPassword(length);
        System.out.println("Generated password: " + password);
    }
    
    private static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    // Nested class for password entries
    private static class KeyForgeEntry {
        private final String site;
        private final String username;
        private String password;
        private final Date startDate;
        
        public KeyForgeEntry(String site, String username, String password, Date startDate) {
            this.site = site;
            this.username = username;
            this.password = password;
            this.startDate = startDate;
        }
        
        public String getSite() {
            return site;
        }
        
        public void setPassword(String password) {
            this.password = password;
        }
        
        @Override
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return String.format("Site: %s, Username: %s, Password: %s, Start Date: %s",
                                 site, username, password, dateFormat.format(startDate));
        }
    }
}
