package com.demo.dao;

import com.demo.model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    
    @Override
    public void saveStudentsToFile(List<Student> students, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
            System.out.println("Students data saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving students to file: " + e.getMessage());
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Student> readStudentsFromFile(String filename) {
        List<Student> students = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (List<Student>) ois.readObject();
            System.out.println("Students data loaded from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading students from file: " + e.getMessage());
        }
        return students;
    }
}