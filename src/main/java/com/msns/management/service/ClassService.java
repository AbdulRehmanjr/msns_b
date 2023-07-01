package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Classs;

public interface ClassService {

    /**
     * The function "createClass" creates a new instance of the "Classs" class using
     * the provided
     * "classInfo" object.
     * 
     * @param classInfo The classInfo parameter is an object of type Classs that
     *                  contains information
     *                  about the class to be created.
     * @return The method is returning an object of type "Classs".
     */
    Classs createClass(Classs classInfo);

    /**
     * The function getClassByName returns a list of Classs objects that match the
     * given ClassName.
     * 
     * @param ClassName The name of the class you want to search for.
     * @return The method is returning a list of objects of type "Classs" that match
     *         the given class
     *         name.
     */
    List<Classs> getClassByName(String ClassName);

    /**
     * The function getAllClasses returns a list of Classs objects.
     * 
     * @return The method getAllClasses() returns a list of objects of type Classs.
     */
    List<Classs> getAllClasses();

    /**
     * The function updateClass takes a Classs object as input and returns an
     * updated version of the
     * object.
     * 
     * @param classInfo The classInfo parameter is an object of type Classs, which
     *                  represents
     *                  information about a class.
     * @return The method is returning an updated instance of the Classs class.
     */
    Classs updateClass(Classs classInfo);

}
