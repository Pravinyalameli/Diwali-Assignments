package com.demo.dao;

import com.demo.model.Student;
import java.util.List;

public interface StudentDao {
    void saveStudentsToFile(List<Student> students, String filename);
    List<Student> readStudentsFromFile(String filename);
}