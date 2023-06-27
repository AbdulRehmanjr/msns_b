package com.msns.management.service;

import java.util.List;

import com.msns.management.model.User;

public interface UserService {
    
     User saveUser(User user);

     User saveAdmin(User user);

     List<User> getAllUsersByUserName(String userName);

     User getUserById(int userId);

     User getUserByIdEdit(int userId);

     User updateUser(User user);
     
     List<User> getAllUsersByUserNameLike(String userNameLike);
    
     List<User> getAllUsers();
     
     User getUserByEmail(String email);

     void deleteUser(int id);

     void resetPassword(int otp,String email);

     User updatePassword(User user);

     User updateUserRole(int userId);
}