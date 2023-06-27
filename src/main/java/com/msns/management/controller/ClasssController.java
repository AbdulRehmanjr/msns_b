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


    @PostMapping("/create")
    ResponseEntity<?> saveClass(@RequestBody Classs classInfo){


        Classs response  = this.classService.createClass(classInfo);

        if(response == null){
            return ResponseEntity.status(404).body("Class Creation Error");
        }

        return ResponseEntity.status(201).body(response);

    }

    @GetMapping("/all")
    ResponseEntity<?> getAllClasses(){


        List<Classs> response  = this.classService.getAllClasses();

        if(response == null){
            return ResponseEntity.status(404).body("No Class Found.");
        }

        return ResponseEntity.status(201).body(response);

    }

    @PutMapping("/update")
    ResponseEntity<?> updateClass(@RequestBody Classs classInfo){


        Classs response  = this.classService.updateClass(classInfo);

        if(response == null){
            return ResponseEntity.status(404).body("Class Updation Error.");
        }

        return ResponseEntity.status(201).body(response);

    }

}
