package com.msns.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Classs;

public interface ClassRespository extends JpaRepository<Classs,Integer>{ 
 
    Classs findByClassName(String className);
}
