import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student implements Serializable {
    private int id;
    private String name;
    private String stream; // new field for course/department
    private double marks;

    public Student(int id, String name, String stream, double marks) {
        this.id = id;
        this.name = name;
        this.stream = stream;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStream() {
        return stream;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}

public class StudentManagementSystem {
    public static final String FILE_NAME = "students.dat";
    public static ArrayList<Student> students = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        int choice;
        do {
            System.out.println("\n==== Student Record Management System ====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students (All / By Stream)");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Clear All Records");
            System.out.println("6. Save & Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> clearAllData();
                case 6 -> saveData();
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);
    }

    // ✅ Add student with stream
    public static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Stream (e.g., CSE, ECE, IT): ");
        String stream = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        students.add(new Student(id, name, stream, marks));
        System.out.println("✅ Student added successfully!");
    }

    // ✅ View Students (All / By Stream / By ID) - case-insensitive
    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No student records found.");
            return;
        }

        sc.nextLine(); // consume newline
        System.out.print("View all students, by stream, or by ID? (all/stream/id): ");
        String input = sc.nextLine().trim().toLowerCase();

        ArrayList<Student> filteredList = new ArrayList<>();

        switch (input) {
            case "all" -> filteredList.addAll(students); // view all

            case "stream" -> { // view by stream
                System.out.print("Enter stream name (e.g., CSE, ECE, IT): ");
                String stream = sc.nextLine().trim().toLowerCase();
                for (Student s : students) {
                    if (s.getStream().toLowerCase().equals(stream)) {
                        filteredList.add(s);
                    }
                }
            }

            case "id" -> { // view by ID
                System.out.print("Enter student ID: ");
                int id = sc.nextInt();
                for (Student s : students) {
                    if (s.getId() == id) {
                        filteredList.add(s);
                        break; // ID is unique
                    }
                }
            }

            default -> { // treat input as stream name directly
                String stream = input;
                for (Student s : students) {
                    if (s.getStream().toLowerCase().equals(stream)) {
                        filteredList.add(s);
                    }
                }
            }
        }

        if (filteredList.isEmpty()) {
            System.out.println("⚠️ No students found for your query.");
            return;
        }

        // Print table
        System.out.println("\n-------------------------------------------------------------");
        System.out.printf("| %-5s | %-20s | %-10s | %-10s |%n", "ID", "Name", "Stream", "Marks");
        System.out.println("-------------------------------------------------------------");

        for (Student s : filteredList) {
            System.out.printf("| %-5d | %-20s | %-10s | %-10.2f |%n",
                    s.getId(), s.getName(), s.getStream(), s.getMarks());
        }

        System.out.println("-------------------------------------------------------------");
    }

    // ✅ Update student with selective fields
    public static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        boolean found = false;

        for (Student s : students) {
            if (s.getId() == id) {
                found = true;
                System.out.print("What do you want to update? (name/stream/marks/all): ");
                String choice = sc.nextLine().trim().toLowerCase();

                switch (choice) {
                    case "name" -> {
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        s.setName(newName);
                        System.out.println("✅ Name updated successfully!");
                    }
                    case "stream" -> {
                        System.out.print("Enter new stream: ");
                        String newStream = sc.nextLine();
                        s.setStream(newStream);
                        System.out.println("✅ Stream updated successfully!");
                    }
                    case "marks" -> {
                        System.out.print("Enter new marks: ");
                        double newMarks = sc.nextDouble();
                        s.setMarks(newMarks);
                        System.out.println("✅ Marks updated successfully!");
                    }
                    case "all" -> {
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new stream: ");
                        String newStream = sc.nextLine();
                        System.out.print("Enter new marks: ");
                        double newMarks = sc.nextDouble();
                        s.setName(newName);
                        s.setStream(newStream);
                        s.setMarks(newMarks);
                        System.out.println("✅ All details updated successfully!");
                    }
                    default -> System.out.println("⚠️ Invalid option! Nothing updated.");
                }
                break;
            }
        }

        if (!found)
            System.out.println("⚠️ Student ID not found!");
    }

    // ✅ Delete student by ID
    public static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        boolean removed = students.removeIf(s -> s.getId() == id);

        if (removed)
            System.out.println("🗑️ Student deleted successfully!");
        else
            System.out.println("⚠️ Student ID not found!");
    }

    // ✅ Clear all student records
    public static void clearAllData() {
        System.out.print("Are you sure you want to delete ALL records? (yes/no): ");
        sc.nextLine(); // consume newline
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("yes")) {
            students.clear();
            System.out.println("🧹 All student records cleared!");
        } else {
            System.out.println("❎ Clear action cancelled.");
        }
    }

    // ✅ Save to file
    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            System.out.println("💾 Data saved successfully. Exiting...");
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    // ✅ Load data from file
    @SuppressWarnings("unchecked")
    public static void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
            System.out.println("📂 Loaded " + students.size() + " student record(s) from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Could not load data: " + e.getMessage());
        }
    }
}