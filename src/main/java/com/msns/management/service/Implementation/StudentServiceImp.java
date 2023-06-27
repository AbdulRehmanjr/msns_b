package com.msns.management.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msns.management.model.Student;
import com.msns.management.repository.StudentRepository;
import com.msns.management.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceImp  implements StudentService{
    

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public Student createStudent(Student student) {
        
        Student response = this.studentRepo.save(student);
        if(response !=null){
            return response;
        }
        log.error("Error in Creation of student Name {} FatherName {}: ",student.getStudentName(),student.getFatherName()); 
        return null;
    }

    @Override
    public List<Student> getStudentByName(String studentName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByName'");
    }

    @Override
    public List<Student> getStudentsByClass(String className) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByClass'");
    }

    @Override
    public List<Student> getStudentByClassAndSection(String className, String sectionName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByClassAndSection'");
    }

    @Override
    public List<Student> getAllStudents() {
        
        List<Student> response = this.studentRepo.findAll();

        if(response.isEmpty() || response.size()==0)
            return null;
        return response;
    }

    @Override
    public Student getStudentUpdate(Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentUpdate'");
    }

    @Override
    public List<Student> promoteStudents(String className) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'promoteStudents'");
    }
    
}
