package com.msns.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    /**
     * The function findByStudentName searches for students with a given name and
     * returns a list of
     * matching students.
     * 
     * @param studentName A String representing the name of the student to search
     *                    for.
     * @return The method findByStudentName returns a list of Student objects.
     */
    List<Student> findByStudentName(String studentName);

    /**
     * This function returns a list of students whose names contain a given string,
     * ignoring case.
     * 
     * @param studentName A string representing the name of the student. The method
     *                    will return a list
     *                    of students whose names contain the specified studentName,
     *                    ignoring case sensitivity.
     * @return The method is returning a list of Student objects.
     */
    List<Student> findAllByStudentNameContainingIgnoreCase(String studentName);
}
