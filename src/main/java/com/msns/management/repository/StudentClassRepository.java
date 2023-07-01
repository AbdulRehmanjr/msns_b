package com.msns.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.StudentClass;

public interface StudentClassRepository extends JpaRepository<StudentClass, Integer> {

    /**
     * The function returns a list of all students belonging to a specific class
     * identified by its
     * classId.
     * 
     * @param classId The classId parameter is an integer that represents the unique
     *                identifier of a
     *                class.
     * @return The method is returning a list of objects of type StudentClass.
     */
    List<StudentClass> findAllStudentsByClassInfoClassId(int classId);

    /**
     * The function finds the top student in a class based on the class ID and
     * orders them by their
     * student class ID in descending order.
     * 
     * @param classId The classId parameter is an integer that represents the ID of
     *                the class.
     * @return The method `findTopByClassInfoClassIdOrderByStudentClassIdDesc`
     *         returns an object of
     *         type `StudentClass`.
     */
    StudentClass findTopByClassInfoClassIdOrderByStudentClassIdDesc(int classId);
}
