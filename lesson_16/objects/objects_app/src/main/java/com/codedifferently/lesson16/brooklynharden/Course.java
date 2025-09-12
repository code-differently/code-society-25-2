package com.codedifferently.lesson16.brooklynharden;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private int courseNumber;
    private String instructor;
    private int courseCredits;
    private ArrayList<String> studentsEnrolled;
    private boolean isActive;
    private CourseLevel courseLevel;

    public Course(String courseName, int courseNumber, String instructor, int courseCredits, ArrayList<String> studentEnrolled, Boolean isActive, CourseLevel courseLevel){
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.instructor = instructor;
        this.courseCredits = courseCredits;
        this.studentsEnrolled = studentEnrolled;
        this.isActive = isActive;
        this.courseLevel = courseLevel;
    }
    //GET METHODS

    public String getCourseName(){
        return this.courseName;
    }

    public int getCourseNumber(){
        return this.courseNumber;
    }

    public String getProfessor(){
        return this.instructor;
    }

    public int getCredits(){
        return this.courseCredits;
    }

    public ArrayList<String> getEnrolledStudents(){
        return this.studentsEnrolled;
    }

    //STUDENTS & ENROLLMENT

    public void enrollStudent(String student) throws CourseException{
        int maxStudents = 30;
        if(studentsEnrolled.size() >= maxStudents){
           throw new CourseException("Course is full"); 
        }
        studentsEnrolled.add(student);
        System.out.println(student + " has been enrolled.");
    }

    public boolean isStudentEnrolled(String student){
        return this.studentsEnrolled.contains(student);
    }

    public void removeStudent(String studentName){
        int index = this.studentsEnrolled.indexOf(studentName);
        if(index != -1){
            this.studentsEnrolled.remove(index);
            System.out.println(studentName + " is found");
        }
        else{
            System.out.println(studentName+" is not found");
        }
    }

    public void listOfStudents(){
        for(int i=0; i<this.studentsEnrolled.size(); i++){
            System.out.println(this.studentsEnrolled.get(i));
        }
    }

    public boolean isCourseFull(){
        return this.studentsEnrolled.size() >= 30;
    }
   
}
