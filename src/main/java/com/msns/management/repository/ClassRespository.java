package com.msns.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Classs;

public interface ClassRespository extends JpaRepository<Classs, Integer> {

    /**
     * The function findByClassName takes a className as input and returns an
     * instance of the Classs
     * class that matches the given className.
     * 
     * @param className The className parameter is a String that represents the name
     *                  of the class you
     *                  want to find.
     * @return The method findByClassName returns an object of type Classs.
     */
    Classs findByClassName(String className);
}
