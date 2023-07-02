package com.msns.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Integer>{
    
}
