package com.msns.management.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msns.management.model.Student;
import com.msns.management.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * This function returns a list of all students and responds with a success
     * status code if the list
     * is not empty, otherwise it responds with an error status code.
     * 
     * @return The method is returning a ResponseEntity object. The response entity
     *         can contain a list
     *         of Student objects if the response is successful (status code 201),
     *         or a string message
     *         indicating an error if the response is not successful (status code
     *         404).
     */
    @GetMapping("/all")
    ResponseEntity<?> getAllStudents() {

        List<Student> response = this.studentService.getAllStudents();

        if (response != null)
            return ResponseEntity.status(201).body(response);

        return ResponseEntity.status(404).body("Student Fetching Error.");
    }

    /**
     * This function retrieves a list of students by their name and returns a
     * response entity with the
     * list if successful, or an error message if unsuccessful.
     * 
     * @param studentName The studentName parameter is a path variable that is used
     *                    to search for
     *                    students by their name.
     * @return The method is returning a ResponseEntity object.
     */
    @GetMapping("/search/{studentName}")
    ResponseEntity<?> getAllStudentsByStudentName(@PathVariable String studentName) {

        List<Student> response = this.studentService.getStudentByName(studentName);

        if (response != null)
            return ResponseEntity.status(201).body(response);

        return ResponseEntity.status(404).body("Student Fetching Error.");
    }

    /**
     * The function creates a new student by parsing the student object from JSON,
     * setting the profile
     * picture, and then calling the createStudent method from the studentService.
     * 
     * @param profilePicture The `profilePicture` parameter is of type
     *                       `MultipartFile` and represents
     *                       the uploaded profile picture file for the student. It
     *                       is used to retrieve the bytes of the
     *                       uploaded file using the `getBytes()` method.
     * @param student        The "student" parameter is a JSON string representing
     *                       the student object. It will
     *                       be deserialized into a Student object using the
     *                       ObjectMapper class.
     * @return The method is returning a ResponseEntity object.
     */
    @PostMapping("/create")
    ResponseEntity<?> createStudent(@RequestParam("file") MultipartFile profilePicture, String student) {

        Student newStudent = new Student();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            newStudent = objectMapper.readValue(student, Student.class);
        } catch (JsonProcessingException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        try {
            newStudent.setPicture(profilePicture.getBytes());
        } catch (IOException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        newStudent = this.studentService.createStudent(newStudent);
        if (newStudent != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Error Student Creation");

    }

    /**
     * The function `editStudent` updates a student record and returns a response
     * entity with the
     * updated student if successful, or an error message if unsuccessful.
     * 
     * @param student The "student" parameter is of type Student and represents the
     *                student object that
     *                needs to be updated. It is annotated with @RequestBody, which
     *                means that the student object will
     *                be obtained from the request body of the HTTP POST request.
     * @return The method is returning a ResponseEntity object.
     */
    @PostMapping("/update")
    ResponseEntity<?> editStudent(@RequestBody Student student) {

        Student response = this.studentService.updateStudent(student);

        if (response != null) {
            return ResponseEntity.status(201).body(student);
        }
        return ResponseEntity.status(404).body("Updation Error.");
    }

    /**
     * The function `editStudent` updates a student's profile picture by receiving a
     * file and a student
     * object as parameters, converting the student object from JSON to a Java
     * object, setting the
     * profile picture in the student object, and then calling a service method to
     * update the student's
     * blob data in the database.
     * 
     * @param profilePicture The `profilePicture` parameter is of type
     *                       `MultipartFile` and represents
     *                       the uploaded file for the student's profile picture.
     * @param student        The "student" parameter is a JSON string representing a
     *                       Student object. It is
     *                       used to update the student information.
     * @return The method is returning a ResponseEntity object.
     */
    @PostMapping("/update-blob")
    ResponseEntity<?> editStudent(@RequestParam("file") MultipartFile profilePicture, String student) {

        Student newStudent = new Student();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            newStudent = objectMapper.readValue(student, Student.class);
        } catch (JsonProcessingException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        try {
            newStudent.setPicture(profilePicture.getBytes());
        } catch (IOException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        newStudent = this.studentService.updatStudentBlob(newStudent);
        if (newStudent != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Error Student Updation");
    }

}
