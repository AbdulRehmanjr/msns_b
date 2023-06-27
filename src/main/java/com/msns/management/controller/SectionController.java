package com.msns.management.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msns.management.model.Section;
import com.msns.management.service.SectionService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping("/create")
    ResponseEntity<?> saveSection(@RequestBody Section section){

        Section response = this.sectionService.createSection(section);

        if(response == null){
            
            log.error("Error creating section");
            return ResponseEntity.status(404).body("Section Creation Error.");
        }

        return ResponseEntity.status(201).body(response);

    }

    
    @GetMapping("/all")
    ResponseEntity<?> getAllSections(){

        List<Section> sections = this.sectionService.getAllSections();

        if(sections == null || sections.size()==0  ){
            
            log.error("No section found");
            return  ResponseEntity.status(404).body("No Section Found.");
        }

        return ResponseEntity.status(200).body(sections);
    }
}
