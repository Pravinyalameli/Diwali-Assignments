package com.demo.test;

import com.demo.model.Student;
import com.demo.service.StudentServiceImpl;

public class StudentTest {
    public static void main(String[] args) {
        StudentServiceImpl studentService = new StudentServiceImpl();
        
        // Create 10 student objects
        Student[] students = {
            new Student(101, "John Doe", "Computer Science", 85.5, 78.0),
            new Student(102, "Jane Smith", "Mathematics", 45.0, 82.0),
            new Student(103, "Bob Johnson", "Physics", 92.0, 88.5),
            new Student(104, "Alice Brown", "Chemistry", 58.0, 76.0),
            new Student(105, "Charlie Wilson", "Biology", 75.0, 91.0),
            new Student(106, "Diana Lee", "Computer Science", 88.0, 84.5),
            new Student(107, "Eve Davis", "Mathematics", 62.0, 79.0),
            new Student(108, "Frank Miller", "Physics", 95.0, 87.0),
            new Student(109, "Grace Taylor", "Chemistry", 71.0, 73.5),
            new Student(110, "Henry Clark", "Biology", 82.0, 89.0)
        };
        
        // Add students to service
        for (Student student : students) {
            studentService.addStudent(student);
        }
        
        System.out.println("=== Calculating Grades ===");
        studentService.calculateAndDisplayGrades();
        
        System.out.println("\n=== Students Sorted by Attendance (Descending) ===");
        var sortedStudents = studentService.getStudentsSortedByAttendance();
        for (Student student : sortedStudents) {
            System.out.printf("Name: %-15s | Attendance: %.1f%% | Score: %.1f\n", 
                            student.getSname(), 
                            student.getAttendance_percentage(), 
                            student.getScore());
        }
        
        System.out.println("\n=== Performing File Operations ===");
        studentService.performFileOperations("students.dat");
        
        // Optional: Test loading from file
        System.out.println("\n=== Loading Students from File ===");
        StudentServiceImpl newStudentService = new StudentServiceImpl();
        newStudentService.loadStudentsFromFile("students.dat");
        System.out.println("Loaded " + newStudentService.getAllStudents().size() + " students from file.");
    }
}