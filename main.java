import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

// Interface for report generation
interface Reportable {
    void generateReport();
}

// Parent class
class Person {
    protected String name;
    protected int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayBasicInfo() {
        System.out.println("Name : " + name);
        System.out.println("Age  : " + age);
    }
}

// Child class demonstrating inheritance
class Student extends Person implements Reportable {

    private int rollNo;
    private String course;
    private double marks;
    private double attendance;

    Student(int rollNo, String name, int age, String course,
            double marks, double attendance) {

        super(name, age);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        this.attendance = attendance;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public double getAttendance() {
        return attendance;
    }

    // Method for grade calculation
    public String calculateGrade() {

        if (marks >= 90)
            return "A+";
        else if (marks >= 80)
            return "A";
        else if (marks >= 70)
            return "B";
        else if (marks >= 60)
            return "C";
        else if (marks >= 50)
            return "D";
        else
            return "F";
    }

    // Method overriding
    @Override
    public void generateReport() {

        System.out.println("\n---------- STUDENT REPORT ----------");

        displayBasicInfo();

        System.out.println("Roll No    : " + rollNo);
        System.out.println("Course     : " + course);
        System.out.println("Marks      : " + marks);
        System.out.println("Attendance : " + attendance + "%");
        System.out.println("Grade      : " + calculateGrade());

        if (attendance < 75) {
            System.out.println("Status     : Attendance below 75%");
        } else {
            System.out.println("Status     : Attendance requirement satisfied");
        }

        System.out.println("------------------------------------");
    }

    // Convert student data to CSV format
    public String toCSV() {

        return rollNo + "," +
               name + "," +
               age + "," +
               course + "," +
               marks + "," +
               attendance;
    }
}

// Custom exception
class InvalidMarksException extends Exception {

    InvalidMarksException(String message) {
        super(message);
    }
}

// Custom exception
class StudentNotFoundException extends Exception {

    StudentNotFoundException(String message) {
        super(message);
    }
}

// Main class
public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Student> students = new ArrayList<>();

    static final String FILE_NAME = "students.csv";

