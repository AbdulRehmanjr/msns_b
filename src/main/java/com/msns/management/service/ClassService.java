package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Classs;

public interface ClassService {


    Classs createClass(Classs classInfo);
    
    List<Classs> getClassByName(String ClassName);
    
    List<Classs> getAllClasses();
    
    Classs updateClass(Classs classInfo);

}
