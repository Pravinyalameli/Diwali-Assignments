package com.demo.service;

import com.demo.dao.StudentDao;
import com.demo.dao.StudentDaoImpl;
import com.demo.model.LowAttendanceException;
import com.demo.model.Student;
import java.util.*;

public class StudentServiceImpl implements StudentService {
    private List<Student> students;
    private StudentDao studentDao;
    
    public StudentServiceImpl() {
        this.students = new ArrayList<>();
        this.studentDao = new StudentDaoImpl();
    }
    
    @Override
    public void addStudent(Student student) {
        students.add(student);
    }
    
    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    @Override
    public Set<Student> getStudentsSortedByAttendance() {
        // Using TreeSet with custom comparator for descending order of attendance
        Set<Student> sortedStudents = new TreeSet<>((s1, s2) -> 
            Double.compare(s2.getAttendance_percentage(), s1.getAttendance_percentage())
        );
        sortedStudents.addAll(students);
        return sortedStudents;
    }
    
    @Override
    public void calculateAndDisplayGrades() {
        for (Student student : students) {
            try {
                String grade = student.calculateGrade();
                System.out.println("Student: " + student.getSname() + 
                                 " | Roll No: " + student.getRollno() + 
                                 " | Grade: " + grade);
            } catch (LowAttendanceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    // Additional method to save students to file
    public void saveStudentsToFile(String filename) {
        studentDao.saveStudentsToFile(students, filename);
    }
    
    // Additional method to load students from file
    public void loadStudentsFromFile(String filename) {
        this.students = studentDao.readStudentsFromFile(filename);
    }
    
    // Add this method to StudentServiceImpl class
    public void performFileOperations(String filename) {
        saveStudentsToFile(filename);
        // You can add other file operations here if needed
    }
}