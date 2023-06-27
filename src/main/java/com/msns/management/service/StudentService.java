package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Student;

public interface StudentService {
    
    Student createStudent(Student student);
    
    List<Student> getStudentByName(String studentName);
    
    List<Student> getStudentsByClass(String className);

    List<Student> getStudentByClassAndSection(String className,String sectionName);

    List<Student> getAllStudents();

    Student getStudentUpdate(Student student);

    List<Student> promoteStudents(String  className);

    

}

