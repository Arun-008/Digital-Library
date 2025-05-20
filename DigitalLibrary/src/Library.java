import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Library {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Book> books = new ArrayList<>();

    // Load users from CSV file
    public static void loadUsers() {
        users.clear();  // Clear existing users before loading
        File userFile = new File("users.csv");
        if (!userFile.exists()) {
            System.out.println("No user file found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    users.add(new User(data[0].trim(), data[1].trim(), data[2].trim()));
                } else {
                    System.out.println("Skipping malformed user line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save users to CSV file
    public static void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("users.csv"))) {
            for (User user : users) {
                writer.println(user.getUsername() + "," + user.getPassword() + "," + user.getRole());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if a user exists by username (case-insensitive)
    public static boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // Log user activity to a text file with timestamp
    public static void logActivity(String user, String book) {
        try (FileWriter fw = new FileWriter("log.txt", true)) {
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            fw.write(user + " accessed " + book + " at " + time + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load books from the "books" directory
    public static void loadBooks() {
        books.clear();  // Clear existing books before loading
        File folder = new File("books");
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("\"books\" folder not found.");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("No books found in the folder.");
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                books.add(new Book(file.getName(), file.getAbsolutePath()));
            }
        }
    }
}
