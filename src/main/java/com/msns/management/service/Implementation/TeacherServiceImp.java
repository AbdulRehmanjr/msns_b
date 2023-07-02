package com.msns.management.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msns.management.model.Teacher;
import com.msns.management.repository.TeacherRepository;
import com.msns.management.service.TeacherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeacherServiceImp  implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepo;
    
   /**
    * The function creates a new teacher record in the database and returns the saved teacher object.
    * 
    * @param teacher The parameter "teacher" is an object of type Teacher, which represents a teacher
    * entity.
    * @return The method is returning a Teacher object.
    */
    @Override
    public Teacher createTeacher(Teacher teacher) {
        Teacher response = this.teacherRepo.save(teacher);
        if(response!=null){
            log.info("Teacher saved successfully.");
            return response;
        }
        return null;
    }

    /**
     * The function returns a list of all teachers from the teacher repository.
     * 
     * @return A List of Teacher objects is being returned.
     */
    @Override
    public List<Teacher> getAllTeachers() {
        return this.teacherRepo.findAll();
    }
    


}
