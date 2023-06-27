package com.msns.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.User;


public interface UserRepository extends JpaRepository<User,Integer>{

    User findByUserName(String userName);

    User findByUserEmail(String email);
}