    public static void main(String[] args) {

        loadStudents();

        System.out.println("======================================");
        System.out.println("       EDUTRACK STUDENT SYSTEM");
        System.out.println("======================================");

        while (true) {

            displayMenu();

            int choice = readInteger("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        displayAllStudents();
                        break;

                    case 3:
                        searchStudent();
                        break;

                    case 4:
                        generateStudentReport();
                        break;

                    case 5:
                        showTopStudent();
                        break;

                    case 6:
                        saveStudents();
                        break;

                    case 7:
                        generateClassSummary();
                        break;

                    case 8:
                        System.out.println("\nThank you for using EduTrack!");
                        saveStudents();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Display menu
    static void displayMenu() {

        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Generate Student Report");
        System.out.println("5. Show Top Student");
        System.out.println("6. Save Data");
        System.out.println("7. Generate Class Summary");
        System.out.println("8. Exit");
        System.out.println("===============================");
    }

    // Add student
    static void addStudent() throws InvalidMarksException {

        System.out.println("\n---------- ADD STUDENT ----------");

        int rollNo = readInteger("Enter Roll Number: ");

        if (findStudent(rollNo) != null) {
            System.out.println("Student with this roll number already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        int age = readInteger("Enter Age: ");

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        double marks = readDouble("Enter Marks (0-100): ");

        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException(
                    "Marks must be between 0 and 100."
            );
        }

        double attendance = readDouble(
                "Enter Attendance Percentage (0-100): "
        );

        if (attendance < 0 || attendance > 100) {
            throw new IllegalArgumentException(
                    "Attendance must be between 0 and 100."
            );
        }

        Student student = new Student(
                rollNo,
                name,
                age,
                course,
                marks,
                attendance
        );

        students.add(student);

        saveStudents();

        System.out.println("\nStudent added successfully!");
    }

    // Display all students
    static void displayAllStudents() {

        if (students.isEmpty()) {

            System.out.println("\nNo student records found.");
            return;
        }

        System.out.println("\n================ STUDENT LIST ================");

        System.out.printf(
                "%-8s %-20s %-6s %-15s %-8s %-12s %-6s%n",
                "Roll", "Name", "Age", "Course",
                "Marks", "Attendance", "Grade"
        );

        System.out.println(
                "--------------------------------------------------------------------------"
        );

        for (Student s : students) {

            System.out.printf(
                    "%-8d %-20s %-6d %-15s %-8.2f %-12.2f %-6s%n",
                    s.getRollNo(),
                    s.getName(),
                    getAge(s),
                    s.getCourse(),
                    s.getMarks(),
                    s.getAttendance(),
                    s.calculateGrade()
            );
        }
    }

    // Search student
    static void searchStudent() throws StudentNotFoundException {

        int rollNo = readInteger("Enter Roll Number to search: ");

        Student student = findStudent(rollNo);

        if (student == null) {

            throw new StudentNotFoundException(
                    "No student found with Roll Number " + rollNo
            );
        }

        student.generateReport();
    }

    // Generate report
    static void generateStudentReport()
            throws StudentNotFoundException {

        int rollNo = readInteger("Enter Roll Number: ");

        Student student = findStudent(rollNo);

        if (student == null) {

            throw new StudentNotFoundException(
                    "Student not found."
            );
        }

        student.generateReport();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss"
                );

        System.out.println(
                "Report generated on: " +
                LocalDateTime.now().format(formatter)
        );
    }

    // Find student
    static Student findStudent(int rollNo) {

        for (Student student : students) {

            if (student.getRollNo() == rollNo) {
                return student;
            }
        }

        return null;
    }

    // Find top student
    static void showTopStudent() {

        if (students.isEmpty()) {

            System.out.println("\nNo student records available.");
            return;
        }

        Student topStudent = students.get(0);

        for (Student student : students) {

            if (student.getMarks() > topStudent.getMarks()) {
                topStudent = student;
            }
        }

        System.out.println("\n---------- TOP STUDENT ----------");

        System.out.println("Roll Number : " +
                topStudent.getRollNo());

        System.out.println("Name        : " +
                topStudent.getName());

        System.out.println("Course      : " +
                topStudent.getCourse());

        System.out.println("Marks       : " +
                topStudent.getMarks());

        System.out.println("Grade       : " +
                topStudent.calculateGrade());

        System.out.println("---------------------------------");
    }

    // Save data to CSV file
    static void saveStudents() {

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             Paths.get(FILE_NAME))) {

            writer.write(
                    "RollNo,Name,Age,Course,Marks,Attendance"
            );

            writer.newLine();

            for (Student student : students) {

                writer.write(student.toCSV());
                writer.newLine();
            }

            System.out.println("Data saved successfully.");

        } catch (IOException e) {

            System.out.println(
                    "Unable to save data: " +
                    e.getMessage()
            );
        }
    }

    // Load data from CSV file
    static void loadStudents() {

        Path path = Paths.get(FILE_NAME);

        if (!Files.exists(path)) {
            return;
        }

        try {

            List<String> lines =
                    Files.readAllLines(path);

            for (int i = 1; i < lines.size(); i++) {

                String[] data =
                        lines.get(i).split(",");

                if (data.length == 6) {

                    int rollNo =
                            Integer.parseInt(data[0]);

                    String name = data[1];

                    int age =
                            Integer.parseInt(data[2]);

                    String course = data[3];

                    double marks =
                            Double.parseDouble(data[4]);

                    double attendance =
                            Double.parseDouble(data[5]);

                    students.add(
                            new Student(
                                    rollNo,
                                    name,
                                    age,
                                    course,
                                    marks,
                                    attendance
                            )
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error loading student data."
            );
        }
    }

    // Class summary using multithreading
    static void generateClassSummary() {

        if (students.isEmpty()) {

            System.out.println(
                    "\nNo student records available."
            );

            return;
        }

        System.out.println(
                "\nGenerating class summary..."
        );

        ExecutorService executor =
                Executors.newSingleThreadExecutor();

        Future<String> result =
                executor.submit(() -> {

                    double totalMarks = 0;
                    double totalAttendance = 0;

                    for (Student student : students) {

                        totalMarks += student.getMarks();
                        totalAttendance +=
                                student.getAttendance();
                    }

                    double averageMarks =
                            totalMarks / students.size();

                    double averageAttendance =
                            totalAttendance /
                            students.size();

                    return "\n========== CLASS SUMMARY ==========\n" +
                            "Total Students       : " +
                            students.size() + "\n" +
                            "Average Marks        : " +
                            String.format(
                                    "%.2f",
                                    averageMarks
                            ) + "\n" +
                            "Average Attendance   : " +
                            String.format(
                                    "%.2f",
                                    averageAttendance
                            ) + "%\n" +
                            "===================================";
                });

        try {

            System.out.println(result.get());

        } catch (Exception e) {

            System.out.println(
                    "Unable to generate summary."
            );

        } finally {

            executor.shutdown();
        }
    }

    // Input helper
    static int readInteger(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    // Input helper
    static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid value."
                );
            }
        }
    }

    // Since age is protected in parent class
    static int getAge(Student student) {

        return student.age;
    }
}
