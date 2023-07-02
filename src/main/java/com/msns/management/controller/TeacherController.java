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
import com.msns.management.model.Teacher;
import com.msns.management.service.TeacherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    /**
     * The function saves a teacher's profile picture and information in the database.
     * 
     * @param profilePicture The `profilePicture` parameter is of type `MultipartFile` and represents
     * the uploaded profile picture file for the teacher.
     * @param teacher The "teacher" parameter is a JSON string representing a Teacher object. It
     * contains information such as the teacher's name, email, and other details.
     * @return The method is returning a ResponseEntity object.
     */
    @PostMapping("/save")
    ResponseEntity<?> saveTeacher(@RequestParam("file") MultipartFile profilePicture, String teacher) {

        Teacher newTeacher = new Teacher();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            newTeacher = objectMapper.readValue(teacher, Teacher.class);
        } catch (JsonProcessingException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        try {
            newTeacher.setPicture(profilePicture.getBytes());
        } catch (IOException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        newTeacher = this.teacherService.createTeacher(newTeacher);
        if (newTeacher != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(newTeacher);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Teacher Creation Error.");

    }

    /**
     * This function retrieves all teachers and returns them in a ResponseEntity.
     * 
     * @return The method is returning a ResponseEntity object.
     */
    @GetMapping("/all")
    ResponseEntity<?> getAllTeachers(){

        List<Teacher> teachers = this.teacherService.getAllTeachers();

        if(teachers.isEmpty() || teachers.size()==0){
            log.error("No teacher found.");
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Teacher Creation Error.");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(teachers);
    }
}
