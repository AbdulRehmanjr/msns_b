package com.msns.management.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.msns.management.model.User;
import com.msns.management.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> resgisterUser(@RequestParam("file") MultipartFile profilePicture, String user) {

        User n_user = new User();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            n_user = objectMapper.readValue(user, User.class);
        } catch (JsonProcessingException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        User found = this.userService.getUserByEmail(n_user.getUserName());

        if (found != null) {
            log.error("User already exists with given Email: {}", n_user.getUserName());
            return ResponseEntity.badRequest().body("User already exists with given email");
        }

        try {
            n_user.setUserPicture(profilePicture.getBytes());
        } catch (IOException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        n_user = this.userService.saveUser(n_user);
        if (n_user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(n_user);
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Error in creation user");

    }

    @PostMapping("/register-admin")
    public ResponseEntity<String> resgisterAdmin(@RequestParam("file") MultipartFile profilePicture, String user) {

        
        User n_user = new User();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            n_user = objectMapper.readValue(user, User.class);
        } catch (JsonProcessingException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        try {
            n_user.setUserPicture(profilePicture.getBytes());
        } catch (IOException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        n_user = this.userService.saveAdmin(n_user);
        if (n_user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created.");
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Error in creation user");

    }

    @GetMapping("/all/{userName}")
    public List<User> getUserByName(@PathVariable("userName") String username) {

        List<User> result = this.userService.getAllUsersByUserNameLike(username);

        if (result == null) {
            log.error("Users not found");
            return null;
        }

        log.info("User Found.");

        return result;

    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") int userId) {

        
        User result = this.userService.getUserById(userId);

        if (result == null) {
            log.error("Users not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        log.info("User Found.");

        

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @GetMapping("/edit/{userId}")
    public ResponseEntity<?> getUserByIdEdit(@PathVariable("userId") int userId) {

        
        User result = this.userService.getUserByIdEdit(userId);

        if (result == null) {
            log.error("Users not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        log.info("User Found.");

        

        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    // @Deprecated
    // @PostMapping("/forgot-password/{email}")
    // ResponseEntity<?> restPassword(@PathVariable String email,@RequestBody Otp otp) {
    //     log.info("Resting the password");

    //     User user = this.userService.getUserByEmail(email);

    //     if(user == null){
    //         return ResponseEntity.status(401).body(null);
    //     }
    //     this.userService.restPassword(otp.getOtp(),email);
    //     return ResponseEntity.status(201).body(user);
    // }

    @PostMapping("/change-password/{email}")
    ResponseEntity<?> change(@PathVariable String email,@RequestBody User password) {
        log.info("Resting the password");

        User user = this.userService.getUserByEmail(email);

        if(user == null){
            return ResponseEntity.status(401).body(null);
        }
        user.setUserPassword(password.getUserPassword());
       user =  this.userService.updatePassword(user);

        return ResponseEntity.status(201).body(user);
    }
    @PostMapping("/edit/file")
    public ResponseEntity<?> updateUser(@RequestParam("file") MultipartFile profilePicture, String user) {

    
        User nUser = new User();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            nUser = objectMapper.readValue(user, User.class);
        } catch (JsonProcessingException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        try {
            nUser.setUserPicture(profilePicture.getBytes());
        } catch (IOException e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

        nUser = this.userService.updateUser(nUser);
        if (nUser != null) {
            return ResponseEntity.status(201).body(nUser);
        }
        return ResponseEntity.status(401).body(null);

    }
    @PostMapping("/edit")
    public ResponseEntity<?> updateUserNFile(@RequestBody User user) {

        log.info("Request to update the user without file");
       User response =  this.userService.updateUser(user);
        if (response != null) {
            return ResponseEntity.status(201).body(response);
        }
        return ResponseEntity.status(401).body(null);

    }
    @GetMapping("/all")
    ResponseEntity<?> Allusers() {
        log.info("Geting all users");

        List<User> users = this.userService.getAllUsers();

        if(users==null){
            return ResponseEntity.status(401).body(null);
        }
        
        return ResponseEntity.status(201).body(users);
    }

    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

}
