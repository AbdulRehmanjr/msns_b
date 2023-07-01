package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Student;
import com.msns.management.model.StudentClass;

public interface StudentClassService {

    /**
     * The function creates a new instance of the StudentClass class and returns it.
     * 
     * @param studentClass The parameter "studentClass" is an object of the class
     *                     StudentClass.
     * @return The method is returning an instance of the StudentClass class.
     */
    StudentClass createStudentClass(StudentClass studentClass);

    /**
     * The function adds a student to a specific class.
     * 
     * @param students An array of Student objects representing the students in the
     *                 school.
     * @param classId  The ID of the class that the student will be added to.
     * @return The method is returning a String value.
     */
    String addStudentClass(Student[] students, int classId);

    /**
     * The function returns a list of all students in a given class.
     * 
     * @param classId The classId parameter is an integer that represents the unique
     *                identifier of a
     *                class.
     * @return The method is returning a list of objects of type StudentClass.
     */
    List<StudentClass> getAllStudentByClass(int classId);

}
