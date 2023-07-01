package com.msns.management.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msns.management.model.Classs;
import com.msns.management.repository.ClassRespository;
import com.msns.management.service.ClassService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClassServiceImp implements ClassService {

    @Autowired
    private ClassRespository classRepo;

    /**
     * The function creates a new class object and saves it to the class repository.
     * 
     * @param classInfo The parameter `classInfo` is an object of type `Classs`
     *                  which contains
     *                  information about a class.
     * @return The method is returning an instance of the Classs class.
     */
    @Override
    public Classs createClass(Classs classInfo) {

        try {
            Classs response = this.classRepo.save(classInfo);
            return response;
        } catch (Exception e) {
            log.error("Error saving class");
            return null;
        }
    }

    /**
     * The function getClassByName is not implemented and throws an
     * UnsupportedOperationException.
     * 
     * @param ClassName The parameter ClassName is a String that represents the name
     *                  of the class you
     *                  want to search for.
     * @return The method is returning a List of objects of type "Classs".
     */
    @Override
    public List<Classs> getClassByName(String ClassName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClassByName'");
    }

    /**
     * The function getAllClasses retrieves all classes from the class repository
     * and returns them as a
     * list, or null if an error occurs.
     * 
     * @return The method is returning a List of Classs objects.
     */
    @Override
    public List<Classs> getAllClasses() {
        List<Classs> classes = this.classRepo.findAll();
        try {
            if (!classes.isEmpty()) {
                return classes;
            }
        } catch (Exception e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
        }
        return null;

    }

    /**
     * The function updates the information of a class and returns the updated class
     * object, or null if
     * an error occurs.
     * 
     * @param classInfo The parameter `classInfo` is an object of type `Classs`
     *                  which contains the
     *                  updated information for a class.
     * @return The method is returning an instance of the Classs class.
     */
    @Override
    public Classs updateClass(Classs classInfo) {
        log.info("Updating the class information.");
        try {
            return this.classRepo.save(classInfo);

        } catch (Exception e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

    }

}
