package com.msns.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msns.management.configuration.jwt.JwtUtil;
import com.msns.management.model.JwtRequest;
import com.msns.management.model.JwtResponse;
import com.msns.management.model.User;
import com.msns.management.service.UserService;
import com.msns.management.utils.UserDetailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/token")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


   
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest request) {
        
        
        try {
            authentication(request.getEmail(), request.getPassword());
        } catch (Exception e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error user not found.");
        }

        UserDetails user = this.userDetailsService.loadUserByUsername(request.getEmail());

        String token = this.jwtUtil.generateToken(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new JwtResponse(token));

    }

    private void authentication(String username, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.info("Authenticated User: {} ", username);
        } catch (DisabledException e) {
            log.error("USER DISABLED  {} ", e.getMessage());
            throw new Exception("USER DISABLED");
        } catch (BadCredentialsException e) {
            log.error("INVALID CREDENTIALS {} ", e.getMessage());
            throw new Exception("INVALID CREDENTIALS");
        }

    }

    @PostMapping("/current-user")
    public User getCurrentUser(@RequestBody JwtRequest request) {
        
        User user = this.userService.getUserByEmail(request.getEmail());
        
        return user;
    }
}
