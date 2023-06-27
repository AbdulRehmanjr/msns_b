package com.msns.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{
    
    Student findByRollNumber(String roll);

    List<Student> findByStudentName(String studentName);

    List<Student> findAllByClassNameClassName(String className);

    List<Student> findAllByClassNameClassNameAndSectionNameSectionName(String className,String section);

}
