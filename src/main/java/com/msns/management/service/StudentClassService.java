package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Student;
import com.msns.management.model.StudentClass;

public interface StudentClassService {

    StudentClass createStudentClass(StudentClass studentClass);

    String addStudentClass(Student[] students,int classId);

    List<StudentClass> getAllStudentByClass(int classId);
    
}
