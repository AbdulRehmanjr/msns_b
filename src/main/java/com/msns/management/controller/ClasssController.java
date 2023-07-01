package com.msns.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msns.management.model.Classs;
import com.msns.management.service.ClassService;

@RestController
@RequestMapping("/class")
public class ClasssController {

    @Autowired
    private ClassService classService;

    /**
     * This function handles the creation of a new class and returns the created
     * class object or an
     * error message.
     * 
     * @param classInfo The parameter `classInfo` is of type `Classs`, which is the
     *                  class that contains
     *                  the information about the class to be created.
     * @return The method is returning a ResponseEntity object.
     */
    @PostMapping("/create")
    ResponseEntity<?> saveClass(@RequestBody Classs classInfo) {

        Classs response = this.classService.createClass(classInfo);

        if (response == null) {
            return ResponseEntity.status(404).body("Class Creation Error");
        }

        return ResponseEntity.status(201).body(response);

    }

    /**
     * This function returns all classes and returns a response entity with the
     * classes if found, or a
     * 404 status code with a message if no classes are found.
     * 
     * @return The method is returning a ResponseEntity object.
     */
    @GetMapping("/all")
    ResponseEntity<?> getAllClasses() {

        List<Classs> response = this.classService.getAllClasses();

        if (response == null) {
            return ResponseEntity.status(404).body("No Class Found.");
        }

        return ResponseEntity.status(201).body(response);

    }

    /**
     * This function updates a class and returns a response entity with the updated
     * class information.
     * 
     * @param classInfo The parameter `classInfo` is of type `Classs`, which is the
     *                  class that contains
     *                  the information to be updated for a class.
     * @return The method is returning a ResponseEntity object.
     */
    @PutMapping("/update")
    ResponseEntity<?> updateClass(@RequestBody Classs classInfo) {

        Classs response = this.classService.updateClass(classInfo);

        if (response == null) {
            return ResponseEntity.status(404).body("Class Updation Error.");
        }

        return ResponseEntity.status(201).body(response);

    }

}
