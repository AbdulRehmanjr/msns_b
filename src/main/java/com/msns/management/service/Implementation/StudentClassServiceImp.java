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
     * The function creates a new student class by associating a class info and
     * section with the
     * student class object and saving it to the database.
     * 
     * @deprecated
     * @param studentClass The studentClass parameter is an object of the
     *                     StudentClass class, which
     *                     represents a student's enrollment in a specific class and
     *                     section. It contains information such
     *                     as the student's details, the class information, and the
     *                     section they are enrolled in.
     * @return The method is returning a StudentClass object.
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

    /**
     * The function adds an array of students to a specific class and section,
     * generating a roll number
     * for each student.
     * 
     * @param students An array of Student objects representing the students to be
     *                 added to the class.
     * @param classId  The classId parameter is an integer that represents the ID of
     *                 the class to which
     *                 the students will be added.
     * @return The method is returning a String. If the students are added
     *         successfully, it will return
     *         "Students added successfully". If there is an error while adding
     *         students, it will return
     *         "Failed to add students. Please try again later."
     */
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
                sc.setRollNumber(generateRollNo(info));
                this.scRepo.save(sc);
            }

            return "Students added successfully";
        } catch (Exception e) {
            log.error("Error occurred while adding students: " + e.getMessage());
            return "Failed to add students. Please try again later.";
        }
    }

    /**
     * This function generates a roll number for a student based on the class they
     * belong to.
     * 
     * @param info The "info" parameter is an object of the "Classs" class, which
     *             contains information
     *             about a particular class.
     * @return The method is returning a String value, which is the generated roll
     *         number.
     */
    private String generateRollNo(Classs info) {
        String rollNo = "";
        StudentClass lastStudent = this.scRepo.findTopByClassInfoClassIdOrderByStudentClassIdDesc(info.getClassId());

        if (lastStudent == null) {
            log.info("First student in class");
            rollNo = info.getClassName() + "-" + "1";
            return rollNo;
        } else {
            log.info("Generating roll in class containing students");
            String roll[] = lastStudent.getRollNumber().split("-");
            int increment = Integer.parseInt(roll[1]);
            increment++;
            rollNo = info.getClassName() + "-" + increment;
            return rollNo;
        }

    }

    /**
     * This Java function retrieves a list of all students in a given class based on
     * the class ID.
     * 
     * @param classId The classId parameter is an integer that represents the unique
     *                identifier of a
     *                class.
     * @return The method is returning a List of StudentClass objects.
     */
    @Override
    public List<StudentClass> getAllStudentByClass(int classId) {

        List<StudentClass> response = this.scRepo.findAllStudentsByClassInfoClassId(classId);

        if (response.isEmpty() || response.size() == 0) {
            log.error("No student Found in given class Id {}", classId);
            return null;
        }

        return response;
    }

    @Override
    public StudentClass payFee(StudentClass student) {
        
        student.setPaid(true);
        StudentClass response = this.scRepo.save(student);
        if (response != null) {
            log.info("Fee Paid for student {} Father Name {}.",student.getStudent().getStudentName(),student.getStudent().getFatherName());
            return response;
        }
        return null;
    }

}
