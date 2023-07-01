package com.msns.management.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msns.management.model.Classs;
import com.msns.management.model.Section;
import com.msns.management.model.Student;
import com.msns.management.model.StudentClass;
import com.msns.management.repository.ClassRespository;
import com.msns.management.repository.SectionRepository;
import com.msns.management.repository.StudentClassRepository;
import com.msns.management.service.StudentClassService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentClassServiceImp implements StudentClassService {

    @Autowired
    private StudentClassRepository scRepo;

    @Autowired
    private ClassRespository classRepo;

    @Autowired
    private SectionRepository sectionRepo;

    /**
     * @deprecated
     */
    @Override
    public StudentClass createStudentClass(StudentClass studentClass) {

        Classs info = this.classRepo.findById(studentClass.getClassInfo().getClassId()).get();
        Section section = this.sectionRepo.findById(studentClass.getSection().getSectionId()).get();

        studentClass.setClassInfo(info);
        studentClass.setSection(section);

        StudentClass response = this.scRepo.save(studentClass);

        if (response != null) {
            log.info("Student Added to Section.");
            return response;
        }
        return null;
    }

    @Override
    public String addStudentClass(Student[] students, int classId) {

        try {
            Classs info = this.classRepo.findById(classId).orElse(null);
            Section section = this.sectionRepo.findById(1).orElse(null);

            for (Student element : students) {
                log.info("Adding student");
                StudentClass sc = new StudentClass();
                sc.setStudent(element);
                sc.setClassInfo(info);
                sc.setSection(section);
                sc.setRollNumber("");
                this.scRepo.save(sc);
            }

            return "Students added successfully";
        } catch (Exception e) {
            log.error("Error occurred while adding students: " + e.getMessage());
            return "Failed to add students. Please try again later.";
        }
    }
     private String generateRollNo(Student student,String className,String sectionName){
        String rollNo;
        Student lastStudent = this.studentRepo.LastStudentOfClass(student.getClassSection().getCsId());
        if(lastStudent == null){
            log.info("First student in class");
            rollNo = className+"-"+sectionName+"-"+"1";
            return rollNo;
        }
        else{
            log.info("Generating roll in class containing students");
            String roll[] = lastStudent.getStudentRoll().split("-");    
            int increment = Integer.parseInt(roll[2]);
            increment++;
            rollNo = className+"-"+sectionName+"-"+increment;
            return rollNo;
        }
        
    }

    @Override
    public List<StudentClass> getAllStudentByClass(int classId) {

        List<StudentClass> response = this.scRepo.findAllStudentsByClassInfoClassId(classId);

        if (response.isEmpty() || response.size() == 0) {
            log.error("No student Found in given class Id {}", classId);
            return null;
        }

        return response;
    }

}
