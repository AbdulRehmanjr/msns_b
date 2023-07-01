package com.msns.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * The function findByUserName takes a username as input and returns a User
     * object that matches the
     * given username.
     * 
     * @param userName The username of the user you want to find.
     * @return The method findByUserName returns a User object.
     */
    User findByUserName(String userName);

    /**
     * The function findByUserEmail takes an email as input and returns a User
     * object that matches the
     * email.
     * 
     * @param email The email parameter is a string that represents the email
     *              address of a user.
     * @return The method findByUserEmail returns a User object.
     */
    User findByUserEmail(String email);
}
