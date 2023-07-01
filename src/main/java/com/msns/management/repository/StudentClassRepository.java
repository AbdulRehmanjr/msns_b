package com.msns.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Classs;
import com.msns.management.model.Student;
import com.msns.management.model.StudentClass;

public interface StudentClassRepository extends JpaRepository<StudentClass,Integer>{

    List<StudentClass> findAllStudentsByClassInfoClassId(int classId);

     Student findFirstByOrderByStudentIdDesc();
     StudentClass findTopByClassInfoOrderByStudentStudentIdDesc(Classs classInfo);
}
