package com.msns.management.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msns.management.model.Student;
import com.msns.management.repository.StudentRepository;
import com.msns.management.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    /**
     * The function creates a new student record in the database and returns the
     * created student
     * object, or logs an error if the creation fails.
     * 
     * @param student The "student" parameter is an object of the Student class,
     *                which contains
     *                information about a student such as their name, father's name,
     *                and other details.
     * @return The method is returning a Student object.
     */
    @Override
    public Student createStudent(Student student) {

        Student response = this.studentRepo.save(student);
        if (response != null) {
            return response;
        }
        log.error("Error in Creation of student Name {} FatherName {}: ", student.getStudentName(),
                student.getFatherName());
        return null;
    }

    /**
     * The function returns a list of students whose names contain the given
     * studentName, or null if no
     * students are found.
     * 
     * @param studentName The parameter "studentName" is a String that represents
     *                    the name of the
     *                    student.
     * @return The method is returning a List of Student objects.
     */
    @Override
    public List<Student> getStudentByName(String studentName) {

        List<Student> response = this.studentRepo.findAllByStudentNameContainingIgnoreCase(studentName);
        if (response.isEmpty() || response.size() == 0) {
            log.error("No student Found with given name {}", studentName);
            return null;
        }

        return response;
    }

    /**
     * The function getStudentsByClass is not implemented and throws an
     * UnsupportedOperationException.
     * 
     * @param className The className parameter is a String that represents the name
     *                  of a class.
     * @return The method is throwing an UnsupportedOperationException, which means
     *         that it is not
     *         implemented yet. Therefore, nothing is being returned.
     */
    @Override
    public List<Student> getStudentsByClass(String className) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByClass'");
    }

    /**
     * The function getStudentByClassAndSection is not implemented and throws an
     * UnsupportedOperationException.
     * 
     * @param className   The name of the class for which you want to retrieve
     *                    students.
     * @param sectionName The section name is a string that represents the section
     *                    of a class. It is
     *                    used to identify a specific group of students within a
     *                    class.
     * @return The method is returning a List of Student objects.
     */
    @Override
    public List<Student> getStudentByClassAndSection(String className, String sectionName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByClassAndSection'");
    }

    /**
     * The function returns a list of all students from the student repository, or
     * null if the list is
     * empty.
     * 
     * @return The method is returning a List of Student objects.
     */
    @Override
    public List<Student> getAllStudents() {

        List<Student> response = this.studentRepo.findAll();

        if (response.isEmpty() || response.size() == 0)
            return null;
        return response;
    }

    /**
     * The function 'promoteStudents' is not implemented and throws an
     * UnsupportedOperationException.
     * 
     * @param className The className parameter is a String that represents the name
     *                  of the class for
     *                  which we want to promote the students.
     * @return The method is not implemented yet, so it throws an
     *         UnsupportedOperationException.
     */
    @Override
    public List<Student> promoteStudents(String className) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'promoteStudents'");
    }

    /**
     * The function updates a student record in the database and returns the updated
     * student object if
     * successful.
     * 
     * @param student The "student" parameter is an object of the Student class that
     *                represents the
     *                student to be updated.
     * @return The method is returning a Student object.
     */
    @Override
    public Student updateStudent(Student student) {
        Student response = this.studentRepo.save(student);

        if (response != null) {
            log.info("Student updated Successfull with name {}", student.getStudentName());
            return response;
        }

        return null;
    }

    /**
     * The function updates a student's blob data and returns the updated student
     * object.
     * 
     * @param student The "student" parameter is an object of the Student class,
     *                which represents a
     *                student entity. It contains information such as the student's
     *                name, ID, and other attributes.
     * @return The method is returning a Student object.
     */
    @Override
    public Student updatStudentBlob(Student student) {
        Student response = this.studentRepo.save(student);

        if (response != null) {
            log.info("Student Blob updated Successfull with name {}", student.getStudentName());
            return response;
        }

        return null;
    }

}
