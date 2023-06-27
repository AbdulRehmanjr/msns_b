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

    @Override
    public List<Classs> getClassByName(String ClassName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClassByName'");
    }

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
