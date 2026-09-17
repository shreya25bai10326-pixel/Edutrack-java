# 🎓 EduTrack – Student Performance & Course Management System

## 📌 Project Description

EduTrack is a Java-based command-line application designed to manage student academic information.

The system allows users to add student records, view student details, search for students, calculate grades, check attendance, generate performance reports, identify the top-performing student, and generate a class summary.

The project demonstrates important Java programming concepts such as Object-Oriented Programming, inheritance, interfaces, exception handling, collections, file handling, Date/Time API, lambda expressions, and multithreading.

---

## 🎯 Objectives

* To develop a simple student performance management system.
* To store and manage student academic information.
* To calculate student grades automatically.
* To monitor student attendance.
* To generate individual student performance reports.
* To identify the top-performing student.
* To store student data using a CSV file.
* To apply Java programming concepts in a practical application.

---

## ⚙️ Technologies Used

* Java
* Java Collections Framework
* Java I/O
* Java NIO (`Files` and `Path`)
* Java Date and Time API
* Exception Handling
* Multithreading
* CSV File Storage

---

## 📊 Data Used

The project uses a CSV file named `students.csv`.

The file contains:

* Roll Number
* Student Name
* Age
* Course
* Marks
* Attendance Percentage

### Example

| Roll No | Name  | Age | Course                 | Marks | Attendance |
| ------- | ----- | --: | ---------------------- | ----: | ---------: |
| 101     | Aarav |  19 | Computer Science       |    88 |         92 |
| 102     | Riya  |  20 | Information Technology |    76 |         84 |
| 103     | Karan |  19 | Computer Science       |    91 |         96 |

---

## 🔄 Methodology

The project follows these steps:

1. Load existing student data from the CSV file.
2. Display the main menu.
3. Accept the user's choice.
4. Perform the selected student-management operation.
5. Validate user input.
6. Calculate grades and performance information.
7. Generate student or class reports.
8. Save updated information back to the CSV file.
9. Exit the application.

---

## 🧠 Main Features

### 1. Add Student

The user can enter:

* Roll number
* Name
* Age
* Course
* Marks
* Attendance percentage

The system validates the entered values before storing them.

---

### 2. Display All Students

The system displays all stored student records in a tabular format.

It shows:

* Roll number
* Name
* Age
* Course
* Marks
* Attendance
* Grade

---

### 3. Search Student

A student can be searched using their roll number.

If the roll number exists, the student's performance details are displayed.

If the student does not exist, a custom `StudentNotFoundException` is generated.

---

### 4. Grade Calculation

The system automatically calculates the grade according to marks.

|    Marks | Grade |
| -------: | :---- |
|   90–100 | A+    |
|    80–89 | A     |
|    70–79 | B     |
|    60–69 | C     |
|    50–59 | D     |
| Below 50 | F     |

---

### 5. Attendance Monitoring

The system stores the attendance percentage of each student.

If attendance is below 75%, the generated report displays an attendance warning.

---

### 6. Student Performance Report

The system generates a report containing:

* Student information
* Course
* Marks
* Attendance
* Grade
* Attendance status
* Report generation date and time

---

### 7. Top Student

The system compares the marks of all students and displays the student having the highest marks.

---

### 8. Class Summary

The system calculates:

* Total number of students
* Average marks
* Average attendance

The summary is generated using Java's `ExecutorService`.

---

## 💻 Java Concepts Implemented

### Object-Oriented Programming

The project uses classes and objects to represent students and persons.

### Inheritance

`Student` inherits basic information from the `Person` class.

### Interface

The `Reportable` interface is used for report generation.

### Method Overriding

The `generateReport()` method is implemented in the `Student` class.

### Encapsulation

Student data is stored using private fields with public getter methods.

### Collections

`ArrayList<Student>` is used to store student records dynamically.

### Exception Handling

`try-catch` blocks are used to handle invalid input and file-related errors.

### Custom Exceptions

The project contains:

* `InvalidMarksException`
* `StudentNotFoundException`

### File Handling

Student information is stored and retrieved using a CSV file.

### Java NIO

The project uses:

* `Files`
* `Path`
* `Paths`

for file operations.

### Date and Time API

`LocalDateTime` is used to display the report generation date and time.

### Lambda Expression

A lambda expression is used while generating the class summary task.

### Multithreading

`ExecutorService` is used to execute the class-summary operation separately.

---

## 📁 Project Structure

```text
EduTrack/
│
├── Main.java
├── students.csv
└── README.md
```

---

## ▶️ How to Run

### Step 1: Install Java

Install JDK 17 or later.

Check the Java version using:

```bash
java -version
```

Also check the compiler:

```bash
javac -version
```

---

### Step 2: Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/EduTrack.git
```

Move into the project directory:

```bash
cd EduTrack
```

---

### Step 3: Compile the Program

```bash
javac Main.java
```

---

### Step 4: Run the Program

```bash
java Main
```

---

## 🖥️ Main Menu

```text
======================================
       EDUTRACK STUDENT SYSTEM
======================================

========== MAIN MENU ==========
1. Add Student
2. Display All Students
3. Search Student
4. Generate Student Report
5. Show Top Student
6. Save Data
7. Generate Class Summary
8. Exit
===============================
```

---

## 📈 Sample Output

### Student Report

```text
---------- STUDENT REPORT ----------
Name : Karan
Age  : 19
Roll No    : 103
Course     : Computer Science
Marks      : 91.0
Attendance : 96.0%
Grade      : A+
Status     : Attendance requirement satisfied
------------------------------------
```

### Class Summary

```text
========== CLASS SUMMARY ==========
Total Students       : 5
Average Marks        : 81.00
Average Attendance   : 86.80%
===================================
```

---

## 🧪 Testing

The following cases can be tested:

| Test Case                   | Expected Result                  |
| --------------------------- | -------------------------------- |
| Add valid student           | Student is added successfully    |
| Enter marks above 100       | Invalid marks message            |
| Enter negative marks        | Invalid marks message            |
| Search existing roll number | Student report displayed         |
| Search invalid roll number  | Student not found message        |
| Display all students        | All records displayed            |
| Generate report             | Performance report displayed     |
| Generate class summary      | Average performance displayed    |
| Save data                   | Data stored in CSV file          |
| Restart application         | Previous records loaded from CSV |

---

## ⚠️ Challenges Faced

* Managing student records dynamically.
* Validating user input.
* Handling invalid marks and attendance values.
* Reading and writing CSV files.
* Implementing custom exceptions.
* Combining multiple Java concepts into one application.
* Generating class-level performance statistics.

---

## 🧠 Learning Outcomes

Through this project, I learned how to:

* Apply Object-Oriented Programming concepts.
* Use inheritance and interfaces.
* Work with Java collections.
* Handle exceptions effectively.
* Create custom exceptions.
* Read and write files using Java.
* Use Java NIO classes such as `Files` and `Path`.
* Work with the Java Date/Time API.
* Use lambda expressions.
* Implement basic multithreading.
* Build a complete command-line Java application.

---

## 🚀 Future Scope

The project can be enhanced by:

* Adding course-wise performance analysis.
* Adding a graphical user interface.
* Adding login and authentication.
* Adding database support using MySQL.
* Generating PDF performance reports.
* Adding more detailed attendance analysis.
* Adding semester-wise student performance.
* Adding data visualization.

---

## 📌 Conclusion

EduTrack provides a simple command-line solution for managing student academic information and analyzing performance.

The project combines multiple Java programming concepts into a practical application and demonstrates how Java can be used to build a complete data-management system.

---


Programming in Java Project

