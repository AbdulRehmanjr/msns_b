package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Teacher;

public interface TeacherService {
    
    /**
     * The function "createTeacher" creates a new teacher object and returns it.
     * 
     * @param teacher The teacher object that you want to create.
     * @return The function `createTeacher` returns an object of type `Teacher`.
     */
    Teacher createTeacher(Teacher teacher);

    /**
     * The function getAllTeachers returns a list of Teacher objects.
     * 
     * @return A list of Teacher objects.
     */
    List<Teacher> getAllTeachers();
}
