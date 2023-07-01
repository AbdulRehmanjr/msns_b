package com.msns.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msns.management.model.Student;
import com.msns.management.model.StudentClass;
import com.msns.management.service.StudentClassService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/student-class")
public class StudentClassController {

    @Autowired
    private StudentClassService scService;

    /**
     * This function creates a new student class by adding an array of students to a
     * specified class.
     * 
     * @param students An array of Student objects that contains the details of the
     *                 students to be
     *                 added to the class.
     * @param classId  The classId is a path variable that represents the ID of the
     *                 class to which the
     *                 students will be added.
     * @return The method is returning a ResponseEntity object.
     */
    @PostMapping("/{classId}/add")
    ResponseEntity<?> createStudentClass(@RequestBody Student[] students, @PathVariable int classId) {

        String response = this.scService.addStudentClass(students, classId);
        log.info("Response {}", response);
        if (response.equals("Students added successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }

    /**
     * This function retrieves all students in a class and returns a response entity
     * with the list of
     * students or an error message.
     * 
     * @param classId The classId parameter is an integer that represents the ID of
     *                the class for which
     *                you want to retrieve all the students.
     * @return The method is returning a ResponseEntity object.
     */
    @GetMapping("/all/{classId}")
    ResponseEntity<?> getAllStudents(@PathVariable int classId) {

        List<StudentClass> response = this.scService.getAllStudentByClass(classId);

        if (response != null) {

            return ResponseEntity.status(201).body(response);
        }

        return ResponseEntity.status(404).body("Failed to get students of class");

    }
}
