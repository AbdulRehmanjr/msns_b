package com.msns.management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class StudentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentClassId;

    @ManyToOne
    private Classs classInfo;

    @ManyToOne
    private Section section;

    private String rollNumber="";

    @OneToOne
    private Student student;

    @ManyToOne
    private Fee fee;

    private boolean isPaid=false;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(int studentClassId) {
        this.studentClassId = studentClassId;
    }

    public Classs getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(Classs classInfo) {
        this.classInfo = classInfo;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

}
