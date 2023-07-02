package com.msns.management.service;

import java.util.List;

import com.msns.management.model.User;

public interface UserService {
    
     User saveUser(User user);

     User saveAdmin(User user);

     User getUserById(int userId);

     User getUserByIdEdit(int userId);

     User updateUser(User user);

     List<User> getAllUsers();
     
     User getUserByEmail(String email);

     void deleteUser(int id);

     User updatePassword(User user);

}