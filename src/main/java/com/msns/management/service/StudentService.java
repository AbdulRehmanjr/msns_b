package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Student;

public interface StudentService {
    
    Student createStudent(Student student);
    
    List<Student> getStudentByName(String studentName);
    
    /**
     * @deprecated
     * @param className
     * @return
     */
    List<Student> getStudentsByClass(String className);

    List<Student> getStudentByClassAndSection(String className,String sectionName);

    List<Student> getAllStudents();

    List<Student> promoteStudents(String  className);

    Student updateStudent(Student  student);

    Student updatStudentBlob(Student student);

    

}

