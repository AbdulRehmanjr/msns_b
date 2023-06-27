package com.msns.management.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/all")
    ResponseEntity<?> getAllStudents(){

        List<Student> response = this.studentService.getAllStudents();

        if(response != null)
            return ResponseEntity.status(201).body(response);
            
        return ResponseEntity.status(404).body("Student Fetching Error.");
    }
}
