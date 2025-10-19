package com.demo.service;

import com.demo.model.Student;
import java.util.List;
import java.util.Set;

public interface StudentService {
    void addStudent(Student student);
    List<Student> getAllStudents();
    Set<Student> getStudentsSortedByAttendance();
    void calculateAndDisplayGrades();
    void saveStudentsToFile(String filename);  // Add this method
    void loadStudentsFromFile(String filename); // Add this method
}