package com.demo.model;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int rollno;
    private String sname;
    private String course;
    private double attendance_percentage;
    private double score;
    
    // Default constructor
    public Student() {
    }
    
    // Parameterized constructor
    public Student(int rollno, String sname, String course, double attendance_percentage, double score) {
        this.rollno = rollno;
        this.sname = sname;
        this.course = course;
        this.attendance_percentage = attendance_percentage;
        this.score = score;
    }
    
    // Calculate grade method
    public String calculateGrade() throws LowAttendanceException {
        // Check attendance
        if (attendance_percentage < 60) {
            throw new LowAttendanceException("Student " + sname + " (Roll No: " + rollno + 
                                           ") has low attendance: " + attendance_percentage + 
                                           "%. Minimum required: 60%");
        }
        
        // Calculate grade based on score
        if (score >= 90) return "A+";
        else if (score >= 80) return "A";
        else if (score >= 70) return "B";
        else if (score >= 60) return "C";
        else if (score >= 50) return "D";
        else return "F";
    }
    
    // Getters and Setters
    public int getRollno() { return rollno; }
    public void setRollno(int rollno) { this.rollno = rollno; }
    
    public String getSname() { return sname; }
    public void setSname(String sname) { this.sname = sname; }
    
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    
    public double getAttendance_percentage() { return attendance_percentage; }
    public void setAttendance_percentage(double attendance_percentage) { 
        this.attendance_percentage = attendance_percentage; 
    }
    
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    
    @Override
    public String toString() {
        return "Student{" +
                "rollno=" + rollno +
                ", sname='" + sname + '\'' +
                ", course='" + course + '\'' +
                ", attendance_percentage=" + attendance_percentage +
                ", score=" + score +
                '}';
    }
}