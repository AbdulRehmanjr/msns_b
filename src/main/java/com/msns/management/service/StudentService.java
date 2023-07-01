package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Student;

public interface StudentService {

    /**
     * The function creates a new student object and returns it.
     * 
     * @param student The student object that you want to create.
     * @return The method createStudent is returning an object of type Student.
     */
    Student createStudent(Student student);

    /**
     * The function returns a list of students with a given name.
     * 
     * @param studentName A String representing the name of the student.
     * @return The method is returning a list of Student objects.
     */
    List<Student> getStudentByName(String studentName);

    /**
     * The function "getStudentsByClass" returns a list of students based on the
     * given class name.
     * 
     * @param className A string representing the name of a class.
     * @return The method is returning a List of Student objects.
     */
    @Deprecated
    List<Student> getStudentsByClass(String className);

    /**
     * The function "getStudentByClassAndSection" returns a list of students based
     * on the given class
     * name and section name.
     * 
     * @param className   The name of the class for which you want to retrieve
     *                    students.
     * @param sectionName The section name refers to the specific division or
     *                    subgroup within a class.
     *                    It is used to further categorize students within a
     *                    particular class.
     * @return The method is returning a List of Student objects.
     */
    @Deprecated
    List<Student> getStudentByClassAndSection(String className, String sectionName);

    /**
     * The function getAllStudents returns a list of all students.
     * 
     * @return The method getAllStudents() returns a list of Student objects.
     */
    List<Student> getAllStudents();

    /**
     * The function "promoteStudents" takes a class name as input and returns a list
     * of students who
     * are eligible for promotion.
     * 
     * @param className A string representing the name of the class for which we
     *                  want to promote the
     *                  students.
     * @return The method is returning a list of Student objects.
     */
    List<Student> promoteStudents(String className);

    /**
     * The function "updateStudent" updates the information of a student and returns
     * the updated
     * student object.
     * 
     * @param student The student object that you want to update.
     * @return The method is returning a Student object.
     */
    Student updateStudent(Student student);

    /**
     * The function updates a student object and returns the updated object.
     * 
     * @param student The student object that needs to be updated.
     * @return The method is returning an updated instance of the Student class.
     */
    Student updatStudentBlob(Student student);

}
