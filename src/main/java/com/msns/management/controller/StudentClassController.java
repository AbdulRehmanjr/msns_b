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

    @PostMapping("/{classId}/add")
    ResponseEntity<?> createStudentClass(@RequestBody Student[] students, @PathVariable int classId) {

        String response = this.scService.addStudentClass(students, classId);
        log.info("Response {}",response);
        if (response.equals("Students added successfully")){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
            
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }

    @GetMapping("/all/{classId}")
    ResponseEntity<?> getAllStudents(@PathVariable int classId) {

        List<StudentClass> response = this.scService.getAllStudentByClass(classId);

        if (response != null) {

            return ResponseEntity.status(201).body(response);
        }

        return ResponseEntity.status(404).body("Failed to get students of class");

    }
}
