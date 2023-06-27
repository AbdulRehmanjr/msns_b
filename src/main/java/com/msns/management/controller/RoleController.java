package com.msns.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msns.management.model.Role;
import com.msns.management.service.RoleService;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    ResponseEntity<?> saveRole(@RequestBody Role role){

        Role response = this.roleService.createRole(role);

        if(response == null){

            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(201).body(response);
        
    }

     @GetMapping("/all")
    ResponseEntity<?> getRoles(){

        List<Role> response = this.roleService.getAllRoles();

        if(response == null){

            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(201).body(response);
        
    }
}
